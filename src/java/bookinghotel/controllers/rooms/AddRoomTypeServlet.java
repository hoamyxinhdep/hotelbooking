/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package bookinghotel.controllers.rooms;

import bookinghotel.roomtypes.RoomTypesDAO;
import bookinghotel.roomtypes.RoomTypesDTO;
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
@WebServlet(name="AddRoomTypeServlet", urlPatterns={"/addroomtype"})
public class AddRoomTypeServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddRoomTypeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRoomTypeServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String typeName = request.getParameter("typeName");
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            String utilities = request.getParameter("utilities");
            String bed = request.getParameter("bed");
            int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));
            RoomTypesDAO rtDao = new RoomTypesDAO();
            RoomTypesDTO roomCheck = rtDao.selectRoomTypeByName(typeName);
            if(roomCheck != null){
                request.setAttribute("RT_CHECK", "Room type name already exists");
                request.getRequestDispatcher("LoadRoomAdminServlet").forward(request, response);
            }else if(maxPeople <= 0){
                request.setAttribute("RT_CHECK", "Maximum number of people must be greater than 0");
                request.getRequestDispatcher("LoadRoomAdminServlet").forward(request, response);
            }else{
                RoomTypesDTO rtDto = new RoomTypesDTO(typeName, description, image, maxPeople, utilities, bed);
            if(rtDao.insertRoomType(rtDto)){
                response.sendRedirect("LoadRoomAdminServlet");
            }
            }
            
            

        } catch (Exception e) {
            
            response.sendRedirect("LoadRoomAdminServlet");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
