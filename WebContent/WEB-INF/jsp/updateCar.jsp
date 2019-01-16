<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>

<title>Update auto</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf" %>
<form action="Controller" method="post">
<table class="table-make">
		<tr class="tr-make">
		<td class="td-make"> <label>Auto name:</label></td>
		<td class="td-make"><input type="text" name="name" value="${auto.getName()}"></td>
		</tr>

	<tr class="tr-make">
 		<td class="td-make">	<label>Auto status</label></td>
 		<td class="td-make">
		<p><select name="autoStatus" >
		<c:choose>
		<c:when test="${ auto.getAutoStatusId() == 1}">
		<option value="1" selected>Ready</option>
		</c:when>
		<c:otherwise>
		<option value="1">Ready</option>
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${ auto.getAutoStatusId() == 2}">
		<option value="2" selected>Busy</option>
		</c:when>
		<c:otherwise>
		<option value="2">Busy</option>
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${ auto.getAutoStatusId() == 3}">
		<option value="3" selected>Fallenout</option>
		</c:when>
		<c:otherwise>		
		<option value="3">Fallenout</option>
		</c:otherwise>
		</c:choose>
		</select></td></tr>
	<tr class="tr-make">
 		<td class="td-make">	<label>Auto type</label></td>
 		<td class="td-make">
		<p><select name="autoType">
		<c:choose>
		<c:when test="${auto.getAutoTypeId() == 1}">
		<option value="3">Sedan</option>
		</c:when>
		<c:otherwise>		
		<option value="3">Sedan</option>
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test="${auto.getAutoTypeId() == 2}">
		<option value="2">Van</option>
		</c:when>
		<c:otherwise>		
		<option value="2">Van</option>
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${auto.getAutoTypeId() == 3}">
		<option value="1">Truck</option>
		</c:when>
		<c:otherwise>		
		<option value="1">Truck</option>
		</c:otherwise>
		</c:choose>
			</select></td></tr>
		<tr >
	<td  class="td-make2" colspan="2"><input type="submit"/></td>
	
	</tr>
				</table>
				
				<input type="hidden" name="command" value="listAutos"/> 

</form>
</body>
</html>