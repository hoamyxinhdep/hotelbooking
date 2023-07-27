<%-- 
    Document   : contact
    Created on : Mar 6, 2023, 11:10:20 AM
    Author     : Admin
--%>


<%-- 
    Document   : contact
    Created on : Mar 6, 2023, 11:10:20 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Sona Template">
    <meta name="keywords" content="Sona, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sona</title>

    <jsp:include page="components/head.jsp"></jsp:include>  
</head>

<body>
   <jsp:include page="components/header.jsp"></jsp:include>   
  
    <!-- Header End -->
     <!-- Breadcrumb Section Begin -->
    <div class="breadcrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <h2>Contact</h2>
                        <div class="bt-option">
                            <a href="HomeController">Home</a>
                            <span>Contact</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section End -->

    <!-- Contact Section Begin -->
    <section class="contact-section spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="contact-text">
                        <h2>Contact Info</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua.</p>
                        <table>
                            <tbody>
                                <tr>
                                    <td class="c-o">Address:</td>
                                    <td>Khu đô thị FPT City, Đà Nẵng</td>
                                </tr>
                                <tr>
                                    <td class="c-o">Phone:</td>
                                    <td>(12) 345 67890</td>
                                </tr>
                                <tr>
                                    <td class="c-o">Email:</td>
                                    <td>hotelbookingsystem.01@gmail.com</td>
                                </tr>
                                <tr>
                                    <td class="c-o">Fax:</td>
                                    <td>+(12) 345 67890</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
             
            </div>
            <div class="map">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3835.863822377689!2d108.25798187583013!3d15.968486342125605!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142116949840599%3A0x365b35580f52e8d5!2zxJDhuqFpIGjhu41jIEZQVCDEkMOgIE7hurVuZw!5e0!3m2!1svi!2s!4v1684586015554!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->

    <!-- Footer Section Begin -->
<jsp:include page="components/footer.jsp"></jsp:include>
    <!-- Footer Section End -->

    <!-- Search model Begin -->
  
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
</body>

</html>