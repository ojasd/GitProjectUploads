<?php

$postcode1=$_POST['autocomplete'];
$postcode2=$_POST['desAutocomplete'];
$result=array();

$url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=$postcode1&destinations=$postcode2&mode=driving&language=en-EN&sensor=false";

$data = @file_get_contents($url);

$result = json_decode($data, true);
print_r($result);


?>