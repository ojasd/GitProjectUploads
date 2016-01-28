<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="wrappersidebar">
	<div id="sidebar">
		<ul>
                    <li><a href="javascript:void(0);"><b><c:out value="Coins"/>&nbsp;(</b><span id ="coinsV" class="blue-text"></span><b>)</b></a></li>
                    <li><a href="javascript:void(0);"><b><c:out value="Participants"/>&nbsp;(</b><span id ="partpantV" class="blue-text"></span><b>)</b></a></li>
                            <li><a href="javascript:void(0);"><b><c:out value="Participation"/>&nbsp;(</b><span id="parttionV" class="blue-text"></span><b>)</b></a></li>
			<li></li>
                        <li><a href="User?action=main"><b><c:out value="Home"/></b></a></li>
                        <li><a href="Study?action=participate"><b><c:out value="Participate"/></b></a></li>
                        <li><a href="Study?action=studies"><b><c:out value="My Studies"/></b></a></li>
                        <li><a href="recommend.jsp"><b><c:out value="Recommend"/></b></a></li>
                        <li><a href="contact.jsp"><b><c:out value="Contact"/></b></a></li>
		</ul>
	</div>
</nav>