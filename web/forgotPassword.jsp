<!doctype html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <title>Sona- Forgot Password</title>
        <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>
        <link href='' rel='stylesheet'>
        <script type='text/javascript'
        src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script type='text/javascript' src=''></script>
        <script type='text/javascript' src=''></script>
        <script type='text/Javascript'></script>
        <!------ Include the above in your HEAD tag ---------->

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <style>

            *{
                margin: 0;
                padding: 0;
                outline: none;
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }
            body{
                height: 100vh;
                width: 100%;
                background: linear-gradient(115deg, #56d8e4 10%, #9f01ea 90%);
            }
            body {
                background-position: center;
                background-color: #eee;
                background-repeat: no-repeat;
                background-size: cover;
                color: #505050;
                font-family: "Rubik", Helvetica, Arial, sans-serif;
                font-size: 14px;
                font-weight: normal;
                line-height: 1.5;
                text-transform: none
            }


            .forgot {
                background-color: #fff;
                padding: 12px;
                border: 1px solid #dfdfdf
            }

            .padding-bottom-3x {
                padding-bottom: 72px !important
            }

            .card-footer {
                background-color: #fff
            }

            .btn {
                font-size: 13px
            }

            .form-control:focus {
                color: #495057;
                background-color: #fff;
                border-color: #76b7e9;
                outline: 0;
                box-shadow: 0 0 0 0px #28a745
            }
            .emaill{
                margin-left: 150px;
            }
        </style>
    </head>
    <body>
        <div class="container-login100" ">
            <div class="container padding-bottom-3x mb-2 mt-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10">
                        <div class="forgot text-center">
                            <h2>Forgot your password?</h2>
                            <p>Change your password in three easy steps. This will help you
                                to secure your password!</p>
                            <ol class="list-unstyled">
                                <li><span class="text-primary text-medium">1. </span>Enter
                                    your email address below.</li>
                                <li><span class="text-primary text-medium">2. </span>Our
                                    system will send you an OTP to your email</li>
                                <li><span class="text-primary text-medium">3. </span>Enter the OTP on the 
                                    next page</li>
                            </ol>
                        </div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-4 mt-5 emaill" >
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="text-center">
                                                <h3><i class="fa fa-lock fa-4x"></i></h3>
                                                <h2 class="text-center">Forgot Password?</h2>
                                                <p>You can reset your password here.</p>
                                                <div class="panel-body">

                                                    <form action="forgotpassword" method="post">

                                                        <div class="form-group">
                                                            <div class="input-group">
                                                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                                                <input id="email" name="email" placeholder="Enter your email" class="form-control"  type="email" required="">

                                                            </div>
                                                            <input type="hidden" name="username" value="${acc.userId}"  />
                                                            <p style="color:red;">${mess7}</p>
                                                        </div>
                                                        <div class="form-group">
                                                            <input class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                                                        </div>

                                                        <input type="hidden" class="hide" name="token" id="token" value=""> 
                                                    </form>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </body>
</html>