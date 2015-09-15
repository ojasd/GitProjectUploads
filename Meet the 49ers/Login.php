
<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Log-in to Alumni Registration System</title>

    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/main.css">

</head>

<body>
  <?php include 'header.php'; ?>

  <div class="login-card">
    <h1>Log-in</h1><br>
  <form action='check_login.php' method='POST'>
    <input type="text" name="User_Id" required="" placeholder="Username">
    <input type="password" name="Password" required="" placeholder="Password">
    <input type="submit" name="login" class="login login-submit" value="login">
  </form>

  <div class="login-help">
    <a href="Registration.php">Register</a> • <a href="#">Forgot Password</a>
  </div>
</div>

</body>

</html>