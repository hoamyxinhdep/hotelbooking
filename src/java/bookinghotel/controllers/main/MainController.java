/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.main;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String HOME_PAGE_USER = "HomeController";
    private static final String LOGIN_PAGE_USER = "login1.jsp";
    private static final String LOAD_ROOM_SERVLET = "LoadRoomServlet";
    private static final String LOAD_HOTEL_SERVLET = "LoadHotelUserServlet";
    private static final String LOGIN_SERVLET = "LoginServlet";
    private static final String HOME_FOR_ADMIN_SERVLET = "homeForAdmin.jsp";
    private static final String LOGOUT_SERVLET = "LogoutServlet";

    private static final String REGISTER_PAGE = "signup.jsp";
    private static final String REGISTER_SERVLET = "RegisterServlet";
    private static final String SEARCH_HOTEL_SERVLET = "SearchHotelServlet";
    private static final String VIEW_LIST_ROOM_SERVLET = "ViewListRoomServlet";
    private static final String VIEW_DETAIL_ROOM_SERVLET = "ViewDetailRoomServlet";
    private static final String ORDER_ROOM_SERVLET = "OrderRoomServlet";

    private static final String VIEW_CART_SERVLET = "ViewCartServlet";

    private static final String DELETE_CART_SERVLET = "DeleteCartServlet";

    private static final String UPDATE_CART_SERVLET = "UpdateCartServlet";

    private static final String CHECK_OUT_SERVLET = "CheckOutServlet";
    private static final String VIEW_ORDER_SERVLET = "ViewOrderServlet";
    private static final String VIEW_ORDERDETAILS_SERVLET = "ViewOrderDetailsServlet";
    private static final String DELETE_ORDER_SERVLET = "DeleteOrderServlet";
    private static final String FEEDBACK_PAGE = "FeedBackPageServlet";
    private static final String FEEDBACK_SERVLET = "FeedBackServlet";
    private static final String CHECK_DISCOUNTCODE_SERVLET = "CheckDiscountCodeServlet";

    private static final String CHANGE_DISCOUNTCODE_SERVLET = "ChangeDiscountCodeServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_PAGE_USER;
        try {
            String action = request.getParameter("btnAction");
            if (action == null) {
                url = HOME_PAGE_USER;
            } else if (action.equals("")) {
                url = HOME_PAGE_USER;
            } else if (action.equals("loginPage")) {
                url = LOGIN_PAGE_USER;
            } else if (action.equals("loginServlet")) {
                url = LOGIN_SERVLET;
            } else if (action.equals("logout")) {
                url = LOGOUT_SERVLET;
            } else if (action.equals("registerPage")) {
                url = REGISTER_PAGE;
            } else if (action.equals("CreateServlet")) {
                url = REGISTER_SERVLET;
            } else if (action.equals("SearchHotel")) {
                url = SEARCH_HOTEL_SERVLET;
            } else if (action.equals("viewListRoom")) {
                url = VIEW_LIST_ROOM_SERVLET;
            } else if (action.equals("viewDetailRoom")) {
                url = VIEW_DETAIL_ROOM_SERVLET;
            } else if (action.equals("Order Now")) {
                url = ORDER_ROOM_SERVLET;
            } else if (action.equals("ViewCart")) {
                url = VIEW_CART_SERVLET;
            } else if (action.equals("deleteCart")) {
                url = DELETE_CART_SERVLET;
            } else if (action.equals("updateCart")) {
                url = UPDATE_CART_SERVLET;
            } else if (action.equals("checkOut")) {
                url = CHECK_OUT_SERVLET;
            } else if (action.equals("ViewOrder")) {
                url = VIEW_ORDER_SERVLET;
            } else if (action.equals("viewOrderDetails")) {
                url = VIEW_ORDERDETAILS_SERVLET;

            } else if (action.equals("deleteOrder")) {
                url = DELETE_ORDER_SERVLET;
            } else if (action.equals("feedBackPage")) {
                url = FEEDBACK_PAGE;
            } else if (action.equals("FeedBackServlet")) {
                url = FEEDBACK_SERVLET;
            } else if (action.equals("checkDiscountCode")) {
                url = CHECK_DISCOUNTCODE_SERVLET;
            } else if (action.equals("homeForAdmin")) {
                url = HOME_FOR_ADMIN_SERVLET;
            } else if (action.equals("changeDiscount")) {
                url = CHANGE_DISCOUNTCODE_SERVLET;

            } else if (action.equals("addservice")) {
                url = "add-service-cart";
            }

        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
