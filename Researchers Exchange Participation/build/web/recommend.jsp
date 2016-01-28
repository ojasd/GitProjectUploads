<%-- 
    Document   : recommend
    Created on : Sep 24, 2015, 12:39:10 PM
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
        <title>Recommend Page</title>
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <div id="header"><jsp:include page="header_login.html" /></div>
        <div class="pageTitleBackground"><b><c:out value="Recommend to a friend"/></b></div>
        <div id="backPage">
            <a href="User?action=main">&nbsp;&nbsp;&lt;&lt;<c:out value="Back to the main page"/></a><br/>
        </div>
        <div id="login_container">
            <div id="login-card" style="padding: 0; margin: 10px;">
                <form action="EmailListServlet" method="post">
                    <input type="hidden" name="action" value="recommend"/>
                    <c:out value="Name*"/>&nbsp;&nbsp;&nbsp;<input type="text" name="Name" required/><br/><br/>
                    <c:out value="Email*"/>&nbsp;&nbsp;&nbsp;<input type="text" name="User_Id" required/><br/><br/>
                    <c:out value="Friend's Email*"/>&nbsp;&nbsp;&nbsp;<input type="text" name="Friend_Id" required/><br/><br/>
                    <c:out value="Message*"/>&nbsp;&nbsp;&nbsp;<textarea name="Message" required rows="6" cols="30"></textarea><br/><br/>
                    <input type="submit" name="create_account" class="button" value="Submit"><br>
                    <br/><br/><br/><div id="msg"><c:out value="When your friend whom you referred creates an account, you'll get 2 coins bonus"/>
                    </div>
                </form>
            </div>
            
        </div><br/><br/>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
        <script>var theUserJSON=${theUserJSON};setUserHeaderValues();</script>
    </body>
</html>
