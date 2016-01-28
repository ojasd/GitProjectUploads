<%-- 
    Document   : aboutl
    Created on : Sep 24, 2015, 12:35:02 PM
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
        <title>About Us</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <div id="header"><jsp:include page="header_login.html" /></div>
        <div id="navsidebar"><jsp:include page="navsidebar.jsp" /></div>
        <div id="mainContent" class="mainContentPart">
        <div class="boxed margin_0">
            <jsp:include page="about_text.jsp" />
        </div>
        </div>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
        <script>highlightAbout();</script>
        <script>var theUserJSON=${theUserJSON};setUserHeaderValues();setUserSideBarValues();</script>
    </body>
</html>
