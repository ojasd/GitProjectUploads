<%-- 
    Document   : signup
    Created on : Sep 24, 2015, 12:21:53 PM
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
        <title>Reset Password Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script> 
    </head>
    <body>
        <div id="header"><jsp:include page="header.html" /></div>
        <div id="login_container">
        <div id="login-card"> 
         <form method="post" action="User">
         	<input type="hidden" name="action" value="updatePassword"/>
            <div class="red-text text-align_center">${msg}<br/></div>
            <c:out value="Email*"/>&nbsp;&nbsp;&nbsp;<input type="text" name="user_Id" disabled value="<c:out value='${userEmailID}'/>"><input type="hidden" name="userEmail" value="<c:out value='${userEmailID}'/>"><br/><br/>
            <c:out value="Password*"/>&nbsp;&nbsp;&nbsp;<input type="password" name="Password" required><br/><br/>
            <c:out value="Confirm Password*"/>&nbsp;&nbsp;<input type="password" name="Conf_password" required><br/><br/>
            <input type="submit" name="create_account" class="button" value="Reset Password"><br/><br/><br/>
            </form>
        </div>
        </div>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
    </body>
</html>
