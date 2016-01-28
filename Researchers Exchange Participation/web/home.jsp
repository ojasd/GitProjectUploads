<%-- 
    Document   : home
    Created on : Sep 24, 2015, 12:33:29 PM
    Author(s)     : Ojas
    Collaborator(s)     : Rohit
    Last Modified     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <div id="header"><jsp:include page="header.html" /></div>
        <div id="homeImage">
            <img src="images/user.png" alt=""/>
        </div>​
	<div id="footer"><jsp:include page="footer.jsp" /></div>       
</body>
</html>
