<?php
session_start();/*
//Check whether the session variable SESS_MEMBER_ID is present or not
if(!isset($_SESSION['sess_user_id']) || (trim($_SESSION['sess_user_id']) == '')) {
	header("location: login.php");
	exit();
	}		*/
?>

<!DOCTYPE>
<html>

<head>
	<meta charset="utf-8" />
	<title>2-factor Authentication</title>
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/alumni.css">	
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/main.css">
	
	<div class="banner">
		<img class="banner" src="images/banner.jpg" alt="Bank Banner">
	</div>
</head>

<body>


<?php
// define variables and set to empty values
$num = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
   $num = test_input($_POST["number"]);
   if(($num == 1111) || ($num == 2222) || ($num == 3333) || ($num == 4444) || ($num == 5555) || ($num == 6666) || ($num == 7777) || ($num == 8888) || ($num == 9999) )
   {
		header("Location:successfull.php");
   }
   else 
   {
		header("Location:Error.php");
   }
}

function test_input($data) {
   $data = trim($data);
   $data = stripslashes($data);
   $data = htmlspecialchars($data);
   return $data;
}
?>
<div class="wrapper">
	<section>
		<div class="">
			<h2> Add Bank of America to your 2-factor authentication app</h2>
			<p>You will need to install two-factor authentication application on your Smartphone or Tablet. </p>
			<p><a href="#">Click here</a> to download the Smartphone application.</p> 
		</div>		
	</section>
	
	<section>
	<h4>Step 1. Configure the App</h4>
	</section>
	
	<section>
	<p>Open your two factor authentication app and add your Bank of America account by scanning the QR code</p>
	</section>
	
	<section>
	<div>
	<img class="QR Code" src="images/qrcode.png" alt="QR Code">
	</div>
	<p>If you can't use the QR code, enter the text code sent to your mobile.</p>
	</section>
	
	<section>
			<h4>Step 2. Enter the 4 digit code that the application generates</h4><br>
		<div class = "logic-card">
			<form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="POST">
				<input type= "text" name= "number" id = "number" required="true" placeholder="1234"><br><br>
				<input type="submit" name="login" class="login login-submit" value="Submit">
			</form>
		</div>		
	</section>
	</div>	
	<div>
	<form action="Log!n.php" method="POST"><br>
	<input class="buttom" name="Logout" id="submit" value="Logout!" tabindex = "25" width = "10px" height= " 5px" type="submit">
	</form>
	
<div id="footer">
	<footer> 
         <p>&copy; Bank of America</p>
    </footer>
</div>

	</body>

</html>
