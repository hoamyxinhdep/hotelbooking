/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.orders;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import bookinghotel.accounts.AccountDTO;
import bookinghotel.orderdetails.OrderDetailsDAO;
import bookinghotel.orderdetails.OrderDetailsDTO;
import bookinghotel.orders.OrderDAO;
import bookinghotel.orders.OrderDTO;
import bookinghotel.orderservices.OrderServiceDAO;
import bookinghotel.orderservices.OrderServiceDTO;
import bookinghotel.orderservices.ServiceDTO;
import bookinghotel.rooms.RoomDAO;
import bookinghotel.rooms.RoomDTO;
import bookinghotel.validation.OrderUtils;
import java.util.ArrayList;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private static final String VIEW_CART_PAGE = "viewCart.jsp";
    private static final String LOGIN_PAGE = "login1.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = VIEW_CART_PAGE;
        try {
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            String msg = "";

            if (acc != null) {

                List<RoomDTO> listCart = (List<RoomDTO>) session.getAttribute("LIST_CART");
                RoomDAO roomDAO = new RoomDAO();
                OrderUtils orderUtils = new OrderUtils();

                boolean check = true;

                for (int i = 0; i < listCart.size(); i++) {
                    if (roomDAO.checkExistInDB(listCart.get(i).getRoomNo(), listCart.get(i).getCheckInDate(),
                            listCart.get(i).getCheckOutDate()) < listCart.get(i).getQuantity()) {
                        check = false;
                    }
                }
                if (check) {
                    Float total = (Float) session.getAttribute("TOTAL");

                    if (listCart.size() == 0) {
                        msg = "Nothing To CheckOut!";
                        session.setAttribute("LIST_CART", null);
                        session.setAttribute("TOTAL", null);
                    } else {

                        String orderCode = orderUtils.getAlphaNumericString(10);
                        OrderDAO orderDAO = new OrderDAO();
                        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
                        //get To day
                        Long milis = System.currentTimeMillis();
                        Date today = new Date(milis);
                        //Order
                        OrderDTO order = new OrderDTO(orderCode, acc.getUserId(), today.toString(), total, 1);
                        OrderDTO orderDTO = orderDAO.insertOrder(order);
                        for (int i = 0; i < listCart.size(); i++) {

                            //orderId, roomNo, hotelId, orderQuantity, night, checkIn, checkOut
                            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orderCode, listCart.get(i).getRoomNo(),
                                    listCart.get(i).getHotelId(), listCart.get(i).getQuantity(), listCart.get(i).getNight(),
                                    listCart.get(i).getCheckInDate(), listCart.get(i).getCheckOutDate());

                            orderDetailsDAO.insertOrderDetails(orderDetailsDTO);
                        }
                        OrderServiceDAO orderServiceDAO = new OrderServiceDAO();
                        List<ServiceDTO> serviceDTOs = (List<ServiceDTO>) session.getAttribute("LIST_SERVICE_CHECKED");
                        if (serviceDTOs != null && !serviceDTOs.isEmpty()) {

                            for (ServiceDTO serviceDTO : serviceDTOs) {
                                OrderServiceDTO orderServiceDTO = new OrderServiceDTO(order.getOrderId(), serviceDTO.getId());
                                orderServiceDAO.saveOrderService(orderServiceDTO);
                            }

                        }
                        msg = "order success!";
                        url = "MainController?btnAction=";
                        session.setAttribute("TOTAL", null);
                        session.setAttribute("LIST_CART", null);
                        session.setAttribute("LIST_SERVICE_CHECKED", null);
                    }
                } else {
                    msg = "Order fail, Something not right!";
                    session.setAttribute("TOTAL", null);
                    session.setAttribute("LIST_CART", null);
                }
            } else {
                msg = "You Must Login To Process This Request!";
                session.setAttribute("TOTAL", null);
                session.setAttribute("LIST_CART", null);
            }
            request.setAttribute("CART_MSG", msg);
        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT CheckOutServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT CheckOutServlet:" + e.toString());
        } catch (Exception e) {
            log("Error at CheckOutServlet:" + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
