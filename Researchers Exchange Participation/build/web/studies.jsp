<%-- 
    Document   : studies
    Created on : Sep 24, 2015, 12:41:07 PM
    Author(s)     : Rohit
    Collaborator(s)     : Ojas
    Last Modified    : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Studies Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css" />
        <link rel="stylesheet" href="styles/style.css" type="text/css" />
        <script src="//platform.linkedin.com/in.js"></script>
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/main.js"></script>
        <script srcc="js/in.js"></script>
    </head>
    <body onload="fbscript(); tweetscript()">
        <div id="fb-root"></div>
        
        <div id="header"><jsp:include page="header_login.html" /></div>
        <div class="pageTitleBackground"><b><c:out value="My Studies"/></b></div>
        <div id="backPage">
            <a href="newstudy.jsp">&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="Add a new Study"/></a><br/>
            <a href="User?action=main">&nbsp;&nbsp;&lt;&lt;<c:out value="Back to the main page"/></a>
        </div>
        <c:choose>
			<c:when test="${userStudies.size()>0}">
		        <form method="post" action="Study" id="studiesForm">
			        <input type="hidden" name="action" id="startStopEditAction" value=""/>
			        <input type="hidden" name="StudyCode" id="selectedStudyCode" value=""/>
			        <table class="studyTable">
			            <tr>
                                        <th><b><c:out value="Study Name"/></b></th>
                                        <th><b><c:out value="Requested Participants"/></b></th>
                                        <th><b><c:out value="Participations"/></b></th>
                                        <th><b><c:out value="Status"/></b></th>
                                        <th><b><c:out value="Actions"/></b></th>
                                        <th colspan="3"><b><c:out value="Share"/></b></th>
			            </tr>
		            	<c:forEach items="${userStudies}" var="study">
			            <tr>
			            	<td class="text-align_center"><c:out value="${study.name}"/></td>
			            	<td class="text-align_center"><c:out value="${study.requestedParticipants}"/></td>
			            	<td class="text-align_center"><c:out value="${study.numOfParticipants}"/></td>
			            	<td class="paddingcolumns">
			            		<button onclick="doStartStopEdit('<c:out value="${study.status}"/>','<c:out value="${study.studyCode}"/>');" class="tableButton">
			            			<c:choose>
			            				<c:when test="${study.status == 'Start'}">
			            					<c:out value="Stop"/>
			            				</c:when>
			            				<c:when test="${study.status == 'Stop'}">
			            					<c:out value="Start"/>
			            				</c:when>
			            			</c:choose>
			            		</button>
			            	</td>
                                        <td class="paddingcolumns"><button onclick="doStartStopEdit(undefined,'<c:out value="${study.studyCode}"/>');" class="tableButton"><c:out value="Edit"/></button></td>
                                        <td>
                                            <div class="fb-share-button" data-href="//ornroadiesassignment2-rnbad.rhcloud.com/rohit_Assignment3/Study?action=studies" data-layout="button" onclick="generate()"></div>
                                        </td>
                                        <td>
                                        <script type="IN/Share" data-url="https://ornroadiesassignment2-rnbad.rhcloud.com/rohit_Assignment3/Study?action=studies"></script>
                                        </td>
                                        <td>
                                            <a href="//ornroadiesassignment2-rnbad.rhcloud.com/rohit_Assignment3/Study?action=studies" class="twitter-share-button" data-img="generate()">Tweet</a>
                                        </td>
			            </tr>
			            </c:forEach>
			            <tr class="blank_row">
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
			            </tr>
			            <tr class="blank_row">
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
			                <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
			            </tr>
			        </table>
				        
		        </form>
			</c:when>
            <c:otherwise><br/><br/><br/><h1 class="text-align_center"><c:out value="No Studies to Show"/></h1></c:otherwise>
		</c:choose>
        <div id="footer"><jsp:include page="footer.jsp" /></div>
        <script>var theUserJSON=${theUserJSON};setUserHeaderValues();</script>
    </body>
</html>
