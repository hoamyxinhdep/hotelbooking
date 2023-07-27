/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.feedbacks;

import bookinghotel.users.UserDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import bookinghotel.utils.DBHelper;

public class FeedBackDAO implements Serializable {

    public boolean insertFeedBack(FeedBackDTO feedBackDTO) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblFeedBack values(?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, feedBackDTO.getRoomNo());
                pst.setString(2, feedBackDTO.getUserId());
                pst.setInt(3, feedBackDTO.getValue());
                pst.setString(4, feedBackDTO.getDescription());
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

    public int getAVGFeedback(int roomNo) throws SQLException, NamingException {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT AVG(value) AS averageValue\n"
                        + "FROM tblFeedBack\n"
                        + "WHERE roomNo = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("averageValue");
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

    public boolean checkUserIdFeedBacked(int roomNo, String userId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "    Select roomNo, userId, value \n"
                        + "from tblFeedBack \n"
                        + "where roomNo = ? and userId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                pst.setString(2, userId);
                rs = pst.executeQuery();
                if (rs.next()) {
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
    UserDAO userDAO = new UserDAO();

    public List<FeedBackDTO> getListFeedBack(int roomNo) throws NamingException, SQLException {
        List<FeedBackDTO> listFeedBackDTOs = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select roomNo, userId, value, description \n"
                        + "from tblFeedBack\n"
                        + "where roomNo = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, roomNo);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String userId = rs.getString("userId");
                    int value = rs.getInt("value");
                    FeedBackDTO feedBackDTO = new FeedBackDTO(roomNo, userId, value);
                    feedBackDTO.setDescription(rs.getString("description"));
                    feedBackDTO.setUserDTO(userDAO.getUserByUserName(userId));
                    listFeedBackDTOs.add(feedBackDTO);
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
        return listFeedBackDTOs;

    }

    public List<FeedBackDTO> getAllFeedBack() throws NamingException, SQLException {
        List<FeedBackDTO> listFeedBackDTOs = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select roomNo, userId, value, description \n"
                        + "from tblFeedBack";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("userId");
                    int value = rs.getInt("value");
                    FeedBackDTO feedBackDTO = new FeedBackDTO(rs.getInt("roomNo"), userId, value);
                    feedBackDTO.setDescription(rs.getString("description"));
                    feedBackDTO.setUserDTO(userDAO.getUserByUserName(userId));
                    listFeedBackDTOs.add(feedBackDTO);
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
        return listFeedBackDTOs;

    }

}
