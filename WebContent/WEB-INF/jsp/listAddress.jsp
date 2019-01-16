<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>

<title>Address</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf"%>
<form action="Controller" method="post">
<h2>LIST OF ADDRESS</h2>
<table id ="adr" border="1">
				<tr>
					<th>Address name</th>
					<th> </th>
					<th> </th>
				</tr>
			<c:forEach var="adr" items="${listAdr}">
			<tr>
			<td>${adr.getName()}</td>
			<td><a href="Controller?command=deleteAdr&id=${adr.getId()}">Delete</a></td>
			<td><a href="Controller?command=updateAdr&id=${adr.getId()}">Update</a></td>
			</tr>
			</c:forEach>
			<tr>
			<td colspan="3">  <input type="submit" id="butMake" value="Add adr"></input>
  </td>
			</tr>
   </table>
  <input type="hidden" name="command" value="addAddress"/>
</form>
</body>
</html>