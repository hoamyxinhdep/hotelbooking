/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.validation;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Phước Hà
 */
public class encrypted {
    
    public static String encryptedPassword(String password) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-256");    //khai bao hasing
            // Change this to UTF-16 if needed
            md.update(password.getBytes(StandardCharsets.UTF_8));   // change text to byte[]
            // md.update(text) --> nghia la add byte[] text vao diget
            byte[] digest = md.digest();    // hashing mang byte[] trong md
            String hex = String.format("%064x", new BigInteger(1, digest));
            // login -> foo(pass)--> DB -> match(hop li) --> 
            // update -> foo(pass) --> update
            return hex;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        encrypted a = new encrypted();
        
        System.out.println(a.encryptedPassword("1234"));
    }
}
