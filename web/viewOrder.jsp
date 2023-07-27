<%-- 
    Document   : viewOrder
    Created on : Oct 17, 2021, 9:26:40 PM
    Author     : Phước Hà
--%>


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
        <title>View Order</title>
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
            <c:if test="${sessionScope.ACC.roleId == 1}">
                <h2 style="text-align: center;">REVENUE MANAGEMENT</h2>
            </c:if>
            <c:if test="${sessionScope.ACC.roleId == 2}">
                <h2 style="text-align: center;">VIEW MY ORDER</h2>
            </c:if>
            <c:if test="${sessionScope.ACC.roleId == 3}">
                <h2 style="text-align: center;">LIST ALL ORDER</h2>
            </c:if>
            <div class="container mt-10 mb-10">

                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <c:if test="${requestScope.CART_MSG != null}">
                                    <p class="right" style="color: #000">${requestScope.CART_MSG}</p>
                                </c:if>
                                <c:if test="${requestScope.CHECKDISCOUNT_MSG != null}">
                                    <p class="right">${requestScope.CHECKDISCOUNT_MSG}</p>
                                </c:if>
                                <c:if test="${sessionScope.ACC.roleId != 1}">
                                    <c:if test="${requestScope.LIST_ORDER != null}">
                                        <table width="100%" border="1" cellspacing="0" cellpadding="0" style="text-align: center;">
                                            <thead>
                                                <tr>
                                                    <th>No.</th>
                                                    <th>Order Id</th>
                                                    <th>UserId</th>
                                                    <th>Order Date</th>
                                                    <th>Total</th>
                                                    <th>Status</th>
                                                    <th width="110" class="ac">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>


                                                <c:forEach var="o" items="${requestScope.LIST_ORDER}" varStatus="counter">
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td><h3><a href="#">${o.orderId}</a></h3></td>

                                                        <td>${o.userId}</td>

                                                        <td>${o.orderDate}</td> 
                                                        <td>${o.total}</td>
                                                        <c:if test="${o.status == 1}">
                                                            <td class='text-warning'>Pending</td>
                                                        </c:if> 
                                                        <c:if test="${o.status == 2}">
                                                            <td class='text-success'>Paid</td>
                                                        </c:if>
                                                        <c:if test="${o.status == 3}">
                                                            <td class='text-danger'>Canceled</td>
                                                        </c:if>
                                                        <c:if test="${o.status == 4}">
                                                            <td class='text-danger'>Checkout</td>
                                                        </c:if>
                                                        <c:if test="${sessionScope.ACC.roleId == 2}">
                                                            <td>
                                                                <a href="MainController?btnAction=viewOrderDetails&orderId=${o.orderId}" class="ico edit">Details</a><br>
                                                                <c:if test="${o.status == 1}">
                                                                    <a href="cancelorder?orderId=${o.orderId}" class="ico edit btn-link"onclick="return confirm('Are you sure cancel?')">Cancel</a><br>
                                                                    <button type="button" class="btn btn-link" data-toggle="modal" data-target="#exampleModal-${counter.count}">
                                                                        Pay
                                                                    </button>
                                                                    <br>
                                                                </c:if>
                                                                <c:if test="${o.status != 1}">
                                                                    <a href="MainController?btnAction=deleteOrder&orderId=${o.orderId}" class="ico del btn-link" onclick="return confirm('Are you sure delete?')">Delete</a>
                                                                </c:if>

                                                                <div class="modal fade" id="exampleModal-${counter.count}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <form action="PaymentServlet" method="post">
                                                                                <div class="modal-header">
                                                                                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                                                                    <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <input type="text" value="${o.orderId}" name="orderId" readonly="">
                                                                                    <input type="number" value="${o.total}" name="amount" readonly="">
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                    <button type="submit" class="btn btn-primary">Pay</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>   
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${sessionScope.ACC.roleId == 3}">
                                                            <td>
                                                                <a href="MainController?btnAction=viewOrderDetails&orderId=${o.orderId}" class="ico edit">Details</a><br>
                                                                <c:if test="${o.status == 1}">
                                                                    <a href="confirmorder?orderId=${o.orderId}" class="ico edit"onclick="return confirm('Are you sure confirm pay?')">Confirm</a><br>
                                                                </c:if>
                                                                <c:if test="${o.status == 2}">
                                                                    <a href="checkoutrecep?orderId=${o.orderId}" class="ico edit"onclick="return confirm('Are you sure?')">Checkout</a><br>
                                                                </c:if>
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${sessionScope.ACC.roleId == 1}">
                                                            <td>
                                                                <a href="MainController?btnAction=viewOrderDetails&orderId=${o.orderId}" class="ico edit">Details</a><br>
                                                            </td>
                                                        </c:if>
                                                        <!-- Modal -->

                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </c:if>

                                <c:if test="${sessionScope.ACC.roleId == 1}">
                                    <c:if test="${requestScope.LIST_ORDER != null}">
                                        <table width="100%" border="1" cellspacing="0" cellpadding="0" style="text-align: center;">
                                            <tr>
                                                <th>No.</th>
                                                <th>Order Id</th>
                                                <th>UserId</th>
                                                <th>Order Date</th>
                                                <th>Total</th>
                                                <th>Status</th>
                                                <th width="110" class="ac">Action</th>
                                            </tr>

                                            <c:forEach var="o" items="${requestScope.LIST_ORDER}" varStatus="count">
                                                <form action="MainController">
                                                    <tr>
                                                        <td>${count.count}</td>
                                                        <td><h3><a href="#">${o.orderId}</a></h3></td>
                                                        <td>${o.userId}</td>
                                                        <td>${o.orderDate}</td> 
                                                        <td>${o.total}<br>

                                                        </td>
                                                             <c:if test="${o.status == 1}">
                                                            <td class='text-warning'>Pending</td>
                                                        </c:if> 
                                                        <c:if test="${o.status == 2}">
                                                            <td class='text-success'>Paid</td>
                                                        </c:if>
                                                        <c:if test="${o.status == 3}">
                                                            <td class='text-danger'>Canceled</td>
                                                        </c:if>
                                                        <c:if test="${o.status == 4}">
                                                            <td class='text-danger'>Checkout</td>
                                                        </c:if>
                                                        <td>
                                                            <a href="MainController?btnAction=viewOrderDetails&orderId=${o.orderId}" class="ico edit">Details</a><br>

                                                        </td>
                                                    </tr>
                                                </form>
                                            </c:forEach>


                                        </table>
                                        <h3>Tổng doanh thu : ${requestScope.TOTAL_REVENUE}</h3>
                                    </c:if>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>

            </div>


        </div>
    </section>
    <!-- Room Details Section End -->
    <jsp:include page="components/footer.jsp"></jsp:include>
    <script type="text/javascript">
//        $("#frmCreateOrder").submit(function () {
//            var postData = $("#frmCreateOrder").serialize();
//            var submitUrl = $("#frmCreateOrder").attr("action");
//            $.ajax({
//                type: "POST",
//                url: submitUrl,
//                data: postData,
//                dataType: 'JSON',
//                success: function (x) {
//                    if (x.code === '00') {
//                        if (window.vnpay) {
//                            vnpay.open({width: 768, height: 600, url: x.data});
//                        } else {
//                            location.href = x.data;
//                        }
//                        return false;
//                    } else {
//                        alert(x.Message);
//                    }
//                }
//            });
//            return false;
//        });
    </script>    
</body>
</html>