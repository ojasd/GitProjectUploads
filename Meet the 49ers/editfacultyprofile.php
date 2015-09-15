<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<?php include 'dbconnect.php'?>
<?php

$Person_ID = 3;





$query = "select concat(L_Name ,' ', F_Name) as Name, Email_Id, DOB, Address,Location,Ph_no, Marital_Status from Person where Person_Id ='$Person_ID'";
$result = mysql_query($query);
//echo $result;
while($row = mysql_fetch_array($result)) {
	$Name = $row['Name'];
	
	$Email_ID = $row['Email_Id'];
	$DOB = $row['DOB'];
	$Address = $row['Address'];
	$Location = $row['Location'];
	$Ph_No = $row['Ph_no'];
	$Marital_Status = $row['Marital_Status'];
}
$query = "select f.Facuty_id from faculty f where  f.Person_Id = '$Person_ID'";
$result = mysql_query($query);
//$Faculty_Id = $result;
while($row = mysql_fetch_array($result)) {
$Faculty_Id = $row['Facuty_id'];
}







 ?>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Faculty Profile</title>
</head>
<body background="green-design.jpg" >
<h2 align="center"><u>Faculty Profile</u></h2>



 <?php 
// $actionvar="";

// 	if(isset($_POST['submit1']))	
// 	{
// 		$actionvar="alumni_profile.php";
// 	} 
// 	else if (isset($_POST['submit2']))
// 	{
// 			$actionvar="editfacultyprofile.php"; 
// 	}

// ?>




<form id="form1" name="form1" action="viewfacultyprofile.php" method="post" >
<div>
  <table style="float: right" >
<!--  <table  align="center" cellpadding="2">-->
<tr>
  <td>
    <img src="student-image-2.jpg" style="width:300px;height:300px">
    </td>
  </tr>
</table>
</div>
<div>
  <table  align="center" cellpadding="4">
  
    <tr>
      <td align="left">
        <label for="textfield"><strong>Name: </strong></label>
      </td>
	  <td >
        <input name="faculty_name" value="<?php echo $Name ?>" type="text">
      </td>
    </tr>
    
   
    
     <tr>
      <td align="left">
        <label for="textfield"><strong>Email: </strong></label>
      </td>
	  <td >
	  <input name="email_id" value="<?php echo $Email_ID ?>" type="text">
        
      </td>
    </tr>
    
   <tr>
      <td align="left">
        <label for="textfield"><strong>Date of Birth: </strong></label>
      </td>
	  <td >
	  <input name="dob" value="<?php echo $DOB ?>" type="text">
        
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Address: </strong></label>
      </td>
	  <td >
	  <input name="address" value="<?php echo $Address ?>" type="text">
        
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Location: </strong></label>
      </td>
	  <td >
	  <input name="location" value="<?php echo $Location ?>" type="text">
        
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Contact No.: </strong> </label>
      </td>
	  <td >
	  <input name="ph_no" value="<?php echo $Ph_No?>" type="text">
        
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Marital Status: </strong></label>
      </td>
	  <td >
	  <input name="marital_status" value="<?php echo $Marital_Status?>" type="text">
        
      </td>
    </tr>
    
    
  </table>
  </div>
 
   
  <br/>
  <br/>
<center>
<input name="edit_submit" type="submit" value="Save" onclick="myFunction()" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input name="submit2" type="submit" value="Cancel" /></center>
    
<script type="text/javascript">
function myFunction() {
    
}
</script>
</form>
</body>
</html>


<?php

?>

