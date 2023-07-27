/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.admin;

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
import bookinghotel.rooms.RoomDAO;
import bookinghotel.rooms.RoomDTO;
import bookinghotel.roomtypes.RoomTypesDAO;
import bookinghotel.roomtypes.RoomTypesDTO;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "LoadRoomAdminServlet", urlPatterns = {"/LoadRoomAdminServlet"})
public class LoadRoomAdminServlet extends HttpServlet {

    private static final String HOME_PAGE_ADMIN = "homeForAdmin.jsp";

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
        String url = HOME_PAGE_ADMIN;
        try {
            RoomDAO room = new RoomDAO();
            RoomTypesDAO rtDAO = new RoomTypesDAO();
           
           List<RoomTypesDTO> listRT = rtDAO.listAllRoomType();
            List<RoomDTO> listRoom = room.getListRoom();

            request.setAttribute("LIST_ROOM", listRoom);
            
            request.setAttribute("LIST_ROOMTYPES", listRT);
            
        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT CheckDiscountCodeServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT CheckDiscountCodeServlet:" + e.toString());
        } catch (Exception e) {
            log("Error at LoadRoomServlet:" + e.toString());
        } finally {           
            RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
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
