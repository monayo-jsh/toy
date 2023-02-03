<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" type="text/css" href="<c:out value="/stylesheet/common.css"/>" />
        <link rel="stylesheet" type="text/css" href="<c:out value="/stylesheet/login.css"/>" />

        <script type="text/javascript" src="<c:out value="/js/jquery/jquery-3.6.3.min.js"/>"></script>
        <script type="text/javascript" src="<c:out value="/js/common/common.js"/>"></script>
        <script type="text/javascript" src="<c:out value="/js/common/ajax.js"/>"></script>
        <script type="text/javascript" src="<c:out value="/js/login/login.js"/>"></script>

        <title>토이 프로젝트</title>
    </head>
    <body>
        <script type="text/javascript">

            window.onload = () => {
              const wrapper = document.querySelector(".wrapper"),
                    signupHeader = document.querySelector(".signup header"),
                    loginHeader = document.querySelector(".login header"),
                    btnLogin = document.querySelector("#btn-login"),
                    btnSignup = document.querySelector("#btn-signup");

              loginHeader.addEventListener("click", () => {
                wrapper.classList.add("active");
              });

              signupHeader.addEventListener("click", () => {
                wrapper.classList.remove("active");
              });

            }

        </script>

        <section class="wrapper active">
            <div class="layout-form signup">
                <header>SingUp</header>
                <form class="form" onsubmit="signup(this); return false;">
                    <div class="input-field">
                        <input type="text" name="name" placeholder="you full name" required />
                        <i class="icon-user"></i>
                    </div>
                    <div class="input-field">
                        <input type="text" name="email" placeholder="you email address" required />
                        <i class="icon-email"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" name="password" placeholder="your password" required />
                        <i class="icon-lock"></i>
                    </div>
                    <div class="terms-checkbox">
                        <input type="checkbox" id="signupCheck"/>
                        <label for="signupCheck">I accept all terms & conditions</label>
                    </div>
                    <input type="submit" value="Signup" />
                </form>
            </div>

            <div class="layout-form login">
                <header>Login</header>
                <form class="form" method="post" onsubmit="login(this); return false;">
                    <div class="input-field">
                        <input type="text" name="email" placeholder="you email address" required />
                        <i class="icon-email"></i>
                    </div>
                    <div class="input-field">
                        <input type="password" name="password" placeholder="your password" required />
                        <i class="icon-lock"></i>
                    </div>
                    <a href="#">Forgot password?</a>
                    <input type="submit" value="Login" />
                </form>
            </div>
        </section>
    </body>
</html>