<%-- 
    Document   : viewCart
    Created on : Oct 17, 2021, 2:12:23 AM
    Author     : Phước Hà
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <jsp:include page="components/head.jsp"></jsp:include>
            <style>
                .table-container {
                    width: 100%; /* Set the desired width for the scrollable area */
                    overflow-x: auto; /* Enable horizontal scrolling */
                }

                .table-container table {
                    width: max-content; /* Expand the table to accommodate all columns */
                }
            </style>
        </head>
        <body>
        <jsp:include page="components/header.jsp"></jsp:include>   
            <!-- Room Details Section Begin -->
            <section class="room-details-section spad">
                <div class="container mt-10 mb-10">

                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-12">
                                <c:if test="${requestScope.CART_MSG != null}">
                                    <p class="right text-danger">${requestScope.CART_MSG}</p>
                                </c:if>
                                <c:if test="${requestScope.CHECKDISCOUNT_MSG != null}">
                                    <p class="right text-danger">${requestScope.CHECKDISCOUNT_MSG}</p>
                                </c:if>
                                <c:if test="${sessionScope.LIST_CART != null}">
                                    <div class="table table-striped">
                                        <table width="100%" border="1" cellspacing="0" cellpadding="0" style="text-align: center;">
                                            <tr>
                                                <th style="width: 5%" class="text-nowrap">No.</th>
                                                <th style="width: 5%" class="text-nowrap">Room Name</th>
                                                <th style="width: 5%" class="text-nowrap">Image</th>
                                                <th style="width: 5%" class="text-nowrap">Quantity</th>
                                                <th style="width: 10%; margin-left: 5px" class="text-nowrap">Price</th>
                                                <th style="width: 20%" class="text-nowrap">Check In</th>
                                                <th style="width: 20%" class="text-nowrap">Check Out</th>
                                                <th class="text-nowrap">Night</th>
                                                <th  style="width: 10%" class="text-nowrap">Total Price</th>
                                                <th  style="width: 20%" class="text-nowrap">Action</th>
                                            </tr>
                                            <c:forEach var="r" items="${sessionScope.LIST_CART}" varStatus="count">
                                                <form action="MainController">
                                                    <tr>
                                                        <td>${count.count}</td>
                                                        <td>${r.roomName}</td>
                                                        <td><img src="img/room/room-1.jpg" width="50px" height="50px" alt="#"></td>
                                                        <td>
                                                            <input type="number" class="" style="width: 50px" name="quantity" value="${r.quantity}" readonly="">
                                                        </td> 
                                                        <td> ${r.price}</td>
                                                        <td>
                                                            <input type="text" class="form-control" name="checkIn" value="${r.checkInDate}" readonly="">
                                                        </td>
                                                        <td>
                                                            <input type="text"  class="form-control"  name="checkOut" value="${r.checkOutDate}" readonly="">
                                                        </td>
                                                        <td>
                                                            <input type="hidden" name="roomNo" value="${r.roomNo}"/>
                                                            <input type="text"  class="form-control"  name="night" value="${r.night} night" readonly="">
                                                           
                                                        </td>
                                                        <td>${r.total}</td>
                                                        <td class="align-content-center justify-content-center">
                                                            <a href="MainController?btnAction=deleteCart&roomNo=${r.roomNo}" class="btn btn-danger" onclick="return confirm('Are you sure delete?')">Delete</a>
                                                           
                                                        </td>
                                                    </tr>
                                                </form>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-sm-12">

                        <div class="row">
                            <div class="col-sm-4">
                                <div class="card">
                                    <div class="card-body">
                                        <form action="add-service-cart" method="post">
                                            
                                                <table border="1" style="width: 100%;text-align: center;" cellspacing="0" cellpadding="0" >
                                                   
                                                    <tr >
                                                        <th style="width: 40px;"></th>
                                                            <th>Name</th>
                                                            <th>Price</th>
                                                            <th>Image</th>
                                                        </tr>
                                                     <c:forEach var="service" items="${requestScope.services}">
                                                        <tr>
                                                            <td>  
                                                                <c:set var="isChecked" value="false" />
                                                                <c:forEach var="checkedService" items="${sessionScope.LIST_SERVICE_CHECKED}">
                                                                    <c:if test="${checkedService.id eq service.id}">
                                                                        <c:set var="isChecked" value="true" />
                                                                    </c:if>
                                                                </c:forEach>
                                                                <input type="checkbox" <c:if test="${isChecked}">checked</c:if> name="selectedServices" value="${service.id}">
                                                            </td>
                                                            <td>
                                                                <span>&nbsp;${service.name}</span>
                                                            </td>
                                                            <td>
                                                                <span>&nbsp;${service.price}</span>
                                                            </td>
                                                            <td>
                                                                <img width="50px" height="50px" src="img/services/${service.image}" alt="#">
                                                            </td>
                                                        </tr>


                                                
                                               
                                            </c:forEach>
                                                        </table>
                                            <div class="d-flex justify-content-center">
                                                <button style="margin-top: 10px;"type="submit" value="addservice" class="btn btn-primary bt">Add Service</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card">
                                    <div class="card-body">
                                        <form action="MainController" method="post">
                                            <c:choose>
                                                <c:when test="${sessionScope.DISCOUNT_CODE == null}">
                                                    <c:if test="${requestScope.CHECKDISCOUNT_MSG != null}">
                                                        <p style="color: red">${requestScope.CHECKDISCOUNT_MSG}</p>
                                                    </c:if>

                                                    <div class="form-group">
                                                        <label>Discount Code: </label>
                                                        <input type="text" name="discountCode" value="" class="form-control"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <button name="btnAction" value="checkDiscountCode" class="btn btn-warning" >Check</button>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="form-group">
                                                        <label>Discount Code:</label>
                                                        <input type="text" name="discountCode" value="${sessionScope.DISCOUNT_CODE.discountCode}" class="field small-field"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <button name="btnAction" value="changeDiscount" class="btn btn-warning" >Change DisCount</button>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <div class="card">
                                    <div class="card-body">
                                        <!-- Date Picker -->
                                        <div class="form-group">
                                            <label class="form-label">Total Price</label>
                                            <input type="text" name="totalPrice" value="${sessionScope.TOTAL}" class="form-control"  readonly=""/>
                                        </div>
                                        <!-- End Sort -->
                                        <form action="CheckOutServlet">
                                            <div class="form-group">
                                                <button name="btnAction" value="checkOut" class="btn btn-success">Check Out</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>


        </div>
    </section>
    <!-- Room Details Section End -->
    <jsp:include page="components/footer.jsp"></jsp:include>
</body>

</html>