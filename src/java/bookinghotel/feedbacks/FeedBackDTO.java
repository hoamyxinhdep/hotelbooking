/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.feedbacks;

import bookinghotel.users.UserDTO;
import java.io.Serializable;

/**
 *
 * @author Phước Hà
 */
public class FeedBackDTO implements Serializable {

    private int roomNo;
    private String userId;
    private int value;
    private String description;
    private UserDTO userDTO;

    public FeedBackDTO(int roomNo, String userId, int value) {
        this.roomNo = roomNo;
        this.userId = userId;
        this.value = value;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    
}
