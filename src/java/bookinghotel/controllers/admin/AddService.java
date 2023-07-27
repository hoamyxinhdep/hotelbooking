/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bookinghotel.controllers.admin;

import bookinghotel.orderservices.OrderServiceDAO;
import bookinghotel.orderservices.ServiceDTO;
import bookinghotel.utils.UploadFile;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DW
 */
@WebServlet(name = "AddService", urlPatterns = {"/AddService"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class AddService extends HttpServlet {

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
            out.println("<title>Servlet AddService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddService at " + request.getContextPath() + "</h1>");
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
        String url = "LoadServices";
        try {
            String serviceId = request.getParameter("serviceId") == null ? "" : request.getParameter("serviceId");
            String name = request.getParameter("name") == null ? "" : request.getParameter("name");
            String price = request.getParameter("price") == null ? "" : request.getParameter("price");
            UploadFile uploadFile = new UploadFile();
            String fileName = uploadFile.uploadFile(request, "/img/services/");

            OrderServiceDAO orderServiceDAO = new OrderServiceDAO();
            ServiceDTO service = orderServiceDAO.getServiceByName(name);
            if (service != null) {
                request.setAttribute("SERVICE_CHECK", "Service already exists. Please re-enter");
                request.getRequestDispatcher("LoadServices").forward(request, response);
            } else if(Double.valueOf(price)<=0){
                request.setAttribute("SERVICE_CHECK", "The price of the service must be greater than 0");
                request.getRequestDispatcher("LoadServices").forward(request, response);
            }else{
                ServiceDTO serviceDTO = new ServiceDTO(0, name, Double.valueOf(price));
                serviceDTO.setImage(fileName);
                orderServiceDAO.saveService(serviceDTO);
            }

        } catch (Exception e) {
            response.sendRedirect(url);
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
