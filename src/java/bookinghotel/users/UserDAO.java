/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import bookinghotel.utils.DBHelper;
import bookinghotel.validation.encrypted;

/**
 *
 * @author Phước Hà
 */
public class UserDAO implements Serializable {

    public boolean insertUser(UserDTO user) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblUser values(?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, user.getUserId());
                pst.setString(2, user.getName());
                pst.setString(3, user.getEmail());
                pst.setString(4, user.getPhoneNumber());

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

    public UserDTO getUserByUserName(String UserId) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        UserDTO user = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select * from tblUser where userId =?";
                pst = con.prepareStatement(sql);
                pst.setString(1, UserId);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String userId = rs.getString("userId");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String phone = rs.getString("PhoneNumber");
                    user = new UserDTO(userId, name, email, phone);
                }
            }

        }  finally {
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
        return user;
    }
    public void updateInformation(String userId, String name, String email, String phone) throws SQLException{
        Connection con = null;
        PreparedStatement pst = null;
        
        ResultSet rs = null;
          try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "  update tblUser \n" +
"  set [Name] =?,Email=?,PhoneNumber=?\n" +
"\n" +
"  where userId =?";
                pst = con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, email);
                pst.setString(3, phone);
                pst.setString(4, userId);
               pst.executeUpdate();

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
     
    }
        
    

    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        UserDTO a = dao.getUserByUserName("admin");
        System.out.println(a);

    }
}
