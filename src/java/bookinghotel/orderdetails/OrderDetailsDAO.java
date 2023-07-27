/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.orderdetails;

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
public class OrderDetailsDAO implements Serializable {

    public boolean insertOrderDetails(OrderDetailsDTO orderDetailsDTO) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                //orderId, roomNo, hotelId, orderQuantity, night, checkIn, checkOut
                String sql = "insert into tblOrderDetails values(?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, orderDetailsDTO.getOrderId());
                pst.setInt(2, orderDetailsDTO.getRoomNo());
                pst.setInt(3, orderDetailsDTO.getHotelId());
                pst.setInt(4, orderDetailsDTO.getOrderQuantity());
                pst.setInt(5, orderDetailsDTO.getNight());
                pst.setString(6, orderDetailsDTO.getCheckIn());
                pst.setString(7, orderDetailsDTO.getCheckOut());

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

    public List<OrderDetailsDTO> listOrderDetailsByOrderId(String orderId) throws NamingException, SQLException {
        List<OrderDetailsDTO> listOrderDetail = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select tblRoom.roomNo, tblHotel.hotelId,tblHotel.hotelName, tblRoom.roomName, tblOrderDetails.orderQuantity, tblOrderDetails.night, tblOrderDetails.checkIn, tblOrderDetails.checkOut,tblRoom.roomPrice,tblOrder.[status]\n"
                        + "from tblOrderDetails\n"
                        + "inner join tblRoom on tblRoom.roomNo = tblOrderDetails.roomNo\n"
                        + "inner join tblHotel on tblHotel.hotelId =tblOrderDetails.hotelId\n"
                        + "inner join tblOrder on tblOrder.orderId =tblOrderDetails.orderId\n"
                        + "Where tblOrderDetails.orderId =  ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, orderId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int roomNo = rs.getInt("roomNo");
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    String roomName = rs.getString("roomName");
                    int orderQuantity = rs.getInt("orderQuantity");
                    int night = rs.getInt("night");
                    String checkIn = rs.getString("checkIn");
                    String checkOut = rs.getString("checkOut");
                    float roomPrice = rs.getFloat("roomPrice");
                    int status = rs.getInt("status");
                    OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(orderId, roomNo, hotelId,
                            hotelName, roomName, orderQuantity, night, checkIn, checkOut, roomPrice,status);
                    listOrderDetail.add(orderDetailsDTO);
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
        return listOrderDetail;
    }
}
