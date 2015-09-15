
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<title>CW</title>
	<link rel="stylesheet" href="css/alumni.css">
		<link rel="stylesheet" href="css/main.css">
</head>

<body>
<?php include 'header.php'; ?>

	<div class="wrapper">
				<div id="head">
				<h1 style="color: #006435"> Sign Up to Ride</h1>	
				</div>			
			<div id="registration">
    		<form action="register.php" method="POST"> 
    			<input name="Reg_flag" type="hidden" value="0" />
    			<table id="regtable">
    			<tr><td><label for="first name">First Name</label> </td>
    			<td><input id="F_Name" name="F_Name" placeholder="First name" required="" type="text"> </td>	</tr>
				
				<tr><td><label for="middle name">Middle Name</label> </td>
    			<td><input id="M_Init" name="M_Init" placeholder="Middle name" required="" type="text"> </td>	</tr>

    			<tr><td><label for="name">Last Name</label> </td>
    			<td><input id="L_Name" name="L_Name" placeholder="Last name" required="" type="text"> </td>	</tr>
    			
				<tr><td><label for="email">Email</label> </td>
    			<td><input id="Email_Id" name="Email_Id" placeholder="example@domain.com" required="" type="email"> </td>	</tr>
		
				<tr><td><label for="username">Create a Username</label> </td>
    			<td><input id="User_Id" name="User_Id" placeholder="Username" required="" type="text"> </td>	</tr>
    			 
                <tr><td><label for="password">Create a Password</label> </td>
    			<td><input type="Password" id="Password" name="Password" placeholder="Password" required=""> </td>	</tr>
				
                <tr><td><label for="repassword">Confirm your Password</label> </td>
    			<td><input type="password" id="repassword" name="repassword" placeholder="Re-enter Password" required=""></td>	</tr>
				
				<tr><td><label for="mobile">Mobile No</label> </td>
    			<td><input id="mobile_no" name="mobile_no" placeholder="mobile" required="" type="text"> </td>	</tr>
				
				<tr><td><label for="credit card">Credit Card No</label> </td>
    			<td><input id="CC_No" name="CC_No" placeholder="credit card" required="" type="text"> </td>	</tr>
				
				<tr><td><label for="expiration year">Expiration year</label> </td>
    			<td><input id="exp_year" name="exp_year" placeholder="expiration year" required="" type="text"> </td>	</tr>

				<tr><td><label for="expiration year">Expiration month</label> </td>
    			<td><input id="exp_month" name="exp_month" placeholder="expiration month" required="" type="text"> </td>	</tr>

				<tr><td><label for="postal code">Postal Code</label> </td>
    			<td><input id="post_code" name="post_code" placeholder="postal code" required="" type="text"> </td>	</tr>
				
				</table>
				<br><input class="buttom" name="submit" id="submit" value="Sign up!" type="submit"> 	 

				
			</form> 
			</div>	      
      
	</div>
</body>
</html>
