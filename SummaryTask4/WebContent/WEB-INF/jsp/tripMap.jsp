<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>

<style>
	#map {
  height: 500px;
  width: 800px;
  margin-left: 40px;
}

#floating-panel {
  position: absolute;
  top: 10px;
  left: 25%;
  z-index: 5;
  background-color: #fff;
  padding: 5px;
  border: 1px solid #999;
  text-align: center;
  font-family: 'Roboto','sans-serif';
  line-height: 30px;
  padding-left: 10px;
}
#right-panel {
  font-family: 'Roboto','sans-serif';
  line-height: 30px;
  padding-left: 10px;
}

#right-panel select, #right-panel input {
  font-size: 15px;
}

#right-panel select {
  width: 100%;
}

#right-panel i {
  font-size: 12px;
}
#right-panel {
  height: 100%;
  float: right;
  width: 390px;
  overflow: auto;
}
#map {
  margin-right: 400px;
}
#floating-panel {
  background: #fff;
  padding: 5px;
  font-size: 14px;
  font-family: Arial;
  border: 1px solid #ccc;
  box-shadow: 0 2px 2px rgba(33, 33, 33, 0.4);
  display: none;
}
@media print {
  #map {
    height: 500px;
    margin: 0;
  }
  #right-panel {
    float: none;
    width: auto;
  }
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trip map</title>
</head>
<body>
<script async defer
src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDx-ClYLnxzRarkv2MqPQS7MTZp_R6D99s&callback=initMap">
</script>
<c:set var="trip" value="${trip}"/>
<script>
function initMap() {
	  var directionsDisplay = new google.maps.DirectionsRenderer;
	  var directionsService = new google.maps.DirectionsService;
	  var map = new google.maps.Map(document.getElementById('map'), {
	    zoom: 7,
	    center: {lat: 50.431782, lng: 30.516382}
	  });
	  directionsDisplay.setMap(map);
	  directionsDisplay.setPanel(document.getElementById('right-panel'));
	    calculateAndDisplayRoute(directionsService, directionsDisplay);
	}
		
	function calculateAndDisplayRoute(directionsService, directionsDisplay) {
	  var start = "${trip.getFrom()}";
	  var end = "${trip.getDestination()}";
	  directionsService.route({
	    origin: start,
	    destination: end,
	    travelMode: 'DRIVING'
	  }, function(response, status) {
	    if (status === 'OK') {
	      directionsDisplay.setDirections(response);
	    } else {
	      window.alert('Directions request failed due to ' + status);
	    }
	  });
	}
</script>
<%@ include file="/WEB-INF/jspf/header1.jspf" %>

<div id="right-panel"></div>
 <div id="map"></div>
</body>
</html>