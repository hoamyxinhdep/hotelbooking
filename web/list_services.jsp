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

                <div class="container mt-10 mb-10">
                    <h2 class="text-center">List Services</h2>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        Add Service
                    </button>
                    <p class="text-danger">${SERVICE_CHECK}</p>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <form action="AddService" method="post" enctype="multipart/form-data">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Add Services</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body px-4 ">
                                        <div class="row">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input class="form-control" type="text" value="" required="" name="name">
                                                
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label>Price</label>
                                                <input class="form-control" type="number" value="" required="" name="price">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label>Image</label>
                                                <input class="form-control" type="file" value="" required="" name="photo">
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Update</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <table class="table table-striped text-center">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Image</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach varStatus="counter" var="service" items="${requestScope.services}">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${service.name}</td>
                                            <td>${service.price}</td>
                                            <td>
                                                <img width="50px" height="50px" src="img/services/${service.image}" alt="alt"/>
                                            </td>
                                            <td>
                                                <!-- Button trigger modal -->
                                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal-${counter.count}">
                                                    Update
                                                </button>
                                                    <a href="deleteService?id=${service.id}" onclick="return confirm('Are you sure delete?')"type="button" class="btn btn-danger">
                                                    Delete
                                                </a>
                                                <!-- Modal -->
                                                <div class="modal fade" id="exampleModal-${counter.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <form action="UpdateService" method="post" enctype="multipart/form-data">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel">Update Service</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body px-4 ">
                                                                    <div class="row">
                                                                        <div class="form-group">
                                                                            <label>Name</label>
                                                                            <input class="form-control" type="text" value="${service.name}" name="name">
                                                                            <input class="form-control" type="hidden" value="${service.id}" name="serviceId">
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="form-group">
                                                                            <label>Price</label>
                                                                            <input class="form-control" type="number" value="${service.price}" name="price">
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="form-group">
                                                                            <label>Image</label>
                                                                            <input class="form-control" type="file" value="" name="photo">
                                                                        </div>
                                                                    </div>

                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                        <button type="submit" class="btn btn-primary">Update</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table> 


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
