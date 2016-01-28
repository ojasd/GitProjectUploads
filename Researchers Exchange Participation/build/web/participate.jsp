<%-- 
    Document   : participate
    Created on : Sep 24, 2015, 12:47:38 PM
    Author(s)     : Ojas
    Collaborator(s)     : Rohit
    Last Modified     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Participation</title>
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
                <div class="pageTitleBackground"><b><c:out value="My Studies"/></b></div>
                <br/><br/>
                <c:choose>
			    	<c:when test="${participateStudies.size()>0}">
		                <form id="participateStudiesForm" method="post" action="Study">
		                	<input type="hidden" name="action" value="participate"/>
			        	<input type="hidden" name="StudyCode" id="selectedParticipationStudyCode"/>
			                <table class="studyTableParticipate">
				                <tr>
                                                    <th><b><c:out value="Study Name"/></b></th>
                                                    <th><b><c:out value="Image"/></b></th>
                                                    <th><b><c:out value="Question"/></b></th>
                                                    <th><b><c:out value="Actions"/></b></th>
				                </tr>
				                <c:forEach items="${participateStudies}" var="partStudy">
				                <tr>
				                    <td class="text-align_center"><c:out value="${partStudy.name}"/></td>
				                    <td class="padding"><img src="getImage?file=<c:out value='${partStudy.imageURL}'/>" alt="Image not available"/></td>
				                    <td class="text-align_center"><c:out value="${partStudy.question}"/></td>
                                                    <td class="paddingcolumns"><button onclick="participateInStudy('${partStudy.studyCode}');" class="partTableButton"><c:out value="Participate"/></button></td>
				                </tr>
				                </c:forEach>
				                <tr class="blank_parti_row">
				                    <td></td>
				                    <td></td>
				                    <td></td>
				                    <td></td>
				                </tr>
				                <tr class="blank_parti_row">
				                    <td></td>
				                    <td></td>
				                    <td></td>
				                    <td></td>
				                </tr>
			        		</table>
			        	</form>
	        		</c:when>
                    <c:otherwise><h1 class="text-align_center"><c:out value="No Studies to Participate"/></h1></c:otherwise>
                </c:choose>
            </div>
        </div>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
        <script>var theUserJSON=${theUserJSON};setUserHeaderValues();setUserSideBarValues();</script>
    </body>
</html>
