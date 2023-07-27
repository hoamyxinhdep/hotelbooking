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
import jakarta.servlet.http.HttpSession;
import bookinghotel.accounts.AccountDAO;
import bookinghotel.accounts.AccountDTO;
import bookinghotel.users.UserDAO;
import bookinghotel.users.UserDTO;
import bookinghotel.validation.encrypted;
import jakarta.servlet.http.Cookie;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login1.jsp";
    private static final String HOME_PAGE_USER = "MainController?btnAction=";
    private static final String HOME_PAGE_ADMIN = "MainController?btnAction=homeForAdmin";

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
        String url = LOGIN_PAGE;
        try {
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            String remember = request.getParameter("remember");
            Cookie u = new Cookie("userc", username);
            Cookie p = new Cookie("passc", password);
            Cookie r = new Cookie("remc", remember);
            if(remember!=null){
            u.setMaxAge(60*60*24*7);
            p.setMaxAge(60*60*24*7);
            r.setMaxAge(60*60*24*7);
        }else{
            u.setMaxAge(0);
            p.setMaxAge(0);
            r.setMaxAge(0);
        }
        response.addCookie(u);
        response.addCookie(p);
        response.addCookie(r);

            AccountDAO accDao = new AccountDAO();
            AccountDTO accDto = accDao.loginMA(username, password);
            UserDAO userDao = new UserDAO();
            UserDTO userDto = userDao.getUserByUserName(username);
           
            HttpSession session = request.getSession();
            String msg = "";
            if (accDto == null) {
                msg = "Your Username or Password is Invalid!";
            } else {
                session.setAttribute("ACC", accDto);
                 session.setAttribute("ACCUSER", userDto);
                if (accDto.getRoleId() == 1) {
                    url = HOME_PAGE_ADMIN;
                } else {
                    url = HOME_PAGE_USER;
                }
            }

            request.setAttribute("LOGIN_MSG", msg);

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT LOGINSERVLET:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT LOGINSERVLET:" + e.toString());
        } catch (Exception e) {
            log("Error AT LOGINSERVLET:" + e.toString());
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
