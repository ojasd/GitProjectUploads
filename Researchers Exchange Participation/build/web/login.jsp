<%-- 
    Document   : login
    Created on : Sep 24, 2015, 12:11:18 PM
    Author(s)     : Ojas
    Collaborator(s)     : Rohit
    Last Modified     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" media="screen" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script> 
        <title>Login</title>
    </head>
    <body>
        <div id="header"><jsp:include page="header.html" /></div>
        <div id="login_container">
        <div id="login-card">            
            <form action="User" method="post">
            <input type="hidden" name="action" value="login"/>
            <div class="red-text text-align_center" >${msg}<br/></div>
            <c:out value="Email Address*"/>&nbsp;&nbsp;&nbsp;<input type="email" name="User_Id" placeholder="" required><br/><br/>
            <c:out value="Password*"/>&nbsp;&nbsp;&nbsp;<input type="password" name="Password" placeholder="" required><br/>
            <input type="submit" name="login" class="button" value="Log in"><input type="button" value="Forgot Password" name="forgotPassword"
            onclick="window.location.href='forgotPasswordLink.jsp'"/><br/><br/><br/><br/><br/><br/>
            </form>
            <!--<div id="forgot_password">-->
            
            <!--</div>-->
        </div>
        </div>
        <!--<div id="align_block">-->
        
        <div id="signup_content">
            <a href="signup.jsp"><c:out value="Sign up for a new account"/></a>
        </div>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
    </body>
</html>