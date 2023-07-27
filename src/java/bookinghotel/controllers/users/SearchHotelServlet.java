/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.controllers.users;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import bookinghotel.hotels.HotelDAO;
import bookinghotel.hotels.HotelDTO;
import bookinghotel.rooms.RoomDAO;
import bookinghotel.rooms.RoomDTO;
import bookinghotel.validation.HotelUtils;

/**
 *
 * @author Phước Hà
 */
@WebServlet(name = "SearchHotelServlet", urlPatterns = {"/SearchHotelServlet"})
public class SearchHotelServlet extends HttpServlet {

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
        String url = HOME_PAGE_USER;
        try {
            String searchValue = request.getParameter("searchValue");
            String msg = "";
            String checkInValue = request.getParameter("checkIn");
            String checkOutValue = request.getParameter("checkOut");
            int avaiRoom = Integer.parseInt(request.getParameter("avaiRoom"));
            Long milis = System.currentTimeMillis();
            Date today = new Date(milis);
            if (checkInValue == null || checkInValue.equals("") || checkOutValue == null || checkOutValue.equals("")) {
                msg = "please pick Up CheckIn and CheckOut !";
            } else {
                Date checkIn = Date.valueOf(checkInValue);
                Date checkOut = Date.valueOf(checkOutValue);
                LocalDate dateBefore = LocalDate.parse(request.getParameter("checkIn"));
                LocalDate dateAfter = LocalDate.parse(request.getParameter("checkOut"));
                int noOfDaysBetween = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter);
                if (checkIn.before(today)) {
                    msg = "Can't Order Date Before Today!";

                } else if(checkOut.equals(checkIn)){
                    msg = "You can't Check out equals CheckIn Date!";
                }else if (checkOut.before(checkIn)) {
                    msg = "You can't Check out before CheckIn Date!";

                } else if (noOfDaysBetween > 30) {
                    msg = "You can't Check out greater than 30 day!";

                } else if (avaiRoom <= 0) {
                    msg = "You can't find the negative Room!";
                } else {

                    HotelDAO hotelDAO = new HotelDAO();
                    List<HotelDTO> listHotelDTOs = hotelDAO.searchListHotel(searchValue, searchValue);

                    HttpSession session = request.getSession();

                    RoomDAO roomDAO = new RoomDAO();
                    HotelUtils hotelUtils = new HotelUtils();

                    Iterator iterhotel = listHotelDTOs.iterator();
                    while (iterhotel.hasNext()) {
                        HotelDTO hotelI = (HotelDTO) iterhotel.next();
                        List<RoomDTO> listRoom = roomDAO.searchListRoom2(hotelI.getHotelId(),
                                checkIn.toString(), checkOut.toString());

                        Iterator iter = listRoom.iterator();
                        while (iter.hasNext()) {
                            RoomDTO roomIter = (RoomDTO) iter.next();
                            if (roomIter.getQuantity() < avaiRoom) {
                                iter.remove();
                            }
                        }
                        if (listRoom.size() != 0) {
                            hotelI.setAvailable(hotelUtils.getAvailableHotel(listRoom));
                        } else {
                            iterhotel.remove();
                        }
                        msg = "Search success!";
                    }
                    if (listHotelDTOs.size() != 0) {
                        
                        request.setAttribute("LIST_HOTEL", listHotelDTOs);
                        HotelDTO hotelDTO = listHotelDTOs.get(0);
                        url  = "ViewListRoomServlet?hotelId="+hotelDTO.getHotelId();
                    } else {
                        msg = "There is no Room With Your Available!";
                        request.setAttribute("LIST_HOTEL", null);
                    }
                    session.setAttribute("SEARCH_VALUE", searchValue);
                    session.setAttribute("CHECKIN_DATE", checkIn.toString());
                    session.setAttribute("CHECKOUT_DATE", checkOut.toString());
                    session.setAttribute("AVAIROOM", avaiRoom);
                    session.setAttribute("CLICK_SEARCH", "true");

                }
            }

            request.setAttribute("SEARCH_MSG", msg);

        } catch (SQLException e) {
            log("Error SQL EXCEPTION AT SearchHotelServlet:" + e.toString());
        } catch (NamingException e) {
            log("Error Naming EXCEPTION AT SearchHotelServlet:" + e.toString());
        } catch (Exception e) {
            log("Error at SearchHotelServlet:" + e.toString());
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
