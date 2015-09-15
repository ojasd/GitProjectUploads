<!DOCTYPE html>
<html>
  <head>
    <title>HOME</title>
    
    <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
    <script>
// This example displays an address form, using the autocomplete feature
// of the Google Places API to help users fill in the information.

var placeSearch, autocomplete,desAutocomplete;
var componentForm = {
  street_number: 'short_name',
  route: 'long_name',
  locality: 'long_name',
  administrative_area_level_1: 'short_name',
  country: 'long_name',
  postal_code: 'short_name'
};

function initialize() {
	var mapProp = {
    center:new google.maps.LatLng(35.2269,-80.8433),
    zoom:8,
    mapTypeId:google.maps.MapTypeId.ROADMAP
  };
  var map=new google.maps.Map(document.getElementById("googleMap"), mapProp);
  // Create the autocomplete object, restricting the search
  // to geographical location types.
  desAutocomplete = new google.maps.places.Autocomplete(
      /** @type {HTMLInputElement} */(document.getElementById('desAutocomplete')),
      { types: ['geocode'] });
  autocomplete = new google.maps.places.Autocomplete(
      /** @type {HTMLInputElement} */(document.getElementById('autocomplete')),
      { types: ['geocode'] });
  // When the user selects an address from the dropdown,
  // populate the address fields in the form.
  google.maps.event.addListener(autocomplete, 'place_changed', function() {
    fillInAddress(autocomplete);
  });
  google.maps.event.addListener(desAutocomplete, 'place_changed', function() {
    fillInAddress(desAutocomplete);
  });
  google.maps.event.addDomListener(window, 'load', initialize);
}

// [START region_fillform]
function fillInAddress(auto) {
  // Get the place details from the autocomplete object.
  var place = auto.getPlace();

  for (var component in componentForm) {
    document.getElementById(component).value = '';
    document.getElementById(component).disabled = false;
  }

  // Get each component of the address from the place details
  // and fill the corresponding field on the form.
  for (var i = 0; i < place.address_components.length; i++) {
    var addressType = place.address_components[i].types[0];
    if (componentForm[addressType]) {
      var val = place.address_components[i][componentForm[addressType]];
      document.getElementById(addressType).value = val;
    }
  }
}
// [END region_fillform]

// [START region_geolocation]
// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate(id) {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var geolocation = new google.maps.LatLng(
          position.coords.latitude, position.coords.longitude);
      var circle = new google.maps.Circle({
        center: geolocation,
        radius: position.coords.accuracy
      });
      id.setBounds(circle.getBounds());
    });
  }
}
// [END region_geolocation]

    </script>

    <style>
	#header {
    background-color:black;
    color:white;
    text-align:center;
    padding:5px;
}
#nav {
    line-height:30px;
    background-color:"black";
    height:300px;
    width:500px;
    float:left;
    padding:5px;	      
}
#footer {
    background-color:#F5DA81;
    color:white;
    clear:both;
    text-align:center;
   padding:5px;	 	 
}
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
}

li {
    float: left;
}

a:link, a:visited {
    display: block;
    width: 280px;
    font-weight: bold;
    color: #FFFFFF;
    background-color: black;
    text-align: center;
    padding: 10px;
    text-decoration: none;
    text-transform: uppercase;
}

a:hover, a:active {
    background-color: #7A991A;
}

      
    </style>
  </head>

  <body onload="initialize()">
  
  
<div id="header" style="width: 100%;">
<h1>Charlotte Carz
    
</h1>
</>



<ul>
  <li><a href="home.php">home</a></li>
  <li><a href="profile.php">profile</a></li>
  <li><a href="booking.php">Booking Status</a></li>
  <li><a href="contact.php">Contact</a></li>
  <li><a href="about.php">About</a></li>
</ul>
</div>

    <div id="nav">
	<form>
	Source:<br/>
      <input id="autocomplete" placeholder="Enter your address"
             onFocus="geolocate(autocomplete)" type="text" size="70"></input>
			 <br/>
			 Destination:<br/>
      <input id="desAutocomplete" placeholder="Enter your address"
             onFocus="geolocate(desAutocomplete)" type="text" size="70"></input>
			  <button type="button" onclick="alert('Searching For Car')">Request Car</button>
			 </form>
    </div>
	
<div id="googleMap" style="width:50;height:500px;">
</div>
	

</div><div id="footer">
Copyright © CharlotteCarz.com
</div>
  </body>
</html>