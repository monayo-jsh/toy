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

        <script type="text/javascript" src="<c:out value="/js/jquery-3.6.3.slim.min.js"/>"></script>

        <title>토이 프로젝트</title>
    </head>
    <body>
        <script type="text/javascript">

            window.onload = () => {
              const wrapper = document.querySelector(".wrapper"),
                    signupHeader = document.querySelector(".signup header"),
                    loginHeader = document.querySelector(".login header");

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
                <form class="form" action="#">
                    <input type="text" placeholder="you full name" required />
                    <input type="text" placeholder="you email address" required />
                    <input type="password" placeholder="your password" required />
                    <div class="terms-checkbox">
                        <input type="checkbox" id="signupCheck"/>
                        <label for="signupCheck">I accept all terms & conditions</label>
                    </div>
                    <input type="submit" value="Signup" />
                </form>
            </div>

            <div class="layout-form login">
                <header>Login</header>
                <form class="form" action="#">
                    <input type="text" placeholder="you email address" required />
                    <input type="password" placeholder="your password" required />
                    <a href="#">Forgot password?</a>
                    <input type="submit" value="Login" />
                </form>
            </div>
        </section>
    </body>
</html>