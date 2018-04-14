<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Complete Request</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf" %>
<h2>MAKE COMPLETE REQUEST</h2>
<table>


<tr>
   <td><form action="Controller" method=POST>
		
				<select>
				<c:forEach var="auto" items="${autos}" >
  			<option value="${cr.setAuto_id(auto.getId())}">${auto.getName()}</option>
  			
  			</c:forEach>
				</select>
				<p>	<label>Date completed:</label></p>
		<p><input id="datepicker" name="date"></p>
				<br><input type="submit" value="Add auto to request"/>
				<input type="hidden" name="command" value="listComplReq"/>
			</form>
		</td>
</tr>
</table>
<h1>${autoTypeName}</h1>
<table border="1">
<tr>
<td>Auto Name</td>
<td>Date of broken</td>
</tr>
<c:forEach var="broke" items="${brokenAutos}">
<tr>

<td>${broke.getAutoName()}</td>
<td>${broke.getDateOfBroken()}</td>

</tr>
</c:forEach>
</table>
</body>
</html>