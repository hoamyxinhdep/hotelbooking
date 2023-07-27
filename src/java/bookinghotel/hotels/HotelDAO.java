/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.hotels;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import bookinghotel.utils.DBHelper;

/**
 *
 * @author Phước Hà
 */
public class HotelDAO implements Serializable {

    public List<HotelDTO> searchListHotel(String searchValue, String address) throws NamingException, SQLException {
        List<HotelDTO> listHotel = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select hotelId, hotelName, hotelAddress, Status\n"
                        + "	from tblHotel\n"
                        + "	where hotelName like ? or hotelAddress like ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setString(2, "%" + address + "%");
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    String hotelAddress = rs.getString("hotelAddress");
                    boolean status = rs.getBoolean("status");
                    HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelAddress, status);
                    listHotel.add(hotel);
                    
                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listHotel;

    }

//    public List<HotelDTO> searchByNameHotel(String searchValue) throws NamingException, SQLException {
//        List<HotelDTO> listHotel = new ArrayList<>();
//        Connection con = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        try {
//            con = DBHelper.makeConnect();
//            if (con != null) {
//                String sql = "select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, quantity ,typeId, roomPrice\n"
//                        + "from tblRoom\n"
//                        + "inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId\n"
//                        + "where tblHotel.hotelName like ? ";
//                pst = con.prepareStatement(sql);
//                pst.setString(1, "%" + searchValue + "%");
//
//                rs = pst.executeQuery();
//                while (rs.next()) {
//
//                    int hotelId = rs.getInt("hotelId");
//                    String hotelName = rs.getString("hotelName");
//                    String hotelAddress = rs.getString("hotelAddress");
//                    boolean status = rs.getBoolean("status");
//
//                    HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelAddress, status);
//
//                    listHotel.add(hotel);
//                }
//
//            }
//
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (pst != null) {
//                pst.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return listHotel;
//
//    }

    public List<HotelDTO> loadListHotel() throws NamingException, SQLException {
        List<HotelDTO> listHotel = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select hotelId, hotelName, hotelAddress, Status\n"
                        + "	from tblHotel";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    String hotelAddress = rs.getString("hotelAddress");
                    boolean status = rs.getBoolean("status");
                    HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelAddress, status);
                    listHotel.add(hotel);
                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listHotel;

    }
      public boolean insertHotel(HotelDTO hotel) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblHotel values(?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, hotel.getHotelName());
                pst.setString(2, hotel.getHotelAddress());
               
                pst.setBoolean(3, hotel.getStatus());
                if (pst.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
