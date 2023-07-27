/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.orders;

import java.io.Serializable;

/**
 *
 * @author Phước Hà
 */
public class OrderDTO implements Serializable {

    //orderId, userId, orderDate, total
    private String orderId;
    private String userId;
    private String orderDate;
    private float total;
    private int status;

    @Override
    public String toString() {
        return "OrderDTO{" + "orderId=" + orderId + ", userId=" + userId + ", orderDate=" + orderDate + ", total=" + total + ", status=" + status + '}';
    }

 

    public OrderDTO(String orderId, String userId, String oderDate, float total, int status) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = oderDate;
        this.total = total;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String oderDate) {
        this.orderDate = oderDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
