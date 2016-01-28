<%-- 
    Document   : confirmc
    Created on : Sep 24, 2015, 12:37:54 PM
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
        <title>Confirmation Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <div id="header"><jsp:include page="header_login.html" /></div>
        <br><br><div id="backPage">
            <a href="User?action=main">&lt;&lt;<c:out value="Back to the Main Page"/></a>
        </div>
        <div id="confirmmsg"><c:out value= "Message Sent.."/></div>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
        <script>var theUserJSON=${theUserJSON};setUserHeaderValues();</script>
    </body>
</html>
