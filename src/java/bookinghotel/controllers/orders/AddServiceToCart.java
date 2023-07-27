/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bookinghotel.controllers.orders;

import bookinghotel.accounts.AccountDTO;
import bookinghotel.orderservices.OrderServiceDAO;
import bookinghotel.orderservices.ServiceDTO;
import bookinghotel.rooms.RoomDTO;
import bookinghotel.validation.OrderUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DW
 */
@WebServlet(name = "AddServiceToCart", urlPatterns = {"/add-service-cart"})
public class AddServiceToCart extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddServiceToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddServiceToCart at " + request.getContextPath() + "</h1>");
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
        String url = "ViewCartServlet";
        try {
            HttpSession session = request.getSession();
            AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACC");
            if (accountDTO != null) {

                OrderServiceDAO orderServiceDAO = new OrderServiceDAO();
                List<ServiceDTO> serviceDTOs = new ArrayList<>();
                String[] selectedServices = request.getParameterValues("selectedServices");
                if (selectedServices != null) {
                    for (String serviceId : selectedServices) {
                        serviceDTOs.add(orderServiceDAO.getServiceById(Integer.parseInt(serviceId)));
                    }
                    session.setAttribute("LIST_SERVICE_CHECKED", serviceDTOs);
                    List<RoomDTO> roomDTOs = (List<RoomDTO>) session.getAttribute("LIST_CART");
                    OrderUtils orderUtils = new OrderUtils();
                    float total = orderUtils.calculateTotalWithService(roomDTOs, serviceDTOs);
                    session.setAttribute("TOTAL", total);
                }else{
                    session.setAttribute("LIST_SERVICE_CHECKED", serviceDTOs);
                    List<RoomDTO> roomDTOs = (List<RoomDTO>) session.getAttribute("LIST_CART");
                    OrderUtils orderUtils = new OrderUtils();
                    float total = orderUtils.calculateTotalWithService(roomDTOs, serviceDTOs);
                    session.setAttribute("TOTAL", total);
                }

            } else {
                url = "login1.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
