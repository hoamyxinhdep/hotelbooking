<%-- 
    Document   : Booking
    Created on : Mar 11, 2023, 10:05:51 PM
    Author     : DELL-G3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/BookingStyle.css">
<jsp:include page="components/head.jsp"></jsp:include>

<jsp:include page="components/header.jsp"></jsp:include>   
<div class="BookingForm">
    <h1 style="color:#000">Information</h1>

    <form action="viewinfo" method="POST" >

        <label for="customerName">Name:</label>
        <input value="${sessionScope.ACCUSER.name}"type="text"  name="name" required ><br>
        <p style="color: red">${requestScope.USER_ERROR.getNameError()}</p>
            
            <label for="roomType">Email</label>
            <input value="${sessionScope.ACCUSER.email}"type="text"  name="email" required ><br>
            <p style="color: red">${requestScope.USER_ERROR.getEmailError()}</p>
            <label >Phone:</label>
            <input value="${sessionScope.ACCUSER.phoneNumber}"type="text"  name="phone" required ><br>
            <p style="color: red">${requestScope.USER_ERROR.getPhoneNumberError()}</p>
            <label for="roomNumber">User Name:</label>
            <input value="${sessionScope.ACCUSER.userId}"type="text"  name="userId" required readonly=""><br>
             

        <input type="submit" value="Save" style="margin-left: 170px">
        <p class="text-danger">${mess}</p>
    </form>
            
</div>
<!-- Footer Section Begin -->
<jsp:include page="components/footer.jsp"></jsp:include>
<!-- Search model end -->

<!-- Js Plugins -->
<script src="component/js/jquery-3.3.1.min.js"></script>
        <script src="component/js/bootstrap.min.js"></script>
        <script src="component/js/jquery.magnific-popup.min.js"></script>
        <script src="component/js/jquery.nice-select.min.js"></script>
        <script src="component/js/jquery-ui.min.js"></script>
        <script src="component/js/jquery.slicknav.js"></script>
        <script src="component/js/owl.carousel.min.js"></script>
        <script src="component/js/main.js"></script>
