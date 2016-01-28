<%-- 
    Document   : newstudy
    Created on : Sep 24, 2015, 12:41:51 PM
    Author(s)     : Rohit
    Collaborator(s)     : Ojas
    Last Modified     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add a new Study</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
       <div id="header"><jsp:include page="header_login.html" /></div>
       <div id="navsidebar"></div>
       <div class="pageTitleBackground"><b><c:out value="Adding a study"/></b></div>
       <div id="backPage">
           <a href="User?action=main">&nbsp;&nbsp;&lt;&lt;<c:out value="Back to the main page"/></a><br/>
       </div>
       <div id="loggedUserContent" class="loggedUserMainContent">
           <form action="Study" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="add"/>
            <table class="AddStudyTable">
            <tr>
                <td><label for="NewStudyName"><c:out value="Study Name *"/></label></td>
                <td><input type="text" id="NewStudyName" name="StudyName" class="inputboxstyle" required></td>
            </tr>
            <tr>
                <td><label for="NewQuestionText"><c:out value="Question Text *"/></label></td>
                <td><input type="text" id="NewQuestionText" name="QuestionText" class="inputboxstyle" required></td>
            </tr>
            <tr>
                <td><label for="NewStudyImage"><c:out value="Image *"/></label></td>
                <td>
                    <div class="edityStudy_new_study_image">
                        <input type="file" id="NewStudyImage" name="NewStudyImage" required/>
                    </div>
                    <button type="button" class="StudyButtonBackground" onclick="chooseFile();"><c:out value="Browse"/></button>
                </td>
            </tr>
            <tr>
                <td><label for="NewStudyParticipants"><c:out value="# Participants *"/></label></td>
                <td><input type="number" id = "NewStudyParticipants" name="Participants" class="inputboxstyle" required></td>
            </tr>
            <tr>
                <td><label for="NewStudyDescription"><c:out value="Description *"/></label></td>
                <td><textarea id = "NewStudyDescription" name="Description" class="newStudyDescription"></textarea></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="study_submit" class="button addstudybutton" value="Submit"></td>
            </tr>
            </table>
        </form>
       </div>
       <div id="footer"><jsp:include page="footer.jsp" /></div>
       <script>var theUserJSON=${theUserJSON};setUserHeaderValues();</script>
    </body>
</html>
