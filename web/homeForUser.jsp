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
            <!-- Hero Section Begin -->
            <section class="hero-section">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="hero-text">
                                <h1>Sona A Luxury Hotel</h1>
                                <p>Here are the best hotel booking sites, including recommendations for international
                                    travel and for finding low-priced hotel rooms.</p>
                                <a href="#" class="primary-btn">Discover Now</a>
                            </div>
                        </div>
                    <c:if test="${sessionScope.ACC.roleId == 2}">
                        <div class="col-xl-4 col-lg-5 offset-xl-2 offset-lg-1">
                            <div class="booking-form">
                                <h3>Booking Your Hotel</h3>
                                <c:if test="${requestScope.SEARCH_MSG != null}">
                                    <h3 class="text-danger">${requestScope.SEARCH_MSG}</h3>
                                </c:if>
                                <form action="MainController">
                                    <div class="form-group">

                                        <input type="hidden" name="searchValue" class="form-control" value="${sessionScope.SEARCH_VALUE}" />
                                    </div>
                                    <div class="check-date">
                                        <label for="date-in">Check In:</label>
                                        <input name="checkIn" autocomplete="off" type="date" class="" id="">
                                    </div>
                                    <div class="check-date">
                                        <label for="date-out">Check Out:</label>
                                        <input name="checkOut" autocomplete="off"  type="date" class="" id="">
                                    </div>
                                    
                                    <div class="select-option">
                                        
                                        <input name="avaiRoom" autocomplete="off"  type="hidden" class="" id="" value="1">
                                      
                                    </div>
                                    <button value="SearchHotel" name="btnAction" type="submit">Check Availability</button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="hero-slider owl-carousel">
                <div class="hs-item set-bg" data-setbg="img/hero/hero-1.jpg"></div>
                <div class="hs-item set-bg" data-setbg="img/hero/hero-2.jpg"></div>
                <div class="hs-item set-bg" data-setbg="img/hero/hero-3.jpg"></div>
            </div>
        </section>
        <!-- Hero Section End -->

        <!-- About Us Section Begin -->
        <section class="aboutus-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="about-text">
                            <div class="section-title">
                                <span>About Us</span>
                                <h2>Intercontinental LA <br />Westlake Hotel</h2>
                            </div>
                            <p class="f-para">Sona.com is a leading online accommodation site. We’re passionate about
                                travel. Every day, we inspire and reach millions of travelers across 90 local websites in 41
                                languages.</p>
                            <p class="s-para">So when it comes to booking the perfect hotel, vacation rental, resort,
                                apartment, guest house, or tree house, we’ve got you covered.</p>
                            <a href="#" class="primary-btn about-btn">Read More</a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="about-pic">
                            <div class="row">
                                <div class="col-sm-6">
                                    <img src="img/about/about-1.jpg" alt="">
                                </div>
                                <div class="col-sm-6">
                                    <img src="img/about/about-2.jpg" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- About Us Section End -->

        <!-- Services Section End -->
        <section class="services-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <span>What We Do</span>
                            <h2>Discover Our Services</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-sm-6">
                        <div class="service-item">
                            <i class="flaticon-036-parking"></i>
                            <h4>Travel Plan</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="service-item">
                            <i class="flaticon-033-dinner"></i>
                            <h4>Catering Service</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="service-item">
                            <i class="flaticon-026-bed"></i>
                            <h4>Babysitting</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="service-item">
                            <i class="flaticon-024-towel"></i>
                            <h4>Laundry</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="service-item">
                            <i class="flaticon-044-clock-1"></i>
                            <h4>Hire Driver</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna.</p>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="service-item">
                            <i class="flaticon-012-cocktail"></i>
                            <h4>Bar & Drink</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Services Section End -->

        <!-- Home Room Section Begin -->
        <section class="hp-room-section">
            <div class="container-fluid">
                <div class="hp-room-items">
                    <div class="row">
                        <c:forEach var="r" items="${requestScope.ROOMS}" varStatus="status" >
                            <c:if test="${status.count <= 4}">
                                <div class="col-lg-3 col-md-6">
                                    <div class="hp-room-item set-bg img-fluid" data-setbg="img/room/room-b2.jpg">
                                        <div class="hr-text">
                                            <h3>${r.roomName}</h3>
                                            <h2>${r.price}$<span>/Pernight</span></h2>
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


                                                        <td>${r.roomTypesDTO.utilities}</td>  


                                                    </tr>
                                                </tbody>
                                            </table>
                                            <a href="ViewDetailRoom?roomNo=${r.roomNo}" class="primary-btn">More Details</a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <!-- Home Room Section End -->

        <!-- Testimonial Section Begin -->
        <section class="testimonial-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <span>Testimonials</span>
                            <h2>What Customers Say?</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="testimonial-slider owl-carousel">
                            <c:forEach var="feed" items="${requestScope.FEED_BACKS}">


                                <div class="ts-item">
                                    <p>${feed.description}.</p>
                                    <div class="ti-author">
                                        <div class="rating">
                                            <c:forEach begin="1" end="${feed.value}">
                                                <i class="icon_star"></i>
                                            </c:forEach>
                                        </div>
                                        <h5> - ${feed.userDTO.name}</h5>
                                    </div>
                                    <img src="img/testimonial-logo.png" alt="">
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Testimonial Section End -->

        <!-- Blog Section Begin -->
        <section class="blog-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <span>Hotel News</span>
                            <h2>Our Blog & Event</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <div class="blog-item set-bg" data-setbg="img/blog/blog-1.jpg">
                            <div class="bi-text">
                                <span class="b-tag">Travel Trip</span>
                                <h4><a href="#">Tremblant In Canada</a></h4>
                                <div class="b-time"><i class="icon_clock_alt"></i> 15th April, 2019</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="blog-item set-bg" data-setbg="img/blog/blog-2.jpg">
                            <div class="bi-text">
                                <span class="b-tag">Camping</span>
                                <h4><a href="#">Choosing A Static Caravan</a></h4>
                                <div class="b-time"><i class="icon_clock_alt"></i> 15th April, 2019</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="blog-item set-bg" data-setbg="img/blog/blog-3.jpg">
                            <div class="bi-text">
                                <span class="b-tag">Event</span>
                                <h4><a href="#">Copper Canyon</a></h4>
                                <div class="b-time"><i class="icon_clock_alt"></i> 21th April, 2019</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="blog-item small-size set-bg" data-setbg="img/blog/blog-wide.jpg">
                            <div class="bi-text">
                                <span class="b-tag">Event</span>
                                <h4><a href="#">Trip To Iqaluit In Nunavut A Canadian Arctic City</a></h4>
                                <div class="b-time"><i class="icon_clock_alt"></i> 08th April, 2019</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="blog-item small-size set-bg" data-setbg="img/blog/blog-10.jpg">
                            <div class="bi-text">
                                <span class="b-tag">Travel</span>
                                <h4><a href="#">Traveling To Barcelona</a></h4>
                                <div class="b-time"><i class="icon_clock_alt"></i> 12th April, 2019</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Section End -->
        <jsp:include page="components/footer.jsp"></jsp:include>
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
