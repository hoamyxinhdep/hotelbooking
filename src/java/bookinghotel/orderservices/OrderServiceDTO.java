/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookinghotel.orderservices;

import bookinghotel.orders.OrderDTO;
import java.io.Serializable;

/**
 *
 * @author DW
 */
public class OrderServiceDTO implements Serializable {

    private int id;
    private String orderId;
    private int serviceId;

    public OrderServiceDTO(int id, String orderId, int serviceId) {
        this.id = id;
        this.orderId = orderId;
        this.serviceId = serviceId;
    }
    public OrderServiceDTO( String orderId, int serviceId) {
        
        this.orderId = orderId;
        this.serviceId = serviceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

}
