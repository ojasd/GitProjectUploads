<%-- 
    Document   : question
    Created on : Sep 24, 2015, 12:49:12 PM
    Author(s)     : Rohit
    Collaborator(s)     : Ojas
    Last Modified     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Questions</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script> 
    </head>
    <body>
        <div id="header"><jsp:include page="header_login.html" /></div>
        <div id="navsidebar"><jsp:include page="navsidebar.jsp" /></div>
        <div id="mainContent" class="mainContentPart">
            <div>
                <div class="pageTitleBackground"><b><c:out value="Question"/></b></div>
                <br/>
                <c:if test="${theQuestion != null && theQuestion !=''}">
	                <form action="Study" method="post">
	                    <div class="float-left">
                                <img src="getImage?file=<c:out value='${theQuestion.imageURL}'/>" alt="<c:out value='No image Available'/>"/>
	                    </div>
	                    <div class="questionanswerSection">
	                        <div class="question">  
	                        <c:out value="${theQuestion.question}"/>
	                        </div><br/><br/>
	                      	<input type="hidden" name="action" value="answer"/>
	                      	<input type="hidden" name="StudyCode" value="<c:out value='${theQuestion.studyCode}'/>"/>
	                      	<div class="answers">
	                        	<select id="questionanswers" name="questionanswers">
                                            <option value="0"><c:out value="Select"/></option>
                                            <option value="1"><c:out value="1"/></option>
                                            <option value="2"><c:out value="2"/></option>
                                            <option value="3"><c:out value="3"/></option>
                                            <option value="4"><c:out value="4"/></option>
                                            <option value="5"><c:out value="5"/></option>
                                            <option value="6"><c:out value="6"/></option>
                                            <option value="7"><c:out value="7"/></option>
	                            </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                            <input type="submit" name="answerSubmit" id="answerSubmit" value="Submit" class="StudyButtonBackground"/>
	                    	</div>
	                    </div>
	            	</form>
	            </c:if>
            </div>
        </div>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
        <script>var theUserJSON=${theUserJSON};setUserHeaderValues();setUserSideBarValues();</script>
    </body>
</html>
