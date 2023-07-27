/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookinghotel.orderservices;

import bookinghotel.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DW
 */
public class OrderServiceDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ServiceDTO> getListServiceByOrder(String orderId) throws SQLException {
        List<ServiceDTO> serviceDTOs = new ArrayList<>();
        try {
            String sql = "select s.id, s.name, s.price, s.image from tblOrderService os left join tblService s on os.serviceId = s.id\n"
                    + "where os.orderId = ?";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, orderId);
                rs = ps.executeQuery();
                while (rs.next()) {

                    ServiceDTO serviceDTO = new ServiceDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"));
                    serviceDTO.setImage(rs.getString("image"));
                    serviceDTOs.add(serviceDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return serviceDTOs;
    }

    public List<ServiceDTO> getListServices() throws SQLException {
        List<ServiceDTO> serviceDTOs = new ArrayList<>();
        try {
            String sql = "select * from tblService";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ServiceDTO serviceDTO = new ServiceDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"));
                    serviceDTO.setImage(rs.getString("image"));
                    serviceDTOs.add(serviceDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return serviceDTOs;
    }

    public ServiceDTO getServiceById(int id) throws SQLException {
        try {
            String sql = "select * from tblService where id = ?";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ServiceDTO serviceDTO = new ServiceDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"));
                    serviceDTO.setImage(rs.getString("image"));
                    return serviceDTO;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ServiceDTO getServiceByName(String name) throws SQLException {
        try {
            String sql = "select * from tblService where name = ?";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ServiceDTO serviceDTO = new ServiceDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"));
                    serviceDTO.setImage(rs.getString("image"));
                    return serviceDTO;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean deleteService(int id) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "delete from tblService where id=? ";
                pst = con.prepareStatement(sql);

                pst.setInt(1, id);
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

    public ServiceDTO getLastService() throws SQLException {
        try {
            String sql = "select top(1) * from tblService where id = ? order bt id desc";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ServiceDTO serviceDTO = new ServiceDTO(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"));
                    serviceDTO.setImage(rs.getString("image"));
                    return serviceDTO;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ServiceDTO updateServiceWithImage(ServiceDTO serviceDTO) throws SQLException {
        try {
            String sql = "UPDATE [dbo].[tblService]\n"
                    + "   SET [name] =?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[image] = ?\n"
                    + " WHERE id = ?";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, serviceDTO.getName());
                ps.setDouble(2, serviceDTO.getPrice());
                ps.setString(3, serviceDTO.getImage());
                ps.setInt(4, serviceDTO.getId());
                if (ps.executeUpdate() > 0) {
                    return getServiceById(serviceDTO.getId());
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ServiceDTO updateServiceNoImage(ServiceDTO serviceDTO) throws SQLException {
        try {
            String sql = "UPDATE [dbo].[tblService]\n"
                    + "   SET [name] =?\n"
                    + "      ,[price] = ?\n"
                    + " WHERE id = ?";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, serviceDTO.getName());
                ps.setDouble(2, serviceDTO.getPrice());
                ps.setInt(3, serviceDTO.getId());
                if (ps.executeUpdate() > 0) {
                    return getServiceById(serviceDTO.getId());
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ServiceDTO saveService(ServiceDTO serviceDTO) throws SQLException {
        try {
            String sql = "INSERT INTO [dbo].[tblService]([name],[price],[image])\n"
                    + "     VALUES (?,?,?)";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, serviceDTO.getName());
                ps.setDouble(2, serviceDTO.getPrice());
                ps.setString(3, serviceDTO.getImage());
                if (ps.executeUpdate() > 0) {
                    return getLastService();
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void saveOrderService(OrderServiceDTO orderServiceDTO) throws SQLException {
        try {
            String sql = "INSERT INTO [dbo].[tblOrderService]([orderId],[serviceId])\n"
                    + "     VALUES (?,?);";
            con = DBHelper.makeConnect();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, orderServiceDTO.getOrderId());
                ps.setInt(2, orderServiceDTO.getServiceId());
                ps.executeUpdate();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
