/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paypal.common;

import bookinghotel.accounts.AccountDTO;
import bookinghotel.discountcodes.DiscountDAO;
import bookinghotel.discountcodes.DiscountDTO;
import bookinghotel.orderdetails.OrderDetailsDAO;
import bookinghotel.orderdetails.OrderDetailsDTO;
import bookinghotel.orders.OrderDAO;
import bookinghotel.orderservices.OrderServiceDAO;
import bookinghotel.orderservices.ServiceDTO;
import bookinghotel.utils.Contant;
import java.io.IOException;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import javax.naming.NamingException;

/**
 *
 * @author ptd
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
//        Profile profile = (Profile) session.getAttribute("USER");
        AccountDTO userDTO = (AccountDTO) session.getAttribute("ACC");
        String orderId = req.getParameter("orderId");
        String amountS = req.getParameter("amount");
        OrderDAO orderDAO = new OrderDAO();
        OrderDetailsDAO orderDetailDAO = new OrderDetailsDAO();
        OrderServiceDAO orderServiceDAO = new OrderServiceDAO();
        if (userDTO != null) {
            try {
                List<OrderDetailsDTO> listOrder = orderDetailDAO.listOrderDetailsByOrderId(orderId);
                Transaction listTransaction = getTransactionInformation(listOrder, orderServiceDAO.getListServiceByOrder(orderId), "");
                APIContext apiContext = new APIContext(Contant.CLIENT_ID, Contant.CLIENT_SECRET, Contant.CLIENT_MODE);

                Payment payment = new Payment();
                payment.setIntent("sale");

                Payer payer = getPayerInformation(userDTO);
                payment.setPayer(payer);

                RedirectUrls redirectUrls = getRedirectURLs();
                payment.setRedirectUrls(redirectUrls);
                payment.setTransactions(Collections.singletonList(listTransaction)); // Set a single transaction

                Payment approvedPayment = payment.create(apiContext);

                String link = getApprovalLink(approvedPayment);
                res.sendRedirect(link);

            } catch (PayPalRESTException ex) {
                log("PaymentServlet_Paypal_error: " + ex.getMessage());
            } catch (NamingException ex) {
                log("PaymentServlet_NamingException_error: " + ex.getMessage());
            } catch (SQLException ex) {
                log("PaymentServlet_SQLException_error: " + ex.getMessage());
            }
        }
    }

    private Transaction getTransactionInformation(List<OrderDetailsDTO> orderDetails, List<ServiceDTO> serviceDTOs, String discountCode) throws SQLException, NamingException {
        List<Item> items = new ArrayList<>();
        ItemList itemList = new ItemList();
        Transaction transaction = new Transaction();
        Amount amount = new Amount();
        OrderServiceDAO orderServiceDAO = new OrderServiceDAO();
        DiscountDAO discountDAO = new DiscountDAO();

        transaction.setAmount(amount);
        transaction.setDescription("Thanh toan cho phong va dich vu");

        Item item = new Item();
        item.setCurrency("USD");
        item.setName("Tien Phong");
        DiscountDTO discountDTO = discountDAO.checkDiscountCode(discountCode);
        double roomPrice = 0;
        if (discountDTO != null) {
            roomPrice = orderDetails.stream().mapToDouble(r -> r.getRoomPrice() * r.getNight() * r.getOrderQuantity()).sum() - (discountDTO.getDiscountValue() * 100);
        } else {
            roomPrice = orderDetails.stream().mapToDouble(r -> r.getRoomPrice() * r.getNight() * r.getOrderQuantity()).sum();
        }
        item.setPrice(String.valueOf(roomPrice));

        item.setSku(orderDetails.get(0).getOrderId());
        item.setQuantity("1");
        items.add(item);

        Item service = new Item();
        service.setCurrency("USD");
        service.setName("Total Service");
        service.setPrice(String.valueOf(serviceDTOs.stream().mapToDouble(i -> i.getPrice()).sum()));
        service.setQuantity("1");
        items.add(service);

//        DiscountDTO discountDTO = discountDAO.checkDiscountCode(discountCode);
//        if (discountDTO != null) {
//            Item discount = new Item();
//            discount.setCurrency("USD");
//            discount.setName("Giam gia");
//            discount.setQuantity("1");
//            items.add(discount);
//        }
        itemList.setItems(items);
        transaction.setItemList(itemList);

//        double tienPhong = Double.valueOf(item.getPrice());
//        double tienDichVu = Double.valueOf(service.getPrice());
//        
//        
        double totalAmount = items.stream()
                .mapToDouble(i -> Double.parseDouble(i.getPrice()))
                .sum();
        amount.setCurrency("USD");
        amount.setTotal(String.valueOf(totalAmount));

        transaction.setAmount(amount);

        return transaction;
    }

    private Payer getPayerInformation(AccountDTO p) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(p.getName())
                .setLastName(p.getName())
                .setEmail(p.getEmail());
        payer.setPayerInfo(payerInfo);

        return payer;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/BookingHotel/cancle.jsp");
        redirectUrls.setReturnUrl("http://localhost:8080/BookingHotel/paymentConfirm");

        return redirectUrls;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
