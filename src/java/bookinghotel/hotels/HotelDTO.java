/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.hotels;

import java.io.Serializable;

/**
 *
 * @author Phước Hà
 */
public class HotelDTO implements Serializable {

    private int hotelId;
    private String hotelName;
    private String hotelAddress;
    private int available;
    private boolean status;

    public HotelDTO(int hotelId, String hotelName, String hotelAddress, boolean status) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.status = status;
    }

    public HotelDTO( String hotelName, String hotelAddress, boolean status) {
        
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        
        this.status = status;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
    
    

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
