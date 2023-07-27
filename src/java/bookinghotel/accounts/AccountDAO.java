/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.accounts;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import bookinghotel.utils.DBHelper;
import bookinghotel.validation.encrypted;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phước Hà
 */
public class AccountDAO implements Serializable {

    public AccountDTO login(String username, String Password) throws NamingException, SQLException {
        AccountDTO acc = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select userId, Password, RoleId, Status\n"
                        + "From tblAccount\n"
                        + "Where userId = ? and Password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, Password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("userId");
                    String password = rs.getString("Password");
                    int roleId = rs.getInt("RoleId");
                    String status = rs.getString("Status");
                    acc = new AccountDTO(email, password, roleId, status);
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
        return acc;
    }

    public AccountDTO loginMA(String username, String Password) throws NamingException, SQLException, NoSuchAlgorithmException {
        AccountDTO acc = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select userId, Password, RoleId, Status\n"
                        + "From tblAccount\n"
                        + "Where userId = ? and Password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, encrypted.encryptedPassword(Password));
                rs = pst.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("userId");
                    String password = rs.getString("Password");
                    int roleId = rs.getInt("RoleId");
                    String status = rs.getString("Status");
                    acc = new AccountDTO(email, password, roleId, status);
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
        return acc;
    }

    public boolean insertAccount(AccountDTO acc) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblAccount values(?,?,?,?);";
                pst = con.prepareStatement(sql);
                pst.setString(1, acc.getUserId());
                pst.setString(2, acc.getPassword());
                pst.setInt(3, acc.getRoleId());
                pst.setString(4, acc.getStatus());
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

    public AccountDTO getAccountByUserName(String UserId) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        AccountDTO account = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select * from tblAccount where userId =?";
                pst = con.prepareStatement(sql);
                pst.setString(1, UserId);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String userId = rs.getString("userId");
                    String pass = rs.getString("Password");
                    int role = rs.getInt("RoleId");
                    String status = rs.getString("Status");
                    account = new AccountDTO(userId, pass, role, status);
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
        return account;
    }

    public void updatePass(String pass, String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;

        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = " update tblAccount set [Password] =? where userId =?";

                pst = con.prepareStatement(sql);
                pst.setString(1, pass);
                pst.setString(2, userId);

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
        public void updateRole(int role, String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;

        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = " update tblAccount set [RoleId] =? where userId =?";

                pst = con.prepareStatement(sql);
                pst.setInt(1, role);
                pst.setString(2, userId);

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

    public boolean checkAccountExist(String userId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select userId\n"
                        + "from tblAccount\n"
                        + "Where userId = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, userId);
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

    public boolean setPassword(String pass, String username) throws SQLException, NamingException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = " update tblAccount set [Password] =? where userId =?";
                pst = con.prepareStatement(sql);
                pst.setString(1, encrypted.encryptedPassword(pass));
                pst.setString(2, username);
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

    public List<AccountDTO> loadAllAccount() throws NamingException, SQLException {
        List<AccountDTO> listAccount = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT tblUser.*, tblAccount.RoleId, tblAccount.[Status]\n"
                        + "FROM tblUser\n"
                        + "JOIN tblAccount ON tblUser.userId = tblAccount.userId;";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userId");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String phone = rs.getString("PhoneNumber");
                    int roleId = rs.getInt("RoleId");
                    String status = rs.getString("Status");
                    AccountDTO account = new AccountDTO(userID, roleId, status, name, email, phone);
                    listAccount.add(account);
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
        return listAccount;

    }

}
