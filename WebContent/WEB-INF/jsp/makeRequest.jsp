<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>

<title>Make request</title>
</head>
<body>
<h2>MAKE REQUEST</h2>
<form action="Controller" method=POST>
<table border="1">
<%@ include file="/WEB-INF/jspf/header1.jspf" %>
		
		<tr>
					<th>Date of creation</th>
					<th>Trip number</th>
					<th>Auto type name</th>
					<th>Driver login</th>
			</tr>
			<c:forEach var="req" items="${listReq}">
			
			<tr>
			<td>${req.getDateCreation()}</td>
			<td>${req.getTripId()}</td>
			<td>${req.getAutoTypeName()}</td>
			<td>${req.getDriverName()}</td>
			</tr>
			
			</c:forEach>
			

</table>


</form>
</body>
</html>