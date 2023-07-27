/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.validation;

import java.util.List;
import bookinghotel.rooms.RoomDTO;


/**
 *
 * @author Phước Hà
 */
public class HotelUtils {
    
    public int getAvailableHotel(List<RoomDTO> listRoom){
        int result = 0;
        for (int i = 0; i < listRoom.size(); i++) {
            result += listRoom.get(i).getQuantity();
        }
        return result;
    }
    
    
    public boolean deleteNotAvailableRoom(List<RoomDTO> listRoom, int avaiDate){
        for (int i = 0; i < listRoom.size(); i++) {
            if(listRoom.get(i).getQuantity()<avaiDate){
                return true;
            }
        }
        return false;
     
    }
}
