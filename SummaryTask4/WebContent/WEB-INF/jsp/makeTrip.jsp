<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
  
  <script>
  $(document).ready(function() {
    $("#datepicker").datepicker();
  });
  </script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make trip</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header1.jspf"%>
<h2>MAKE TRIP</h2>
	
 		 <form id="trip_input_form" action="Controller" method=POST>
		
		<table class="table-make">
		<tr class="tr-make">
		<td class="td-make"> <label>destination:</label></td><td class="td-make"><input type="text" name="dest"></td></tr>
 			<tr><td><label>from:</label></td><td><input type="text" name="from"></td></tr>
 		<tr class="tr-make">
 		<td class="td-make">	<label>date set off</label></td>
 		<td class="td-make">
		<p><input id="datepicker" name="date"></td></tr>
	<tr >
	<td  class="td-make2" colspan="2"><input type="submit"/></td>
	
	</tr>
				</table>
				
				<input type="hidden" name="command" value="listTripsDisp"/> 
	
		</form>
		
		
</body>
</html>