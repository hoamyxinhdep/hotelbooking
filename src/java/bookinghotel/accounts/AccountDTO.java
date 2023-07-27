/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.accounts;

import java.io.Serializable;

/**
 *
 * @author Phước Hà
 */
public class AccountDTO implements Serializable {

    private String userId;
    private String password;
    private int roleId;
    private String status;
    private String name;
    private String email;
    private String phone;

    public AccountDTO(String userId, int roleId, String status, String name, String email, String phone) {
        this.userId = userId;
        this.roleId = roleId;
        this.status = status;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    public AccountDTO(String userId, String password, int roleId, String status) {
        this.userId = userId;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
