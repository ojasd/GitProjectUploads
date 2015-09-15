
<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>CW</title>

    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/main.css">

</head>

<body  >
  <?php include 'header.php'; ?>

  <div class="login-card">
    <h1>Log-in</h1><br>
  <form action='logincheck.php' method='POST'>
  <p id="ErrorMessage"
							style="display: inline-block; width: 100%; text-align: center;">
			  				<?php
								if (isset ( $_GET ["errorCode"] )) {
									if (! empty ( $_GET ["errorCode"] ))
							?>
			  					<p style="text-align: center;">Username or Password is invalid</p>
			  				<?php
								} else {
								}
							?></p>
    <input type="text" name="login" id="login" required="" placeholder="Username"><br></br>
    <input type="password" name="password" id="password" required="" placeholder="Password"><br></br>
    <input type="submit" name="loginBt" id="loginBt" class="login login-submit" value="login"><br></br>
  </form>	

  <div class="login-help">
    <a href="#">Forgot Password</a>
  </div>
</div>

</body>

</html>