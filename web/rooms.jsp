<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <jsp:include page="components/head.jsp"></jsp:include>
        </head>
        <body>
        <jsp:include page="components/header.jsp"></jsp:include>   
         <!-- Breadcrumb Section Begin -->
    <div class="breadcrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <h2>Rooms</h2>
                        <div class="bt-option">
                            <a href="HomeController">Home</a>
                            <span>Rooms</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Section End -->
            <!-- Rooms Section Begin -->
            <section class="rooms-section spad">
                <div class="container">
                    <div class="row">
                        &nbsp;
                    </div>
                    <div class="row  mt-10 mb-10">
                        <h4>Search for: ${param.searchValue}</h4>
                </div>
                <div class="row">
                    &nbsp;
                </div>
                <div class="row">
                    <c:if test="${requestScope.rooms.size() <= 0}">
                        <p class="text-danger">Not found Room</p>
                    </c:if>
                    <c:forEach var="r" items="${requestScope.rooms}">
                        <div class="col-lg-4 col-md-6">
                            <div class="room-item">
                                <img src="${r.roomTypesDTO.image}" alt="">
                                <div class="ri-text">
                                    <h4>${r.roomName}</h4>
                                    <h3>${r.price}$<span>/Pernight</span></h3>
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td class="r-o">Type:</td>
                                                <td>${r.roomTypesDTO.typeName}</td>
                                            </tr>
                                            <tr>
                                                <td class="r-o">Max persion:</td>
                                                <td>${r.roomTypesDTO.maxPeople}</td>
                                            </tr>
                                            <tr>
                                                <td class="r-o">Bed:</td>
                                                <td>${r.roomTypesDTO.bed}</td>
                                            </tr>
                                            <tr>
                                                <td class="r-o">Services:</td>
                                                <td>
                                                    ${r.roomTypesDTO.utilities}
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <a href="ViewDetailRoom?roomNo=${r.roomNo}" class="primary-btn">More Details</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="room-pagination">
                            <c:forEach begin="1" end="${endP}" var="i">
                                <a style="${tag == i?"background-color:#EFD4B9;":" "} "href="ListAllRoom?index=${i}">${i}</a>
                            </c:forEach>
       
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Rooms Section End -->

       
        <!-- Blog Section End -->
        <jsp:include page="components/footer.jsp"></jsp:include>
    </body>
</html>
