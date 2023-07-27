/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vnpay.common;

import bookinghotel.accounts.AccountDTO;
import bookinghotel.orders.OrderDAO;
import bookinghotel.orderservices.OrderServiceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MSII
 */
@WebServlet(name = "Payment_ipn", urlPatterns = {"/payment_ipn"})
public class Payment_ipn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("acc");
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }
            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);
            if (signValue.equals(vnp_SecureHash)) {
                boolean checkOrderId = true; // Giá trị của vnp_TxnRef tồn tại trong CSDL của merchant
                boolean checkAmount = true; //Kiểm tra số tiền thanh toán do VNPAY phản hồi(vnp_Amount/100) với số tiền của đơn hàng merchant tạo thanh toán: giả sử số tiền kiểm tra là đúng.
                boolean checkOrderStatus = true; // Giả sử PaymnentStatus = 0 (pending) là trạng thái thanh toán của giao dịch khởi tạo chưa có IPN.
                int vnp_TxnRef = 0;
                long amount = 0;
                String time = request.getParameter("vnp_PayDate");
                DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");
                LocalDateTime ldt = LocalDateTime.parse(time, f);
                Timestamp timestamp = Timestamp.valueOf(ldt);

                vnp_TxnRef = Integer.parseInt(request.getParameter("vnp_TxnRef"));
                amount = Long.parseLong(request.getParameter("vnp_Amount")) / 100;

                if (checkOrderId) {
                    if (checkAmount) {
                        if (checkOrderStatus) {
                            if ("00".equals(request.getParameter("vnp_ResponseCode"))) {
                                //Xử lý/Cập nhật tình trạng giao dịch thanh toán "Thành công"
                                OrderServiceDAO orderServiceDAO = new OrderServiceDAO();
                                OrderDAO orderDAO = new OrderDAO();
                                String OrderString = (String) session.getAttribute("PAY_ORDERID");
                                if (OrderString != null) {
                                    orderDAO.updateOrderStatus(OrderString, 2);
                                }
                                request.setAttribute("message", "successful transaction");
                            } else if ("10".equals(request.getParameter("vnp_ResponseCode"))) {
                                request.setAttribute("message",
                                        "The transaction failed due to: The customer did not verify the correct card/account information more than 3 times");
                            } else if ("11".equals(request.getParameter("vnp_ResponseCode"))) {
                                request.setAttribute("message", "Transaction failed due to: Expired pending payment. Please repeat the transaction.");
                            } else if ("24".equals(request.getParameter("vnp_ResponseCode"))) {
                                request.setAttribute("message", "Transaction failed due to: Customer cancels transaction.");
                            } else {
                                //Xử lý/Cập nhật tình trạng giao dịch thanh toán "Không thành công"
                                request.setAttribute("message", "transaction failed");
                            }
                        } else {
                            //Trạng thái giao dịch đã được cập nhật trước đó
                            request.setAttribute("message", "transaction failed");
                        }
                    } else {
                        //Số tiền không trùng khớp
                        request.setAttribute("message", "transaction failed");
                    }
                } else {
                    //Mã giao dịch không tồn tại
                    request.setAttribute("message", "successful transaction");
                }
            } else {
                // Sai checksum
                request.setAttribute("message", "transaction failed6");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("payment_ipn.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
