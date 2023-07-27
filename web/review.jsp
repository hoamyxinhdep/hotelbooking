<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Transaction Details</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }
        </style>
        <jsp:include page="components/head.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="components/header.jsp"></jsp:include>   
            <section class="room-details-section spad">

                <div class="container d-flex justify-content-center text-center">
                    <div class="card">
                        <div class="card-body">

                            <h3 style="text-align: center;">Transaction Details</h3>

                            <div class="row">
                                <form action="CompletePay" method="POST">
                                    <input type="hidden" name="amount" value="${requestScope.transaction.amount.total}"/>
                                <input type="hidden" name="sku" value="${requestScope.transaction.itemList.items.get(0).sku}"/>
                                <p><strong>Description:</strong> ${requestScope.transaction.description}</p>
                                <c:if test="${sessionScope.DISCOUNT_CODE == null }">
                                    <p><strong>Amount:</strong> ${requestScope.transaction.amount.total} ${requestScope.transaction.amount.currency}</p>
                                        <h3>Items</h3>
                                <table>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                    </tr>
                                    <c:forEach var="item" items="${requestScope.transaction.itemList.items}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${item.name}</td>
                                            <td>${item.price} ${item.currency}</td>
                                            <td>${item.quantity}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                </c:if>
                                <c:if test="${sessionScope.DISCOUNT_CODE != null }">
                                  
                                
                                <h3>Items</h3>
                                <table>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                    </tr>
                                    <c:forEach var="item" items="${requestScope.transaction.itemList.items}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${item.name}</td>
                                            <td>${item.price} ${item.currency}</td>
                                            <td>${item.quantity}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <br>
                                  <c:set var="attribute1" value="${requestScope.transaction.amount.total}" />
                                    <c:set var="attribute2" value="${requestScope.transaction.amount.total*10/100}" />
                                    <c:set var="difference" value="${attribute1 - attribute2}" />
                                    <p><strong>Amount:</strong> ${difference} ${requestScope.transaction.amount.currency}</p>
                                    <p class="text-danger"><strong>Discount: 10 % </strong> </p>
                                </c:if>
                                <hr>

                                <input type="submit" value="Pay Now" class="btn btn-primary"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


        </section>

    </body>
</html>
