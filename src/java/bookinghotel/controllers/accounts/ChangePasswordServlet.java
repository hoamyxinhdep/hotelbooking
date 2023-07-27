/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package bookinghotel.controllers.accounts;

import bookinghotel.accounts.AccountDAO;
import bookinghotel.accounts.AccountDTO;
import bookinghotel.users.UserDAO;
import bookinghotel.users.UserDTO;
import bookinghotel.users.UserError;
import bookinghotel.validation.CheckValidation;
import bookinghotel.validation.encrypted;
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
@WebServlet(name="ChangePasswordServlet", urlPatterns={"/changePass"})
public class ChangePasswordServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ChangePasswordServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath () + "</h1>");
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
            String password = request.getParameter("password");
            String newpassword = request.getParameter("newpassword");
            String confpassword = request.getParameter("confpassword");
            String userId = request.getParameter("userId");
            AccountDAO accountDAO = new AccountDAO();
            AccountDTO accountDto = accountDAO.getAccountByUserName(userId);
            HttpSession session = request.getSession();
           
            boolean check = true;
            if(!encrypted.encryptedPassword(password).equals(accountDto.getPassword())){
                check = false;
                userError.setNameError("Password does not match. Please enter again");
            }
            if (!CheckValidation.isValidPassword(newpassword)) {
                check = false;
                userError.setPasswordError("Password must be 8-30 characters and include at least 1 uppercase letter, 1 number and 1 special character");
            }
            if (!confpassword.equals(newpassword)) {
                check = false;
                userError.setConfirmPasswordError("RePassword must match the password");
            }
            if(check == true){
                accountDAO.setPassword(newpassword, userId);
                request.setAttribute("mess", "Successfull!!");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                session.setAttribute("ACC", accountDto);
                
            }else{
                request.setAttribute("USER_ERROR", userError);
                 request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
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
