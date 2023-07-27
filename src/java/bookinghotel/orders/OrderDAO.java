/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.orders;

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
public class OrderDAO implements Serializable {

    public OrderDTO insertOrder(OrderDTO orderDTO) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "insert into tblOrder values(?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, orderDTO.getOrderId());
                pst.setString(2, orderDTO.getUserId());
                pst.setString(3, orderDTO.getOrderDate());
                pst.setFloat(4, orderDTO.getTotal());
                pst.setInt(5, orderDTO.getStatus());
                if (pst.executeUpdate() > 0) {
                    return getLastOrder();
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
        return null;
    }

    public List<OrderDTO> listOrderByName(String userId) throws NamingException, SQLException {
        List<OrderDTO> listOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select orderId, userId, orderDate, total, status\n"
                        + "from tblOrder\n"
                        + "Where userId = ? \n"
                        + "order by orderDate desc";
                pst = con.prepareStatement(sql);
                pst.setString(1, userId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderId = rs.getString("orderId");
                    String orderDate = rs.getString("orderDate");
                    Float total = rs.getFloat("total");
                    int status = rs.getInt("status");
                    OrderDTO order = new OrderDTO(orderId, userId, orderDate, total, status);
                    listOrder.add(order);
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
        return listOrder;
    }

    public OrderDTO getLastOrder() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select top(1) orderId, userId, orderDate, total, status\n"
                        + "from tblOrder\n"
                        + " order by orderId desc";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String orderId = rs.getString("orderId");
                    String orderDate = rs.getString("orderDate");
                    Float total = rs.getFloat("total");
                    int status = rs.getInt("status");
                    OrderDTO order = new OrderDTO(orderId, rs.getString("userId"), orderDate, total, status);
                    return order;
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

    public String getUserIdOrderByOrderId(String orderId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String email = "";
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT userId\n"
                        + "FROM tblOrder\n"
                        + "WHERE orderId = ?;";
                pst = con.prepareStatement(sql);
                pst.setString(1, orderId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    email = rs.getString("userId");

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
        return email;
    }

    public OrderDTO getOrderByOrderId(String orderId) throws NamingException, SQLException {
        OrderDTO order =null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT *\n"
                        + "FROM tblOrder\n"
                        + "WHERE orderId = ?;";
                pst = con.prepareStatement(sql);
                pst.setString(1, orderId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userId = rs.getString("userId");
                    String orderDate = rs.getString("orderDate");
                    float total = rs.getFloat("total");
                    order = new OrderDTO(orderId, userId, orderDate, total, 0);
                    
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
        return order;
    }

    public boolean updateOrderStatus(String orderId, int status) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "update tblOrder\n"
                        + "set status = ? \n"
                        + "where orderId = ? ";
                pst = con.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, orderId);
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

    public List<OrderDTO> listAllOrder() throws NamingException, SQLException {
        List<OrderDTO> listAllOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select * from tblOrder order by orderDate desc";
                pst = con.prepareStatement(sql);

                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderId = rs.getString("orderId");
                    String userId = rs.getString("userId");
                    String orderDate = rs.getString("orderDate");
                    Float total = rs.getFloat("total");
                    int status = rs.getInt("status");
                    OrderDTO order = new OrderDTO(orderId, userId, orderDate, total, status);
                    listAllOrder.add(order);
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
        return listAllOrder;
    }

    public List<OrderDTO> listAllOrderPaid() throws NamingException, SQLException {
        List<OrderDTO> listAllOrder = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "select * from tblOrder where status = 2 or status = 4 order by orderDate desc";
                pst = con.prepareStatement(sql);

                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderId = rs.getString("orderId");
                    String userId = rs.getString("userId");
                    String orderDate = rs.getString("orderDate");
                    Float total = rs.getFloat("total");
                    int status = rs.getInt("status");
                    OrderDTO order = new OrderDTO(orderId, userId, orderDate, total, status);
                    listAllOrder.add(order);
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
        return listAllOrder;
    }

    public float getTotalRevenue() throws SQLException, NamingException {
        float result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "SELECT SUM([total]) AS TotalSum\n"
                        + "FROM [tblOrder]\n"
                        + "WHERE [status] = 2 or [status] = 4";
                pst = con.prepareStatement(sql);

                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getFloat("TotalSum");
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
    public static void main(String[] args) {
        try {
        OrderDAO or = new OrderDAO();
        List<OrderDTO> a = or.listAllOrderPaid();
            for (OrderDTO orderDTO : a) {
                System.out.println(orderDTO);
            }
           
        
        
        } catch (Exception e) {
        }
        
    }

}
