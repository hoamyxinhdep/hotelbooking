/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.validation;

import bookinghotel.orderservices.ServiceDTO;
import java.util.List;
import bookinghotel.rooms.RoomDTO;

/**
 *
 * @author Phước Hà
 */
public class OrderUtils {

    public boolean checkExistIncart(List<RoomDTO> listCart, List<RoomDTO> listRoom) {
        int RoomNo = 0;
        for (int j = 0; j < listCart.size(); j++) {
            RoomNo = listCart.get(j).getRoomNo();
            for (int i = 0; i < listRoom.size(); i++) {
                if (listRoom.get(i).getRoomNo() == RoomNo) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean checkExistIncartWithRoomId(List<RoomDTO> listCart, int roomNo) {
        for (int j = 0; j < listCart.size(); j++) {
            if (listCart.get(j).getRoomNo() == roomNo) {
                return true;
            }
        }
        return false;
    }

    public void removeListRoom(List<RoomDTO> listCart, List<RoomDTO> listRoom) {
        int RoomNo = 0;
        for (int j = 0; j < listCart.size(); j++) {
            RoomNo = listCart.get(j).getRoomNo();
            for (int i = 0; i < listRoom.size(); i++) {
                if (listRoom.get(i).getRoomNo() == RoomNo) {
                    listRoom.remove(i);
                }

            }
        }
    }

    public float calculatePriceTotalRoomPrice(int night, float Price, int quantity) {
        float result = 0;
        result = (night * Price) * quantity;
        return result;
    }

    public float calculateTotal(List<RoomDTO> listRoom) {
        float total = 0;
        for (int i = 0; i < listRoom.size(); i++) {
            total += listRoom.get(i).getTotal();
        }
        return total;
    }

    public float calculateTotalWithService(List<RoomDTO> listRoom, List<ServiceDTO> serviceDTOs) {
        float total = 0;
        for (int i = 0; i < listRoom.size(); i++) {
            total += listRoom.get(i).getTotal();
        }
        for (ServiceDTO serviceDTO : serviceDTOs) {
            total += serviceDTO.getPrice();
        }
        return total;
    }

    public boolean deleteRoomCart(List<RoomDTO> listRoom, int roomNo) {
        for (int i = 0; i < listRoom.size(); i++) {
            if (listRoom.get(i).getRoomNo() == roomNo) {
                listRoom.remove(i);
                return true;
            }
        }
        return false;
    }

    public void updateRoomNight(List<RoomDTO> listRoom, int roomNo, int night, int roomQuantity) {
        for (int i = 0; i < listRoom.size(); i++) {
            if (listRoom.get(i).getRoomNo() == roomNo) {
                listRoom.get(i).setNight(night);
                listRoom.get(i).setQuantity(roomQuantity);
            }
        }
    }

    public void updateRoomTotal(List<RoomDTO> listRoom, int roomNo, float total) {
        for (int i = 0; i < listRoom.size(); i++) {
            if (listRoom.get(i).getRoomNo() == roomNo) {
                listRoom.get(i).setTotal(total);
            }
        }
    }

    public String getCheckInRoomDate(List<RoomDTO> listRoom, int roomNo) {
        String checkInDate = "";
        for (int i = 0; i < listRoom.size(); i++) {
            if (listRoom.get(i).getRoomNo() == roomNo) {
                checkInDate = listRoom.get(i).getCheckInDate();
            }
        }
        return checkInDate;
    }

    public float getRoomPrice(List<RoomDTO> listRoom, int roomNo) {
        float price = 0;
        for (int i = 0; i < listRoom.size(); i++) {
            if (listRoom.get(i).getRoomNo() == roomNo) {
                price = listRoom.get(i).getPrice();
            }
        }
        return price;
    }

    public void updateRoomCheckoutDate(List<RoomDTO> listRoom, int roomNo, String checkOutDate) {
        for (int i = 0; i < listRoom.size(); i++) {
            if (listRoom.get(i).getRoomNo() == roomNo) {
                listRoom.get(i).setCheckOutDate(checkOutDate);
            }
        }
    }

    public String getAlphaNumericString(int n) {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public float calculatorDiscount(float total, int value) {
        return (total * value) / 100;
    }

    public boolean checkNotExistInDB(List<RoomDTO> listDB, int roomNo) {
        for (int i = 0; i < listDB.size(); i++) {
            if (listDB.get(i).getRoomNo() != roomNo) {
                return true;
            }
        }
        return false;
    }

}
