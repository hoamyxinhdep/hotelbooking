/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.orders;

import java.io.IOException;
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
import bookinghotel.discountcodes.DiscountDAO;
import bookinghotel.discountcodes.DiscountDTO;
import bookinghotel.orderservices.OrderServiceDAO;
import bookinghotel.orderservices.ServiceDTO;
import bookinghotel.rooms.RoomDTO;
import bookinghotel.validation.OrderUtils;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "ChangeDiscountCodeServlet", urlPatterns = {"/ChangeDiscountCodeServlet"})
public class ChangeDiscountCodeServlet extends HttpServlet {

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
            String discountCode = request.getParameter("discountCode");
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            OrderUtils orderUtils = new OrderUtils();
            DiscountDAO discountDAO = new DiscountDAO();

            String msg = "";
            if (acc != null) {

                List<RoomDTO> listCart = (List<RoomDTO>) session.getAttribute("LIST_CART");
                float total = orderUtils.calculateTotal(listCart);

                DiscountDTO discount = discountDAO.checkDiscountCode(discountCode);

                if (discount == null) {
                    msg = "Nothing to Check!";
                } else {
                    msg = "Your total Will Discount:" + discount.getDiscountValue();
                    float discountTotal = orderUtils.calculatorDiscount(total, discount.getDiscountValue());
                    float newTotal = total - discountTotal;
                    session.setAttribute("TOTAL", newTotal);
                    session.setAttribute("DISCOUNT_CODE", discount);
                }

            } else {
                msg = "You need to Login To Process This Request!";
                url = LOGIN_PAGE;
            }
            OrderServiceDAO orderServiceDAO = new OrderServiceDAO();
            List<ServiceDTO> serviceCheckeds = (List<ServiceDTO>) session.getAttribute("LIST_SERVICE_CHECKED");
            session.setAttribute("serviceCheckeds", serviceCheckeds);
            List<ServiceDTO> serviceDTOs = orderServiceDAO.getListServices();
            request.setAttribute("services", serviceDTOs);

            request.setAttribute("CHECKDISCOUNT_MSG", msg);

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT CheckDiscountCodeServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT CheckDiscountCodeServlet:" + e.toString());
        } catch (Exception e) {
            log("ERROR at CheckDiscountCodeServlet:" + e.toString());
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
