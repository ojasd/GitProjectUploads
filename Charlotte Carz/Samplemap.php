<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>HOME</title>
	<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <link rel="stylesheet" type="text/css" href="map.css">
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

function showLocation() {
        geocoder.getLocations(document.forms[0].address1.value, function (response) {
            if (!response || response.Status.code != 200)
            {
                alert("Sorry, we were unable to geocode the first address");
            }
            else
            {
                autocomplete = {lat: response.Placemark[0].Point.coordinates[1], lon: response.Placemark[0].Point.coordinates[0], address: response.Placemark[0].address};
                geocoder.getLocations(document.forms[0].address2.value, function (response) {
                    if (!response || response.Status.code != 200)
                    {
                        alert("Sorry, we were unable to geocode the second address");
                    }
                    else
                    {
                        desAutocomplete = {lat: response.Placemark[0].Point.coordinates[1], lon: response.Placemark[0].Point.coordinates[0], address: response.Placemark[0].address};
                        calculateDistance();
                    }
                });
            }
        });
    }
    
    function calculateDistance()
    {
        try
        {
            var glatlng1 = new GLatLng(autocomplete.lat, desAutocomplete.lon);
            var glatlng2 = new GLatLng(desAutocomplete.lat, desAutocomplete.lon);
            var miledistance = glatlng1.distanceFrom(glatlng2, 3959).toFixed(1);
            var kmdistance = (miledistance * 1.609344).toFixed(1);

            document.getElementById('results').innerHTML = '<strong>Address 1: </strong>' + autocomplete.address + '<br /><strong>Address 2: </strong>' + desAutocomplete.address + '<br /><strong>Distance: </strong>' + miledistance + ' miles (or ' + kmdistance + ' kilometers)';
        }
        catch (error)
        {
            alert(error);
        }
    }

</script>
  </head>

<body onload="initialize()">
 
<div id="header" style="width: 100%">
<h1>Charlotte Carz</h1>

<ul>
  <li><a href="home.php">home</a></li>
  <li><a href="profile.php">profile</a></li>
  <li><a href="booking.php">Booking Status</a></li>
  <li><a href="contact.php">Contact</a></li>
  <li><a href="about.php">About</a></li>
</ul>
</div>

<div id="nav">
	<form action="#" onsubmit="calculateDistance()"; return "false";> 
	Source:<br/>
	<input id="autocomplete" placeholder="Enter your address" onFocus="geolocate(autocomplete)" type="text" size="70"><br/>			 
	Destination:<br/>
    <input id="desAutocomplete" placeholder="Enter your address" onFocus="geolocate(desAutocomplete)" type="text" size="70"><br/>
	<input type="submit" name="button" id="button" value="Request Car">
	</form>
	
	   <p id="results"></p>
</div>

<div id="googleMap" style="width:50;height:500px;">
</div>

<div id="footer">
Copyright © CharlotteCarz.com
</div>
  </body>
</html>


