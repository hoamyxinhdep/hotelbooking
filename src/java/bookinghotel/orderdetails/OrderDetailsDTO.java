/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.orderdetails;

import java.io.Serializable;

/**
 *
 * @author Phước Hà
 */
public class OrderDetailsDTO implements Serializable {

    //orderId, roomNo, hotelId, orderQuantity
//    Select orderId, roomNo, hotelId, orderQuantity, night, checkIn, checkOut
//    from tblOrderDetails
    private String orderId;
    private int roomNo;
    private int hotelId;
    private String hotelName;
    private String roomName;
    private int orderQuantity;
    private int night;
    private String checkIn;
    private String checkOut;
    
    private float roomPrice;
    private int status;

    public OrderDetailsDTO(String orderId, int roomNo, int hotelId, int orderQuantity, int night, String checkIn, String checkOut) {
        this.orderId = orderId;
        this.roomNo = roomNo;
        this.hotelId = hotelId;
        this.orderQuantity = orderQuantity;
        this.night = night;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    

    public OrderDetailsDTO(String orderId, int roomNo, int hotelId, String hotelName, String roomName, int orderQuantity, int night, String checkIn, String checkOut, float roomPrice) {
        this.orderId = orderId;
        this.roomNo = roomNo;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.orderQuantity = orderQuantity;
        this.night = night;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomPrice = roomPrice;
    }
        public OrderDetailsDTO(String orderId, int roomNo, int hotelId, String hotelName, String roomName, int orderQuantity, int night, String checkIn, String checkOut, float roomPrice,int status) {
        this.orderId = orderId;
        this.roomNo = roomNo;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.roomName = roomName;
        this.orderQuantity = orderQuantity;
        this.night = night;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomPrice = roomPrice;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

}
