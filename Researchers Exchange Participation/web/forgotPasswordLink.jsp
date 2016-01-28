<%-- 
    Document   : forgotPasswordLink
    Created on : Nov 23, 2015, 7:55:14 PM
    Author     : Ojas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password Link Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <div id="header"><jsp:include page="header.html" /></div>
        
        <div id="login_container">
        <div id="login-card">            
            <form action="User" method="post">
            <input type="hidden" name="action" value="forgotPassword"/>
            <div class="red-text text-align_center" >${msg}<br/></div>
            <c:out value="Please enter your Email Address*"/>&nbsp;&nbsp;<input type="email" name="UserEmailForgot" id="user_email_id_fp" required><br><br>            
            <input type="submit" name="login" class="button" value="Submit"><br><br><br><br>            
            </form>
            </div>
        </div>         
            
        <div id="footer"><jsp:include page="footer.jsp" /></div>
        <script>var theUserJSON=${theUserJSON};setUserHeaderValues();</script>
    </body>
</html>
