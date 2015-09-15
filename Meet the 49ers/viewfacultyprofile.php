<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<?php include 'dbconnect.php'?>
<?php

$Person_ID = 3;

if(isset($_POST['edit_submit'])){
	$faculty_name=$_POST['faculty_name'];

	$token = strtok($faculty_name, " ");
	$lname =  $token;
	$token = strtok(" ");
	$fname = $token;

	$email_id=$_POST['email_id'];
	$dob=$_POST['dob'];
	$address=$_POST['address'];
	$location=$_POST['location'];
	$ph_no=$_POST['ph_no'];
	$marital_status=$_POST['marital_status'];

	$query = "UPDATE Person SET F_Name = '$fname',L_Name = '$lname', Email_Id = '$email_id', DOB = '$dob', Address = '$address',Location = '$location',Ph_no = '$ph_no', Marital_Status='$marital_status' where Person_Id ='$Person_ID'";
	$result = mysql_query($query);

}

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




<form id="form1" name="form1" action="" method="post" >
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
        <label for="textfield"><?php echo $Name ?></label>
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Faculty_Id: </strong></label>
      </td>
	  <td >
        <label for="textfield"><?php echo $Faculty_Id ?></label>
      </td>
    </tr>
    
     <tr>
      <td align="left">
        <label for="textfield"><strong>Email: </strong></label>
      </td>
	  <td >
        <label for="textfield"><?php echo $Email_ID ?> </label>
      </td>
    </tr>
    
   <tr>
      <td align="left">
        <label for="textfield"><strong>Date of Birth: </strong></label>
      </td>
	  <td >
        <label for="textfield"><?php echo $DOB ?></label>
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Address: </strong></label>
      </td>
	  <td >
        <label for="textfield"><?php echo $Address ?></label>
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Location: </strong></label>
      </td>
	  <td >
        <label for="textfield"><?php echo $Location ?></label>
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Contact No.: </strong> </label>
      </td>
	  <td >
        <label for="textfield"><?php echo $Ph_No?></label>
      </td>
    </tr>
    
    <tr>
      <td align="left">
        <label for="textfield"><strong>Marital Status: </strong></label>
      </td>
	  <td >
        <label for="textfield"><?php echo $Marital_Status?></label>
      </td>
    </tr>
    
    
  </table>
  </div>
 
   
  <br/>
  <br/>
<center>
<input name="submit1" type="submit" value="Delete" onclick="DeleteWithCheck()" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input name="submit2" type="submit" value="Edit" /></center>
    
<script type="text/javascript">
function DeleteWithCheck() {
	  if (confirm("Are you sure you want to delete the faculty profile"+<?php echo $Faculty_Id ?>))
	  {
	    $.ajax({
	      type: "POST",
	      url: "/deleteprocess.php",
	      data: "delcustomerid="+<?php echo $Faculty_Id ?>,
	      success: refreshTable
	    });
	  }
	  else
	    alert("Aborted");
}



</script>

</form>

</body>
</html>