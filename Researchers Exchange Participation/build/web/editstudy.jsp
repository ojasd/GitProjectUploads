<%-- 
    Document   : editstudy
    Created on : Sep 24, 2015, 12:46:35 PM
    Author(s)     : Rohit
    Collaborator(s)     : Ojas
    Last Modified       : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Study</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
       <div id="header"><jsp:include page="header_login.html" /></div>
       <div class="pageTitleBackground"><b><c:out value="Adding a study"/></b></div>
       <div id="backPage">
           <a href="User?action=main">&nbsp;&nbsp;&lt;&lt;<c:out value="Back to the main page"/></a><br/>
       </div>
       <div id="loggedUserContent" class="loggedUserMainContent">
        <form action="Study" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="StudyCode" value="<c:out value='${editStudy.studyCode}'/>"/>
            <table class="AddStudyTable">
            <tr>
                <td><label for="EditStudyName"><c:out value="Study Name *"/></label></td>
                <td><input type="text" id = "EditStudyName" name="StudyName" class="inputboxstyle" required value="<c:out value='${editStudy.name}'/>"/></td>
            </tr>
            <tr>
                <td><label for="EditQuestionText"><c:out value="Question Text *"/></label></td>
                <td><input type="text" id="EditQuestionText" name="QuestionText" class="inputboxstyle" required value="<c:out value='${editStudy.question}'/>"/></td>
            </tr>
            <tr>
                <td><label for="NewStudyImage"><c:out value="Image *"/></label></td>
                <td>
                    <img src="getImage?file=<c:out value='${editStudy.imageURL}'/>" alt="No Image Available"/>
                    <button type="button" class="StudyButtonBackground editstudybutton" onclick="chooseFile();"><c:out value="Browse"/></button>
                    <div class="edityStudy_new_study_image">
                        <input type="file" id="NewStudyImage" name="NewStudyImage" required/>
                    </div>
               </td>
            </tr>
            <tr>
                <td><label for="EditStudyParticipants"><c:out value="# Participants *"/></label></td>
                <td><input type="number" id="EditStudyParticipants" name="Participants" class="inputboxstyle" required value="<c:out value='${editStudy.requestedParticipants}'/>"/></td>
            </tr>
            <tr>
                <td><label for="EditStudyDescription"><c:out value="Description *"/></label></td>
                <td><textarea id="EditStudyDescription" name="Description" class="newStudyDescription"><c:out value="${editStudy.description}"/></textarea></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="edit_study_update" class="button addstudybutton" value="Update"></td>
            </tr>
            </table>
        </form>
       </div>
       <div id="footer"><jsp:include page="footer.jsp" /></div>
       <script>var theUserJSON=${theUserJSON};setUserHeaderValues();</script>
    </body>
</html>
