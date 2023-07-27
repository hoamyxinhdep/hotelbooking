/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bookinghotel.controllers.rooms;

import bookinghotel.rooms.RoomDAO;
import bookinghotel.rooms.RoomDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddRoom", urlPatterns = {"/addroom"})
public class AddRoom extends HttpServlet {

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
        response.sendRedirect("LoadRoomAdminServlet");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddRoom</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRoom at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String roomName = request.getParameter("roomName");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float price = Float.parseFloat(request.getParameter("price"));
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            RoomDAO roomDao = new RoomDAO();
            RoomDTO roomCheck = roomDao.getRoomByName(roomName);
            if (roomCheck != null) {
                request.setAttribute("ROOM_CHECK", "Room name already exists. Please re-enter");
                request.getRequestDispatcher("LoadRoomAdminServlet").forward(request, response);

            } else if (price <= 0) {
                request.setAttribute("ROOM_CHECK", "The price of the room must be greater than 0");
                request.getRequestDispatcher("LoadRoomAdminServlet").forward(request, response);
            } else if (quantity <= 0) {
                request.setAttribute("ROOM_CHECK", "The quantity of the room must be greater than 0");
                request.getRequestDispatcher("LoadRoomAdminServlet").forward(request, response);
            } else {

                RoomDTO roomDto = new RoomDTO(0, roomName, quantity, typeId, price);
                if (roomDao.insertRoom(roomDto)) {
                    response.sendRedirect("LoadRoomAdminServlet");
                }
            }

//            }} else {
//                RoomDTO roomDto = new RoomDTO(0, roomName, quantity, typeId, price);
//                if (roomDao.insertRoom(roomDto)) {
//                    response.sendRedirect("LoadRoomAdminServlet");
//                }else{
//                    response.sendRedirect("Admin.jsp");
//                }
//            }
        } catch (Exception e) {
            response.sendRedirect("LoadRoomAdminServlet");
        }

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
