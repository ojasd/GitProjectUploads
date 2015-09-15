<!DOCTYPE html>
<html>
<head>
    <script
src="http://maps.googleapis.com/maps/api/js">
</script>

<script>
function initialize() {
  var mapProp = {
    center:new google.maps.LatLng(35.2269,-80.8433),
    zoom:8,
    mapTypeId:google.maps.MapTypeId.ROADMAP
  };
  var map=new google.maps.Map(document.getElementById("googleMap"), mapProp);
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
    
<style>
#header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}
#nav {
    line-height:50px;
    background-color:"black";
    height:300px;
    width:550px;
    float:left;
    padding:5px;	      
}
#section {
    width:350px;
    float:left;
    padding:10px;	 	 
}
#footer {
    background-color:#F5DA81;
    color:white;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}
</style>
</head>
<body>

<div id="header" style="width: 100%;">
<h1>Charlotte Carz

    <a href = "Main.php"><button style="font-size: 18px; margin: 2px; float: right;" id="i" >Logout</button></a>
</h1>
</>


</div>

<div id="nav">
<form>
Source:<br>
<input type="text" name="source" size="70">
<br>
Destination:<br>
<input type="text" name="destination" size="70"><br/>
 
    <button type="button" onclick="alert('Searching For Car')">Request Car</button>
    
    
</form>
</div>

<div id="googleMap" style="width:500;height:500px;"></div>
</div>

<div id="footer">
Copyright © CharlotteCarz.com
</div>

</body>
</html>

