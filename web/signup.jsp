<!DOCTYPE html>
<!-- Designined by CodingLab - youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <title> Responsive Registration Form | CodingLab </title>
        <link rel="stylesheet" href="css/1style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="container">
            <div class="title" style="text-align: center ">Registration</div>
            <div class="content">
                <form action="MainController" method="POST">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details form-label"for="exampleInputPassword1" >Full Name<span style="color: red;">*</span> </span>
                            <input type="text" placeholder="Enter your name" name="fullName" class="form-control" id="exampleInputPassword1"required>
                            <p style="color: red">${requestScope.USER_ERROR.getNameError()}</p>

                        </div>
                        <div class="input-box">
                            <span class="details">Phone Number<span style="color: red;">*</span></span>
                            <input type="text" placeholder="Enter your number" name="phoneNumber" class="form-control" id="exampleInputPassword1"required>
                            <p style="color: red">${requestScope.USER_ERROR.getPhoneNumberError()}</p>
                        </div>

                        <div class="input-box">
                            <span class="details">Email<span style="color: red;">*</span></span>
                            <input type="text" placeholder="Enter your email" name="email" required>
                            <p style="color: red">${requestScope.USER_ERROR.getEmailError()}</p>
                        </div>
                        <div class="input-box">
                            <span class="details form-label" for="exampleInputEmail1">Username<span style="color: red;">*</span></span>
                            <input type="text" name="userId" class="form-control" id="exampleInputEmail1"placeholder="Enter your username" required>
                            <p style="color: red">${requestScope.USER_ERROR.getUserIDError()}</p>
                        </div>
                        <div class="input-box form-label">
                            <span class="details">Password<span style="color: red;">*</span></span>
                            <input type="password" placeholder="Enter your password" name="Password" class="form-control" id="exampleInputPassword1"required>
                            <p style="color: red">${requestScope.USER_ERROR.getPasswordError()}</p>
                        </div>
                        <div class="input-box">
                            <span class="details">Confirm Password <span style="color: red;">*</span></span>
                            <input type="password" placeholder="Confirm your password"name="rePassword" class="form-control" id="exampleInputPassword1" required>
                            <p style="color: red">${requestScope.USER_ERROR.getConfirmPasswordError()}</p>
                        </div>
                    </div>

                 
                        <button type="submit" name ="btnAction" value="CreateServlet" class="buttonsignup btn btn-primary">REGISTER</button>
                    
                </form>
            </div>
        </div>

    </body>
</html>
