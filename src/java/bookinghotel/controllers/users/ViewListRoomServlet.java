/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.users;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import bookinghotel.rooms.RoomDAO;
import bookinghotel.rooms.RoomDTO;
import bookinghotel.validation.OrderUtils;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "ViewListRoomServlet", urlPatterns = {"/ViewListRoomServlet"})
public class ViewListRoomServlet extends HttpServlet {

    private static final String ROOM_PAGE_USER = "roomForUser.jsp";
    private static final String HOME_PAGE_USER = "homeForUser.jsp";

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
        String url = ROOM_PAGE_USER;
        try {
            HttpSession session = request.getSession();
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            RoomDAO roomDAO = new RoomDAO();
            int count = roomDAO.getTotalRoom();
            int endP = count / 6;
            if (count % 6 != 0) {
                endP++;
            }
//
            int hotelId = Integer.parseInt(request.getParameter("hotelId"));

            List<RoomDTO> listRoom = null;
            String msg = "";
            String checkIn = (String) session.getAttribute("CHECKIN_DATE");
            String checkOut = (String) session.getAttribute("CHECKOUT_DATE");
            if (checkIn == null || checkOut == null) {
                url = "MainController?btnAction=";
                msg = "You must to pick Up CheckIn and Check Out Date!";
            } else {
                if (session.getAttribute("CLICK_SEARCH") == null) {
                    listRoom = roomDAO.searchListRoom(hotelId);
                } else {
                    List<RoomDTO> listCart = (List<RoomDTO>) session.getAttribute("LIST_CART");
                    if (listCart == null) {
                        if (session.getAttribute("AVAIROOM") == null) {
                            listRoom = roomDAO.searchListRoom3(hotelId, checkIn, checkOut,index);
                        } else {

                            listRoom = roomDAO.searchListRoom3(hotelId, checkIn, checkOut,index);
                            int avaiRoom = (int) session.getAttribute("AVAIROOM");

                            Iterator iterListRoom = listRoom.iterator();
                            while (iterListRoom.hasNext()) {
                                RoomDTO roomIter = (RoomDTO) iterListRoom.next();
                                if (roomIter.getQuantity() < avaiRoom) {
                                    iterListRoom.remove();
                                }
                            }
                        }
                    } else {
                        if (session.getAttribute("AVAIROOM") == null) {
                            OrderUtils orderUtils = new OrderUtils();
                            listRoom = roomDAO.searchListRoom3(hotelId, checkIn, checkOut,index);
                            for (int i = 0; i < listRoom.size(); i++) {
                                if (orderUtils.checkExistIncartWithRoomId(listCart, listRoom.get(i).getRoomNo())) {
                                    listRoom.remove(i);
                                }
                            }
                        } else {
                            OrderUtils orderUtils = new OrderUtils();
                            listRoom = roomDAO.searchListRoom3(hotelId, checkIn, checkOut,index);
                            Iterator iterList = listRoom.iterator();
                            while (iterList.hasNext()) {
                                RoomDTO roomIter = (RoomDTO) iterList.next();
                                if (orderUtils.checkExistIncartWithRoomId(listCart, roomIter.getRoomNo())) {
                                    iterList.remove();
                                }
                            }
                            int avaiRoom = (int) session.getAttribute("AVAIROOM");
                            Iterator iterListRoom = listRoom.iterator();
                            while (iterListRoom.hasNext()) {
                                RoomDTO roomIter = (RoomDTO) iterListRoom.next();
                                if (roomIter.getQuantity() < avaiRoom) {
                                    iterListRoom.remove();
                                }
                            }
                        }
                    }
                }
            }
            request.setAttribute("endP", endP);
            request.setAttribute("tag", index);
            request.setAttribute("SEARCH_MSG", msg);
            request.setAttribute("LIST_ROOM", listRoom);

        } catch (Exception e) {
            log("Error at ViewDetailRoomServlet:" + e.toString());
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
