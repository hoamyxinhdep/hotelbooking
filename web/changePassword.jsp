<%-- 
    Document   : Booking
    Created on : Mar 11, 2023, 10:05:51 PM
    Author     : DELL-G3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href="css/BookingStyle.css" />
<link type="text/css" rel="stylesheet" href="css/style.css" />

<jsp:include page="components/head.jsp"></jsp:include>


<jsp:include page="components/header.jsp"></jsp:include>   
<div class="BookingForm">
    <h1 style="color:#000">Information</h1>

    <form action="changePass" method="POST" >
        
        <input value="${sessionScope.ACCUSER.userId}"type="hidden"  name="userId" required readonly=""><br>

        <label for="customerName">Password</label>
        <input style="width: 100%;padding: 10px;border: 1px solid #ccc;border-radius: 3px;font-size: 1em;margin-bottom: 20px;"
               value=""type="password"  name="password" placeholder="Enter your password"required ><br>
        <p style="color: red">${requestScope.USER_ERROR.getNameError()}</p>
        <label for="customerName">New Password</label>
        <input style="width: 100%;padding: 10px;border: 1px solid #ccc;border-radius: 3px;font-size: 1em;margin-bottom: 20px;"
            value=""type="password"  name="newpassword" placeholder="Enter your new password"required ><br>
         <p style="color: red">${requestScope.USER_ERROR.getPasswordError()}</p>
        
        
        <label for="customerName">Confilm New Password</label>
        <input style="width: 100%;padding: 10px;border: 1px solid #ccc;border-radius: 3px;font-size: 1em;margin-bottom: 20px;"
            value=""type="password"  name="confpassword" placeholder="Enter your new password again"required ><br>
            <p style="color: red">${requestScope.USER_ERROR.getConfirmPasswordError()}</p>
          
        <p class="text-danger">${mess}</p>
        <input type="submit" value="Save" style="margin-left: 170px">
        
    </form>
            
</div>
<!-- Footer Section Begin -->
<jsp:include page="components/footer.jsp"></jsp:include>
<!-- Search model end -->

<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>

