/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.rooms;

import bookinghotel.roomtypes.RoomTypesDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import bookinghotel.utils.DBHelper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phước Hà
 */
public class RoomDAO implements Serializable {

    public List<RoomDTO> searchListRoom(int hotelId) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select hotelId, roomNo, roomName, availableDate, quantity, typeId, roomPrice\n"
                        + "from tblRoom\n"
                        + "Where hotelId = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, hotelId);
                rs = pst.executeQuery();

                while (rs.next()) {
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    String availableDate = rs.getString("availableDate");
                    int quantity = getSubtractRoomQuantityNoDate(roomNo);
                    if (quantity <= 0) {
                        quantity = getRoomQuantity(roomNo);
                    }
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    RoomDTO room = new RoomDTO(hotelId, roomNo, roomName, availableDate, quantity, typeId, roomPrice);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    listRoom.add(room);

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
        return listRoom;

    }

    public List<RoomDTO> searchListRoom3(int hotelId, String checkIn, String checkOut, int index) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT hotelId, roomNo, roomName, availableDate, quantity, typeId, roomPrice\n"
                        + "FROM tblRoom\n"
                        + "WHERE hotelId = ?\n"
                        + "ORDER BY roomNo \n"
                        + "OFFSET ? ROWS\n"
                        + "FETCH NEXT 4 ROWS ONLY ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, hotelId);
                pst.setInt(2, (index - 1) * 4);
                rs = pst.executeQuery();

                while (rs.next()) {
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    String availableDate = rs.getString("availableDate");
                    int quantity = getSubtractRoomQuantityWithDate(roomNo, checkIn, checkOut);
                    if (quantity <= 0) {
                        quantity = getRoomQuantity(roomNo);
                    }
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    RoomDTO room = new RoomDTO(hotelId, roomNo, roomName, availableDate, quantity, typeId, roomPrice);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    listRoom.add(room);
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
        return listRoom;

    }

    public List<RoomDTO> searchListRoom2(int hotelId, String checkIn, String checkOut) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select hotelId, roomNo, roomName, availableDate, quantity, typeId, roomPrice\n"
                        + "from tblRoom\n"
                        + "Where hotelId = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, hotelId);
                rs = pst.executeQuery();

                while (rs.next()) {
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    String availableDate = rs.getString("availableDate");
                    int quantity = getSubtractRoomQuantityWithDate(roomNo, checkIn, checkOut);
                    if (quantity <= 0) {
                        quantity = getRoomQuantity(roomNo);
                    }
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    RoomDTO room = new RoomDTO(hotelId, roomNo, roomName, availableDate, quantity, typeId, roomPrice);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    listRoom.add(room);
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
        return listRoom;

    }

    public RoomDTO getRoomByName(String name) throws SQLException {
        RoomDTO room = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select * from tblRoom Where roomName = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, name);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    String avail = rs.getString("availableDate");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    room = new RoomDTO(hotelId, roomName, quantity, typeId, roomPrice);

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
        return room;
    }

    public RoomDTO getRoomInfor(int hotelId, int roomNo, String checkIn, String checkOut) throws NamingException, SQLException {
        RoomDTO room = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, typeId, roomPrice \n"
                        + "from tblRoom\n"
                        + "inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId\n"
                        + "Where tblHotel.hotelId = ?  and tblRoom.roomNo = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, hotelId);
                pst.setInt(2, roomNo);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String hotelName = rs.getString("hotelName");
                    String roomName = rs.getString("roomName");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    int roomQuantity = getSubtractRoomQuantityWithDate(roomNo, checkIn, checkOut);
                    room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", roomQuantity, typeId, roomPrice, 0, "", "", 0);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    //hotelId,hotelName,roomNo,roomName,typeId,roomPrice
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
        return room;

    }

    public int checkExistInDB(int roomNo, String checkIn, String checkOut) throws NamingException, SQLException {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "/****** Script for SelectTopNRows command from SSMS  ******/\n"
                        + "DECLARE @duration INT = DATEDIFF(DAY, ?, ?);\n"
                        + "\n"
                        + "SELECT \n"
                        + "    CASE\n"
                        + "        WHEN (\n"
                        + "            (SELECT quantity FROM tblRoom WHERE roomNo = ?)\n"
                        + "            - \n"
                        + "            (SELECT COALESCE(SUM(orderQuantity), 0) AS Quantity FROM tblOrderDetails\n"
                        + "             INNER JOIN tblOrder ON tblOrderDetails.orderId = tblOrder.orderId\n"
                        + "             WHERE tblOrderDetails.roomNo = ?\n"
                        + "             AND tblOrder.[status] != 3 AND tblOrder.[status] != 4\n"
                        + "             AND tblOrderDetails.checkIn >= ? AND tblOrderDetails.checkOut < DATEADD(DAY, @duration, ?))\n"
                        + "        ) > 0 AND (\n"
                        + "            (SELECT quantity FROM tblRoom WHERE roomNo = ?)\n"
                        + "            - \n"
                        + "            (SELECT COALESCE(SUM(orderQuantity), 0) AS Quantity FROM tblOrderDetails\n"
                        + "             INNER JOIN tblOrder ON tblOrderDetails.orderId = tblOrder.orderId\n"
                        + "             WHERE tblOrderDetails.roomNo = ?\n"
                        + "             AND tblOrder.[status] != 3 AND tblOrder.[status] != 4\n"
                        + "             AND tblOrderDetails.checkIn >= ? AND tblOrderDetails.checkOut < DATEADD(DAY, @duration, ?))\n"
                        + "        ) < (SELECT quantity FROM tblRoom WHERE roomNo = ?)\n"
                        + "        THEN (\n"
                        + "            (SELECT quantity FROM tblRoom WHERE roomNo = ?) - \n"
                        + "            (SELECT COALESCE(SUM(orderQuantity), 0) AS Quantity FROM tblOrderDetails\n"
                        + "                  INNER JOIN tblOrder ON tblOrderDetails.orderId = tblOrder.orderId\n"
                        + "                  WHERE tblOrderDetails.roomNo = ?\n"
                        + "                  AND tblOrder.[status] != 3 AND tblOrder.[status] != 4\n"
                        + "                  AND tblOrderDetails.checkIn >= ? AND tblOrderDetails.checkOut <DATEADD(DAY, @duration-2, ?))\n"
                        + "        )\n"
                        + "        ELSE (\n"
                        + "            (SELECT quantity FROM tblRoom WHERE roomNo = ?) -\n"
                        + "            (SELECT COALESCE(SUM(orderQuantity), 0) AS Quantity FROM tblOrderDetails\n"
                        + "                  INNER JOIN tblOrder ON tblOrderDetails.orderId = tblOrder.orderId\n"
                        + "                  WHERE tblOrderDetails.roomNo = ?\n"
                        + "                  AND tblOrder.[status] != 3 AND tblOrder.[status] != 4\n"
                        + "                  AND tblOrderDetails.checkOut BETWEEN ? AND DATEADD(DAY, @duration, ?))\n"
                        + "        )\n"
                        + "    END AS subtractQuantity;";
                pst = con.prepareStatement(sql);
                pst.setString(1, checkIn);
                pst.setString(2, checkOut);
                pst.setInt(3, roomNo);
                pst.setInt(4, roomNo);
                pst.setString(5, checkIn);
                pst.setString(6, checkOut);
                pst.setInt(7, roomNo);
                pst.setInt(8, roomNo);
                pst.setString(9, checkIn);
                pst.setString(10, checkOut);
                pst.setInt(11, roomNo);
                pst.setInt(12, roomNo);
                pst.setInt(13, roomNo);
                pst.setString(14, checkIn);
                pst.setString(15, checkOut);
                pst.setInt(16, roomNo);
                pst.setInt(17, roomNo);
                pst.setString(18, checkIn);
                pst.setString(19, checkOut);
                rs = pst.executeQuery();

                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("subtractQuantity");
                    if (result <= 0) {
                        result = 0;
                    }
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
        return result;

    }
    RoomTypesDAO roomTypesDAO = new RoomTypesDAO();

    public List<RoomDTO> getListRoom() throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, quantity ,typeId, roomPrice \n"
                        + "from tblRoom\n"
                        + "inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId		\n"
                        + "Where tblRoom.quantity > 0 ";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    listRoom.add(room);
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
        return listRoom;

    }

    public List<RoomDTO> getListRoomPage(int index) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT tblHotel.hotelId, tblHotel.hotelName, tblRoom.roomNo, tblRoom.roomName, tblRoom.quantity, tblRoom.typeId, tblRoom.roomPrice \n"
                        + "FROM tblRoom\n"
                        + "INNER JOIN tblHotel ON tblRoom.hotelId = tblHotel.hotelId\n"
                        + "WHERE tblRoom.quantity > 0\n"
                        + "ORDER BY tblRoom.roomNo\n"
                        + "OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY;";
                pst = con.prepareStatement(sql);
                pst.setInt(1, (index - 1) * 6);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    listRoom.add(room);
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
        return listRoom;

    }

    public int getTotalRoom() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select count(*) from tblRoom";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
            }
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public List<RoomDTO> getListRoomBySearchValue(String searchValue) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, quantity ,typeId, roomPrice \n"
                        + "from tblRoom\n"
                        + "inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId\n"
                        + "Where tblRoom.quantity > 0 and tblRoom.roomName like ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    listRoom.add(room);
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
        return listRoom;

    }

    public List<RoomDTO> getListRoomBySearchValue(float searchValue) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, quantity ,typeId, roomPrice \n"
                        + "from tblRoom\n"
                        + "inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId\n"
                        + "Where tblRoom.roomPrice <= ? ";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, searchValue);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    listRoom.add(room);
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
        return listRoom;

    }

    public List<RoomDTO> searchByNameRoom(String searchValue) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, quantity ,typeId, roomPrice\n"
                        + "from tblRoom\n"
                        + "inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId\n"
                        + "where tblHotel.hotelName like ? and tblRoom.quantity > 0";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");

                rs = pst.executeQuery();
                while (rs.next()) {

                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");

                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    listRoom.add(room);
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
        return listRoom;

    }

    public List<RoomDTO> searchByNameAndOptionRoom(String searchValue, String option) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, quantity ,typeId, roomPrice\n"
                        + "from tblRoom\n"
                        + "inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId\n"
                        + "where tblHotel.hotelName like ? and tblRoom.typeId like ? and tblRoom.quantity > 0";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setString(2, "%" + option + "%");

                rs = pst.executeQuery();
                while (rs.next()) {

                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");

                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    listRoom.add(room);
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
        return listRoom;

    }

    public List<RoomDTO> searchListRoomAll(String searchValue, String typeRoom, String checkIn, String checkOut) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, quantity ,typeId, roomPrice\n"
                        + "from tblRoom	\n"
                        + "inner join tblHotel on tblHotel.hotelId = tblRoom.hotelId\n"
                        + "where tblHotel.hotelName like ? \n"
                        + "and tblRoom.typeId like ? \n"
                        + "and tblRoom.availableDate between ? and ? and tblRoom.quantity > 0";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                pst.setString(2, typeRoom);
                pst.setString(3, checkIn);
                pst.setString(4, checkOut);

                rs = pst.executeQuery();
                while (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");

                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    listRoom.add(room);
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
        return listRoom;

    }

    public List<RoomDTO> searchByTypeRoom(String searchValue) throws NamingException, SQLException {
        List<RoomDTO> listRoom = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select tblHotel.hotelId, tblHotel.hotelName, roomNo, roomName, quantity ,typeId, roomPrice\n"
                        + "from tblRoom\n"
                        + "inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId\n"
                        + "where tblRoom.typeId like ? and tblRoom.quantity > 0";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");

                rs = pst.executeQuery();
                while (rs.next()) {

                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    int roomNo = rs.getInt("roomNo");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");

                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    listRoom.add(room);
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
        return listRoom;

    }

    public static int getSubtractRoomQuantityNoDate(int roomNo) throws SQLException, NamingException {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select \n"
                        + "		(select quantity from tblRoom where tblRoom.roomNo = ?)\n"
                        + "		- \n"
                        + "		(select COALESCE(SUM(orderQuantity),0) as Quantity from tblOrderDetails\n"
                        + "			where roomNo = ? \n"
                        + "		)\n"
                        + "	as subtractQuantity";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                pst.setInt(2, roomNo);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("subtractQuantity");
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
        return result;
    }

    public int getSubtractRoomQuantityWithDate(int roomNo, String checkIn, String checkOut) throws SQLException, NamingException {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT \n"
                        + "    CASE\n"
                        + "        WHEN ((\n"
                        + "            (SELECT quantity FROM tblRoom WHERE roomNo = ?)\n"
                        + "			-\n"
                        + "            (SELECT COALESCE(SUM(orderQuantity), 0) AS Quantity FROM tblOrderDetails\n"
                        + "             INNER JOIN tblOrder ON tblOrderDetails.orderId = tblOrder.orderId\n"
                        + "             WHERE tblOrderDetails.roomNo = ?\n"
                        + "             AND tblOrder.[status] != 3 AND tblOrder.[status] != 4\n"
                        + "             AND tblOrderDetails.checkIn >= ? AND tblOrderDetails.checkOut < ?)\n"
                        + "        ) > 0 and(\n"
                        + "		 (SELECT quantity FROM tblRoom WHERE roomNo = ?)\n"
                        + "			-\n"
                        + "            (SELECT COALESCE(SUM(orderQuantity), 0) AS Quantity FROM tblOrderDetails\n"
                        + "             INNER JOIN tblOrder ON tblOrderDetails.orderId = tblOrder.orderId\n"
                        + "             WHERE tblOrderDetails.roomNo = ?\n"
                        + "             AND tblOrder.[status] != 3 AND tblOrder.[status] != 4\n"
                        + "             AND tblOrderDetails.checkIn >= ? AND tblOrderDetails.checkOut < ?)\n"
                        + "		)< (SELECT quantity FROM tblRoom WHERE roomNo = ?))\n"
                        + "        THEN (\n"
                        + "		(SELECT quantity FROM tblRoom WHERE roomNo = ?) - \n"
                        + "        (SELECT COALESCE(SUM(orderQuantity), 0) AS Quantity FROM tblOrderDetails\n"
                        + "              INNER JOIN tblOrder ON tblOrderDetails.orderId = tblOrder.orderId\n"
                        + "              WHERE tblOrderDetails.roomNo = ?\n"
                        + "              AND tblOrder.[status] != 3 AND tblOrder.[status] != 4\n"
                        + "              AND tblOrderDetails.checkIn >= ? AND tblOrderDetails.checkOut < ?))\n"
                        + "		else (\n"
                        + "		(SELECT quantity FROM tblRoom WHERE roomNo = ?)-\n"
                        + "		(SELECT COALESCE(SUM(orderQuantity), 0) AS Quantity FROM tblOrderDetails\n"
                        + "              INNER JOIN tblOrder ON tblOrderDetails.orderId = tblOrder.orderId\n"
                        + "              WHERE tblOrderDetails.roomNo = ?\n"
                        + "              AND tblOrder.[status] != 3 AND tblOrder.[status] != 4\n"
                        + "              AND tblOrderDetails.checkOut between ? and DATEADD(DAY, 1, ?))\n"
                        + "		)\n"
                        + "       \n"
                        + "		\n"
                        + "    END AS subtractQuantity;";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                pst.setInt(2, roomNo);
                pst.setString(3, checkIn);
                pst.setString(4, checkOut);
                pst.setInt(5, roomNo);
                pst.setInt(6, roomNo);
                pst.setString(7, checkIn);
                pst.setString(8, checkOut);
                pst.setInt(9, roomNo);
                pst.setInt(10, roomNo);
                pst.setInt(11, roomNo);
                pst.setString(12, checkIn);
                pst.setString(13, checkOut);
                pst.setInt(14, roomNo);
                pst.setInt(15, roomNo);
                pst.setString(16, checkIn);
                pst.setString(17, checkOut);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("subtractQuantity");
                    if (result <= 0) {
                        result = 0;
                    }

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
        return result;
    }

    public int getRoomQuantity(int roomNo) throws SQLException, NamingException {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select quantity\n"
                        + "from tblRoom\n"
                        + "Where roomNo = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("quantity");
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
        return result;
    }

    public boolean getRoom(int roomNo) throws SQLException, NamingException {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select * from tblRoom Where roomNo = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                if (pst.executeUpdate() > 0) {
                    return true;
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
        return false;
    }

    public RoomDTO getRoomByRoomNo(int roomNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select * from tblRoom inner join tblHotel on tblRoom.hotelId = tblHotel.hotelId Where roomNo = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int hotelId = rs.getInt("hotelId");
                    String hotelName = rs.getString("hotelName");
                    String roomName = rs.getString("roomName");
                    int quantity = rs.getInt("quantity");
                    int typeId = rs.getInt("typeId");
                    float roomPrice = rs.getFloat("roomPrice");
                    RoomDTO room = new RoomDTO(hotelId, hotelName, roomNo, roomName, "", quantity, typeId, roomPrice, 0, "", "", 0);
                    room.setRoomTypesDTO(roomTypesDAO.selectRoomType(typeId));
                    return room;
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
        return null;
    }

    public boolean setRoomQuantity(int roomNo, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "update tblRoom\n"
                        + "set quantity = ? \n"
                        + "where roomNo = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, quantity);
                pst.setInt(2, roomNo);
                if (pst.executeUpdate() > 0) {
                    return true;
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
        return false;
    }

    public boolean setRoom(int roomNo, String roomName, int quantity, float price) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "update tblRoom set roomName=?,quantity=?,roomPrice=? where roomNo = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, roomName);
                pst.setInt(2, quantity);
                pst.setFloat(3, price);
                pst.setInt(4, roomNo);
                if (pst.executeUpdate() > 0) {
                    return true;
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
        return false;
    }

    public boolean deleteRoom(int roomNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "delete from tblRoom where roomNo=? ";
                pst = con.prepareStatement(sql);

                pst.setInt(1, roomNo);
                if (pst.executeUpdate() > 0) {
                    return true;
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
        return false;
    }

    public boolean insertRoom(RoomDTO room) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            int hotel = 0;

            if (con != null) {
                String sql = "insert into tblRoom (hotelId,roomName,quantity,typeId,roomPrice)\n"
                        + "  values (?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, hotel);
                pst.setString(2, room.getRoomName());
                pst.setInt(3, room.getQuantity());
                pst.setInt(4, room.getTypeId());
                pst.setFloat(5, room.getPrice());
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

    public static void main(String[] args) {

        RoomDAO dao = new RoomDAO();
        try {
            int listroom = dao.checkExistInDB(9, "2023-07-19", "2023-07-20");
            System.out.println(listroom);
        } catch (NamingException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
