<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add auto</title>
<link rel="stylesheet" type="text/css" href="style/myStyle2.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf"%>
<h2>ADD AUTO</h2>
 <form id="trip_input_form" action="Controller" method=POST>
		
		<table class="table-make">
		<tr class="tr-make">
		<td class="td-make"> <label>Auto type:</label></td><td class="td-make">
		
		<select name="autoType">
		<c:forEach var="type" items="${listAut}">
		<option value="${type.getId()}">${type.getName()}</option>
		</c:forEach>
		</select>
		
		</td></tr>
 			<tr><td><label>Name of auto:</label></td><td>
 			<input type="text" name="nameAut"/>
		</td></tr>
		<tr>
		<td colspan="2"><input type="submit" id="butMake" value="Add auto"/></td>
		</tr>
				</table>
				
				<input type="hidden" name="command" value="listAutos"/> 
				
		</form>
		

</body>
</html>