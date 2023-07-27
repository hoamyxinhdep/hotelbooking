/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinghotel.utils;

/**
 *
 * @author ptd
 */
public class Contant {

    public static final int PAGE_SIZE = 6;

    // code for VNPAY
    public static final String VNP_COMMAND = "pay";
    public static final String VNP_CURCODE = "VND";
    public static final String VNP_VERSION = "2.1.0";
    public static final String VNP_TMNCODE = "B3GJ4EAH";
    public static final String VNP_HASHSCRET = "RAOFLVMYIXMFIPSSRIFYIAWLBOSIJTPQ";
    public static final String VNP_URI = "http://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNP_ORDER_TYPE_HOTEL = "170000";

//  public static final String VNP_RETURNURL = "https://5men.netlify.app/bookingConfirm";
    public static final String VNP_RETURNURL = "http://localhost:3000/bookingConfirm";

    public static final String VNP_RETURN_URL_APP = "";

    public static final String CLIENT_ID = "AYrQHcn3xVy1_MNFrqGOvlpl-w182slLCBfSbFLjDYITvAw3a0GdIHMW4Fe4GAf05Kozz44wBet_kNoF";
    public static final String CLIENT_SECRET = "EHwW8z7y6S90uvQZ0mnypkmk1kY4at2UjfCPz30bTC2pCy9C65__uBI4RvFBEApzl8HoMdjZ2KBYL6F9";
    public static final String CLIENT_MODE = "sandbox";
}
