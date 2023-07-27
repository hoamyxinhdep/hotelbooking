/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.discountcodes;

import java.io.Serializable;

/**
 *
 * @author Phước Hà
 */
public class DiscountDTO implements Serializable {

    private String discountCode;
    private int discountValue;
    private String existDate;

    public DiscountDTO(String discountCode, int discountValue, String existDate) {
        this.discountCode = discountCode;
        this.discountValue = discountValue;
        this.existDate = existDate;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public String getExistDate() {
        return existDate;
    }

    public void setExistDate(String existDate) {
        this.existDate = existDate;
    }

}
