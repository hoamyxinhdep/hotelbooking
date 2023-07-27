/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package bookinghotel.controllers.accounts;

import bookinghotel.users.UserDAO;
import bookinghotel.users.UserDTO;
import bookinghotel.users.UserError;
import bookinghotel.validation.CheckValidation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name="ViewInformationServlet", urlPatterns={"/viewinfo"})
public class ViewInformationServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ViewInformationServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewInformationServlet at " + request.getContextPath () + "</h1>");
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
        UserError userError = new UserError("", "", "", "", "", "");
        try {
            String userId = request.getParameter("userId");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
             boolean check = true;
            String msg = "";
            if (!CheckValidation.isValidEmailAddress(email)) {
                check = false;
                userError.setEmailError("Email must be between 6-30 characters in length and in the format xxx@xxx.xxx");
            }
           if (name.length() < 1 && name.length() > 20) {
                check = false;
                userError.setNameError("Name valid in [1,20]!");
            }
            if (!CheckValidation.isValidPhoneNumber(phone)) {
                check = false;
                userError.setPhoneNumberError("Phone Number is Invalid");
            }
            if(check){
                 UserDAO userDao = new  UserDAO();
            userDao.updateInformation(userId, name, email, phone);
            UserDTO userDto = userDao.getUserByUserName(userId);
             HttpSession session = request.getSession();
             session.setAttribute("ACCUSER", userDto);
            request.setAttribute("mess","Successful !!");
            request.getRequestDispatcher("information.jsp").forward(request, response);
            }else{
                request.setAttribute("USER_ERROR", userError);
                request.getRequestDispatcher("information.jsp").forward(request, response);
            }
           
        } catch (Exception e) {
            request.setAttribute("mess"," Update Fail !!");
            request.getRequestDispatcher("information.jsp").forward(request, response);
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
