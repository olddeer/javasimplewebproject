<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Autos</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf" %>
<form action="Controller" method="post">
<h2>LIST OF AUTOS</h2>
<table border="1">
				<tr>
					<th>Auto name</th>
					<th>Auto type name</th>
					<th>Auto status</th>
					<th> </th>
					<th> </th>
				</tr>
			<c:forEach var="auto" items="${listAut}">
			<tr>
			<td>${auto.getName()}</td>
			<td>${auto.getAutoTypeName()}</td>
			<td>${auto.getStatusName()}</td>
			<td><a href="Controller?command=deleteCar&id=${auto.getId()}">Delete</a></td>
			<td><a href="Controller?command=selectUpdateCar&id=${auto.getId()}">Update</a></td>
			</tr>
			</c:forEach>
			<tr>
			<td colspan="6">  <input type="submit" id="butMake" value="Add auto"></input>
  </td>
			</tr>
   </table>
  <input type="hidden" name="command" value="addAuto"/>
</form>
</body>
</html>