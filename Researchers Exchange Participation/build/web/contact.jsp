<%-- 
    Document   : contact
    Created on : Sep 24, 2015, 12:35:54 PM
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
        <title>Contact Us</title>
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <div id="header"><jsp:include page="header_login.html" /></div>
        <div class="pageTitleBackground"><c:out value="Contact"/></div>
            <div id="backPage">
                <a href="User?action=main">&lt;&lt;<c:out value="Back to the Main Page"/></a>
            </div>
        <div id="login_container">
        <div id="login-card"> 
            <form action="EmailListServlet" method="post">
            <input type="hidden" name="action" value="contact"/>
            <c:out value="Name*"/>&nbsp;&nbsp;&nbsp;<input type="text" name="Name" required placeholder=""><br/><br/>
            <c:out value ="Email*"/>&nbsp;&nbsp;&nbsp;<input type="text" name="Friend_Id" required placeholder=""><br/><br/>
            <c:out value ="Message*"/>&nbsp;&nbsp;&nbsp;<textarea name="Message" required rows="6" cols="30"></textarea><br/><br/>
            <input type="submit" name="submit_comment" class="button" value="Submit">
        </form>
        </div>
        </div>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
        <script>var theUserJSON=${theUserJSON};setUserHeaderValues();</script>
    </body>
</html>
