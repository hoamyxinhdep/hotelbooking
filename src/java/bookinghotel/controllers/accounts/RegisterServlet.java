/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.accounts;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bookinghotel.accounts.AccountDAO;
import bookinghotel.accounts.AccountDTO;
import bookinghotel.users.UserDAO;
import bookinghotel.users.UserDTO;
import bookinghotel.users.UserError;
import bookinghotel.validation.CheckValidation;
import bookinghotel.validation.encrypted;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private static final String REGISTER_PAGE = "signup.jsp";
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
        String url = REGISTER_PAGE;
        UserError userError = new UserError("", "", "", "", "", "");

        try {
            AccountDAO accountDAO = new AccountDAO();

            String userId = request.getParameter("userId");
            String email = request.getParameter("email");
            String password = request.getParameter("Password");
            String rePassword = request.getParameter("rePassword");
            String fullname = request.getParameter("fullName");
            String phoneNumber = request.getParameter("phoneNumber");
            boolean check = true;
            String msg = "";

            if (accountDAO.checkAccountExist(userId)) {
                check = false;
                userError.setUserIDError("The Account have been Taken!");
            }

            if (!CheckValidation.isValidEmailAddress(email)) {
                check = false;
                userError.setEmailError("Email must be between 6-30 characters in length and in the format xxx@xxx.xxx");
            }
            if (!CheckValidation.isValidPassword(password)) {
                check = false;
                userError.setPasswordError("Password must be 8-30 characters and include at least 1 uppercase letter, 1 number and 1 special character!");
            }
            if (!rePassword.equals(password)) {
                check = false;
                userError.setConfirmPasswordError("RePassword must match the password");
            }
            if (fullname.length() < 1 && fullname.length() > 20) {
                check = false;
                userError.setNameError("Name valid in [1,20]!");
            }
           
            if (!CheckValidation.isValidPhoneNumber(phoneNumber)) {
                check = false;
                userError.setPhoneNumberError("Phone Number is Invalid");
            }

            if (check) {
                UserDAO userDAO = new UserDAO();
                AccountDTO acc = new AccountDTO(userId, encrypted.encryptedPassword(rePassword), 2, "Active");
                UserDTO user = new UserDTO(userId, fullname, email, phoneNumber);

                if (accountDAO.insertAccount(acc) && userDAO.insertUser(user)) {
                    url = LOGIN_PAGE;
                    msg = "Create Success! Login here!";
                    request.setAttribute("CREATE_MSG", msg);
                }

            } else {
                request.setAttribute("USER_ERROR", userError);
                
            }

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT RegisterServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT RegisterServlet:" + e.toString());
        } catch (Exception e) {
            log("Error At RegisterServlet: " + e.toString());
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
