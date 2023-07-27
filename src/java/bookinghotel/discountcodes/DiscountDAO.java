/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.discountcodes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import bookinghotel.utils.DBHelper;

/**
 *
 * @author Phước Hà
 */
public class DiscountDAO implements Serializable {

    public DiscountDTO checkDiscountCode(String discountCode) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnect();
            if (con != null) {
                String sql = "Select discountCode, discountValue, existDate \n"
                        + "from tblDiscount \n"
                        + "Where discountCode = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, discountCode);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int discountValue = rs.getInt("discountValue");
                    String existDate = rs.getString("existDate");
                    DiscountDTO discountDTO = new DiscountDTO(discountCode, discountValue, existDate);
                    return discountDTO;
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
}
