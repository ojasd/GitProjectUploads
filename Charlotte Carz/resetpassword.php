<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>AlumniBook- Reset Password</title>
  <link rel="stylesheet" href="css/style.css">
  <script type="text/javascript" src="js/functions.js"></script>
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
  <div id="main-wraper">
	<div id="top-wraper" style="padding-bottom: 15px;">
		<div id="banner" style="padding-top: 90px;">AlumniBook</div>
		<div id="nav" style="display: none;"> 
			<!-- <ul>
				<li style="padding-left:0px; padding-right:0px;"><a href="ConferenceChairLoginSuccess.jsp">Home</a></li>
				<li style="padding-left:0px; padding-right:0px;"><a href="CreateConference.jsp">Create Conference</a></li>
				<li style="padding-left:0px; padding-right:0px;"><a href="/EasyResearch/InviteReviewers">Invite Reviewers</a></li>  
				<li style="padding-left:0px; padding-right:0px;"><a href="/EasyResearch/CheckStatusConferenceChair">Check Abstract</a></li>
				<li style="padding-left:0px; padding-right:0px;"><a href="/EasyResearch/AssignReviewerPaper">Assign Papers</a></li>
				<li style="padding-left:0px; padding-right:0px;"><a href="/EasyResearch/ConferenceChairNotification">Notifications</a></li>
				<li style="padding-left:0px; padding-right:0px;"><a href="/EasyResearch/MakeDecision">Make Paper Decision</a></li>
			</ul> -->
		</div>
  	</div>
	<form method="post" action="passwordret.php" class="login" style="align-text: right; margin-top:13px;" onsubmit="return checkResetFields();">
		<table>
			<tbody>
				<tr>
		  			<td style="display: inline-block;">
		  				<h2 class="blue-heading">Reset Password</h2>
		  			</td>
			    </tr>
		  		<tr>
		  			<td id = "ErrorResetMessage"></td>
			    </tr>
			    <tr>
		  			<td>
			    		<p>
			      			<label for="login">User Name:</label>
			      			<input type="text" name="resetlogin" id="resetlogin" value="user name" onfocus="emptyStringResetUserName();" onblur="addDefaultResetUserName(); ">
			      		</p>
			      		<p>
			      			<label for="Email">Email:</label>
			      			<input type="text" name="resetemail" id="resetemail" value="name@example.com" onfocus="emptyStringResetEmail();" onblur="addDefaultResetEmail();">
		    			</p>
			    	</td>
			    </tr>
			    <tr>
			    	<td colspan="2" align="center" style="padding-left: 80px;">
			    		<button type="submit" class="loginbutton" style="border:none;" value="Login">Reset</button>
			    	</td>
			    </tr>
		    </tbody>
		 </table>
	  </form>
  </div>
  <!-- <section class="about">
    <p class="about-links">
      <a href="http://www.cssflow.com/snippets/dark-login-form" target="_parent">View Article</a>
      <a href="http://www.cssflow.com/snippets/dark-login-form.zip" target="_parent">Download</a>
    </p>
    <p class="about-author">
      &copy; 2012&ndash;2013 <a href="http://thibaut.me" target="_blank">Thibaut Courouble</a> -
      <a href="http://www.cssflow.com/mit-license" target="_blank">MIT License</a><br>
      Original PSD by <a href="http://365psd.com/day/2-234/" target="_blank">Rich McNabb</a>
  </section> -->
</body>
</html>