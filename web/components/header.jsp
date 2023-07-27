<!-- Header Section Begin -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header-section header-normal">
    <div class="top-nav">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <ul class="tn-left">
                        <c:if test="${sessionScope.ACCUSER == null}">
                            <li><i class="fa fa-phone"></i></li>
                            <li><i class="fa fa-envelope"></i></li>
                            </c:if>


                        <c:if test="${sessionScope.ACCUSER != null}">
                            <li><i class="fa fa-phone mt-4"></i>${sessionScope.ACCUSER.phoneNumber}</li>
                            <li><i class="fa fa-envelope mt-4"></i>${sessionScope.ACCUSER.email}</li>
                            </c:if>
                    </ul>
                </div>
                <div class="col-lg-6">
                    <div class="tn-right">
                        <div class="top-social">
                            <c:if test="${sessionScope.ACC != null}">
                                <a href="#"><i class="fa""></i>Hello ${sessionScope.ACCUSER.name}</a>
                                <a class="bk-btn" href="MainController?btnAction=logout" >Logout</a>
                            </c:if>
                            <c:if test="${sessionScope.ACC == null}">
                                <a href="MainController?btnAction=loginPage" class="bk-btn">Login</a>
                            </c:if>
                        </div>


                      
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="menu-item">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <div class="logo">
                        <a href="HomeController">
                            <img src="img/logo.png" alt="">
                        </a>
                    </div>
                </div>
                <div class="col-lg-10">
                    <div class="nav-menu">
                        <nav class="mainmenu">
                            <ul>
                                <li><a href="HomeController">Home</a></li>
                                <li><a href="ListAllRoom">Rooms</a></li>
                                <li><a href="about-us.jsp">About Us</a></li>

                                <li><a href="contact.jsp">Contact</a></li>
                                    <c:if test="${sessionScope.ACC.roleId == 2}">


                                    <li><a href="#">Users</a>
                                        <ul class="dropdown " style="width: 195px;">
                                            <li><a href="MainController?btnAction=ViewCart"class="active" >View Cart</a></li>
                                            <li><a href="MainController?btnAction=ViewOrder">View Order</a></li>
                                            <li><a href="information.jsp">View Information</a></li>
                                            <li><a href="changePassword.jsp">Change Password</a></li>

                                        </ul>
                                    </li>
                                </c:if>
                                <c:if test="${sessionScope.ACC.roleId == 1}">


                                    <li><a href="#">Admin</a>
                                        <ul class="dropdown " style="width: 195px;">
                                            <li><a href="loadAccount"class="active" >User</a></li>
                                            <li><a href="LoadRoomAdminServlet">Room</a></li>
                                            
                                            <li><a href="loadorderbystatus">Reservation</a></li>
                                            <li><a href="LoadServices">Service</a></li>
                                            <li><a href="information.jsp">View Information</a></li>
                                            <li><a href="changePassword.jsp">Change Password</a></li>
                                        </ul>
                                    </li>
                                </c:if>
                                <c:if test="${sessionScope.ACC.roleId == 3}">


                                    <li><a href="#">Receptionist</a>
                                        <ul class="dropdown " style="">

                                            <li><a href="loadallorder">View Order</a></li>
                                            <li><a href="information.jsp">View Information</a></li>
                                            <li><a href="changePassword.jsp">Change Password</a></li>

                                        </ul>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                        <div class="nav-right search-switch">
                            <i class="icon_search"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Header End -->