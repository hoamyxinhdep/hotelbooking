<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="components/css/manager.css" rel="stylesheet" type="text/css"/>
        <!--         <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">-->
        <!--        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">-->
        <link rel="stylesheet" href="components/css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="components/css/flaticon.css" type="text/css">
        <link rel="stylesheet" href="components/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="components/css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="components/css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="components/css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="components/css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="components/css/style_2.css" type="text/css">


        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <header class="header-section">
            <div class="top-nav">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <ul class="tn-left">
                                <li><i class="fa fa-phone"></i> ${sessionScope.ACCUSER.phoneNumber}</li>
                                <li><i class="fa fa-envelope"></i> ${sessionScope.ACCUSER.email}</li>
                            </ul>
                        </div>
                        <div class="col-lg-6">
                            <div class="tn-right">


                                <c:if test="${sessionScope.ACC != null}">
                                    <a href="#"><i class="fa""></i>Hello ${sessionScope.ACCUSER.name}</a>
                                    <a class="bk-btn" href="MainController?btnAction=logout" >Logout</a>
                                </c:if>
                                <c:if test="${sessionScope.ACC == null}">
                                    <a href="MainController?btnAction=loginPage" class="bk-btn">Login</a>
                                </c:if>
                                <div class="language-option">
                                    <img src="img/flag.jpg" alt="">
                                    <span>EN <i class="fa fa-angle-down"></i></span>
                                    <div class="flag-dropdown">
                                        <ul>
                                            <li><a href="#">Zi</a></li>
                                            <li><a href="#">Fr</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>List <b>All Room</b></h2>
                        </div>
                        
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Room</span></a>
                            <a href="#addNewHotel"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Hotel</span></a>
                            <a href="#addRoomType"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add Room Type</span></a>


                            <!--                                                       <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						-->
                        </div>
                    </div>
                </div>
                <p class="text-danger text-center" style="font-size: 30px;">${ROOM_CHECK}</p>
                <p class="text-danger text-center" style="font-size: 30px;">${RT_CHECK}</p>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>Hotel Name</th>
                            <th>Type ID</th>

                            <th>Name</th>
                            <th>Image</th>

                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="room" items="${LIST_ROOM}">
                            <c:if test="${room.hotelId != null}">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>${room.hotelName}</td>
                                    <td>${room.typeId} </td>
                                    <td>${room.roomName}</td>
                                    <td>
                                        <img src="img/room/room-1.jpg">
                                    </td>
                                    <td>${room.quantity} </td>

                                    <td>${room.price} </td>




                                    <td>
                                        <a href="loadroom?roomNo=${room.roomNo}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="deleteRoom?roomNo=${room.roomNo}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                        <p class="text-danger">${mess1}</p>
                                        <!--                                    <a href="Detail.jsp" class="delete" ><i class="material-icons" data-toggle="tooltip" title="Details">&#8756;</i></a>-->
                                    </td>
                                </tr>
                            </c:if>



                        </c:forEach>

                    </tbody>
                </table>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>1</b> out of <b>25</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                        <li class="page-item active"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item "><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
            <a href="HomeController"><button type="button" class="btn btn-primary">Back to home</button> </a>

        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="addroom" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Room</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">

                            <div class="form-group">
                                Room Name
                                <input name="roomName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                Number Room
                                <input name="quantity" type="number" class="form-control" required>
                            </div><!-- comment -->
                            <div class="form-group">
                                Price
                                <input name="price" type="number" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Room Types: </label><br>
                                 
                                <select name="typeId" class="form-select" aria-label="Default select example" style="width: 100%; height: 30px;">
                                    <c:forEach var="roomt" items="${LIST_ROOMTYPES}">
                                    <option value="${roomt.typeId}">${roomt.typeName}</option>
                                    </c:forEach>
                                </select>
                                 
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="addNewHotel" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="addhotel" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Hotel</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Hotel Name</label>
                                <input name="hotelName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Hotel Address</label>
                                <input name="hotelAdress" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Available</label>
                                <input name="avail" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <input name="status" type="text" class="form-control" required>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="addRoomType" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="addroomtype" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add RoomType</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Type Name</label>
                                <input name="typeName" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <input name="description" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>MaxPeople</label>
                                <input name="maxPeople" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Utilities</label>
                                <input name="utilities" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Bed</label>
                                <input name="bed" type="text" class="form-control" required>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->

        <!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <p>Are you sure you want to delete these Records?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </a>
    <script src="js/manager.js" type="text/javascript"></script>
</body>

</html>