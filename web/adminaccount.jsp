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
                            <h2>List <b>All Account</b></h2>
                            <p class="text-danger">${mess}</p>

                        </div>
                        <table class="table ">
                            <thead>
                                <tr>

                                    <th>User Name</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>  
                                    <th>Role</th>
                                    <th>Status</th>
                                    <th>Action<th>


                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="acc" items="${LIST_Account}" varStatus="counter">


                                    <tr>
                                        <td>${acc.userId}</td>
                                        <td>${acc.name}</td>
                                        <td>${acc.email}</td>
                                        <td>${acc.phone}</td>
                                        <c:if test="${acc.roleId == 1}">
                                            <td>Admin</td>
                                        </c:if>
                                        <c:if test="${acc.roleId == 2}">
                                            <td>Customer</td>
                                        </c:if>
                                        <c:if test="${acc.roleId == 3}">
                                            <td>Receptionist</td>
                                        </c:if>
                                        <td>${acc.status}</td>
                                        <c:if test="${acc.roleId != 1}">
                                            <td>
                                                <div id="setroleaccount-${counter.count}" class="modal fade "tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog" style="color: black;">
                                                        <div class="modal-content">
                                                            <form action="setrole" method="post">
                                                                <div class="modal-header">						
                                                                    <h4 class="modal-title"id="exampleModalLabel">Set role</h4>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                                </div>
                                                                <div class="modal-body">

                                                                    <div class="form-group">
                                                                        User ID
                                                                        <input name="userId" value="${acc.userId}"type="text" class="form-control" required readonly="">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        Name
                                                                        <input name="name" value="${acc.name}"type="text" class="form-control" required readonly="">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        Email
                                                                        <input name="email" value="${acc.email}"type="text" class="form-control" required readonly="">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>Role</label><br>
                                                                        <select name="role" class="form-select" aria-label="Default select example" style="width: 100%; height: 30px;">

                                                                            <option value="2">Customer</option>
                                                                            <option value="1">Admin</option>
                                                                            <option value="3">Receptionist</option><!-- comment -->

                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer ">
                                                                    <input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancel">
                                                                    <input type="submit" class="btn btn-success" value="Add">
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                                <a href="#setroleaccount-${counter.count}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Set Role">&#xE254;</i></a>
                                            </td>
                                        </c:if>

                                    </tr>


                                </c:forEach>

                            </tbody>
                        </table>
                        <div class="clearfix">
                            <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                            <ul class="pagination">
                                
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




                <!-- Delete Modal HTML -->

                </a>
                <script src="js/manager.js" type="text/javascript"></script>
                </body>

                </html>