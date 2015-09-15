<?php
include 'dbconnect.php';

$Fname=$_POST['F_Name'];
$Mname=$_POST['M_Init'];
$Lname=$_POST['L_Name'];
$email=$_POST['Email_Id'];
$mobNum=$_POST['mobile_no'];
$ExpMon=$_POST['exp_month'];
$ExpYear=$_POST['exp_year'];
$CC_No=$_POST['CC_No'];
$code=$_POST['post_code'];


$userId=$_POST['User_Id'];

$password=$_POST['repassword'];
$sql2="insert into users (firstName,middleName,lastName,email,mobNum,ccNum,expMonth,expYear,postalCode) values 
('$Fname','$Mname','$Lname','$email','$mobNum','$CC_No','$ExpMon','$ExpYear','$code')";
$query2 = mysql_query($sql2);
$sql1="insert into user_login (userName,password) values 
('$userId','$password')";
$query1 = mysql_query($sql1);

header("location: home.php");
?>