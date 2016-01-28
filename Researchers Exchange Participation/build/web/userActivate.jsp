<%-- 
    Document   : userActivate
    Created on : Nov 23, 2015, 5:31:04 PM
    Author     : Ojas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activation Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <div id="header"><jsp:include page="header.html" /></div>
        <h1 class="activate_page"><c:out value="Thank You for signing up. Please check your email for activation link."/></h1>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
    </body>
</html>