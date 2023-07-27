/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.users;

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
import bookinghotel.feedbacks.FeedBackDAO;
import bookinghotel.feedbacks.FeedBackDTO;
import bookinghotel.rooms.RoomDAO;
import bookinghotel.rooms.RoomDTO;
import bookinghotel.validation.FeedBackUtils;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "ViewDetailRoomServlet", urlPatterns = {"/ViewDetailRoomServlet"})
public class ViewDetailRoomServlet extends HttpServlet {

    private static final String DETAIL_ROOM_PAGE = "detailRoom.jsp";
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
        String url = DETAIL_ROOM_PAGE;
        try {

            int hotelId = Integer.parseInt(request.getParameter("hotelId"));
            int roomNo = Integer.parseInt(request.getParameter("roomNo"));

            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("ACC");
            String checkIn = (String) session.getAttribute("CHECKIN_DATE");
            String checkOut = (String) session.getAttribute("CHECKOUT_DATE");
            

            String msg = "";
            if (acc == null) {
                url = LOGIN_PAGE;
                msg = "You Need to Login To process this request!";
            } else {
                RoomDAO roomDAO = new RoomDAO();
                RoomDTO roomDTO = roomDAO.getRoomInfor(hotelId, roomNo, checkIn, checkOut);
                
                int quantityRoom = (int) session.getAttribute("AVAIROOM");
                int availRoom = roomDAO.checkExistInDB(roomNo, checkIn, checkOut);
                request.setAttribute("ROOM_Avail", availRoom);
                request.setAttribute("ROOM_DETAIL", roomDTO);
            }

            //Get Feedback Value
            FeedBackDAO feedBackDAO = new FeedBackDAO();
            List<FeedBackDTO> listFeedBackDTOs = feedBackDAO.getListFeedBack(roomNo);
            int avg = feedBackDAO.getAVGFeedback(roomNo);
            request.setAttribute("AVG_STAR", avg);
            request.setAttribute("FEED_BACKS", listFeedBackDTOs);
            request.setAttribute("VIEWDETAIL_MSG", msg);
        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT ViewDetailRoomServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT ViewDetailRoomServlet:" + e.toString());
        } catch (Exception e) {
            log("Error at ViewDetailRoomServlet:" + e.toString());
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
