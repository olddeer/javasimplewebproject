<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Requests</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf" %>
<form action="Controller" method="post">
<h2>LIST REQUESTS</h2>
<table border="1">

		
		<tr>
					<th>Date of creation</th>
					<th>Trip number</th>
					<th>Auto type name</th>
					
			</tr>
			<c:forEach var="req" items="${listReqDisp}">
			
			<tr>
		
			<td>${req.getDateCreation()}</td>
			<td>${req.getTripId()}</td>
			<td>${req.getAutoTypeName()}</td>
			
			<c:if test="${userRole.name == 'dispatcher'}">
			<td><input type="radio"  name="req" value="${req.getId()}"/>Make a full request</td>						
			</c:if>
			
			</tr>
			
			</c:forEach>
			<tr>
			<c:if test="${userRole.name == 'dispatcher'}">
				<td>
				
					<input type="hidden" name="command" value="makeCompleteRequest"/>
					<input type="submit" value="Make a complete request">
				
				</td>
			</c:if>
			</tr>
					

   </table>
</form>
</body>
</html>