/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.users;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import bookinghotel.accounts.AccountDTO;
import bookinghotel.feedbacks.FeedBackDAO;
import bookinghotel.feedbacks.FeedBackDTO;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "FeedBackServlet", urlPatterns = {"/FeedBackServlet"})
public class FeedBackServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login1.jsp";
    private static final String VIEW_ORDERDETAILS_PAGE = "viewOrderDetails.jsp";

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
        String url = VIEW_ORDERDETAILS_PAGE;
        try {
            int roomNo = Integer.parseInt(request.getParameter("roomNo"));
            int value = Integer.parseInt(request.getParameter("value"));
            String description = request.getParameter("description");
            String orderId = request.getParameter("orderId");
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            String msg = "";
            if (acc != null) {
                FeedBackDTO feedBackDTO = new FeedBackDTO(roomNo, acc.getUserId(), value);
                feedBackDTO.setDescription(description);
                FeedBackDAO feedBackDAO = new FeedBackDAO();

                feedBackDAO.insertFeedBack(feedBackDTO);
                int avg = feedBackDAO.getAVGFeedback(roomNo);
                msg = "Feed Back Success! Thank For Using The Service!";
                url = "MainController?btnAction=viewOrderDetails&orderId=" + orderId;
                request.setAttribute("AVG_STAR", avg);

            } else {
                msg = "You need Login To Process This Request!";
                url = LOGIN_PAGE;
            }
            request.setAttribute("FEEDBACK_MSG", msg);

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT FeedBackServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT FeedBackServlet:" + e.toString());
        } catch (Exception e) {
            log("Error at FeedBackServlet:" + e.toString());
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
