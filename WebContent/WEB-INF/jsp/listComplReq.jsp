<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Completed requests</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf"%>
	<form action="Controller" method=POST>
		<h2>LIST COMPLETED REQUESTS</h2>
		<table border="1">
			<tr>
				<th>Trip number</th>
				<th>Dispatcher login</th>
				<th>Driver login</th>
				<th>Auto name</th>
				<th>Destination</th>
				<th>From</th>
				<th>Date completed</th>
			</tr>
			<c:forEach var="req" items="${listComplReq}">

				<tr>
					<td>${req.getTripId()}</td>
					<td>${req.getDispatcherLogin()}</td>
					<td>${req.getDriverLogin()}</td>
					<td>${req.getAutoName()}</td>
					<td>${req.getDestination()}</td>
					<td>${req.getFrom()}</td>
					<td>${req.getDateCompleted()}</td>
					
					<c:if test="${userRole.name == 'driver'}">
					<c:if test="${req.getStatusTrip()=='inprogress'}">
						<td><input type="radio" name="reqId" value="${req.getId()}" />Make
							a full request</td>
							</c:if>
					</c:if>
				</tr>

			</c:forEach>
		
		</table>
		<c:if test="${userRole.getName().equals('driver')}">
					<div id="compl"><select name="stats">
							<c:forEach var="stat" items="${autoStats}">
								<option value="${stat.getId()}">${stat.name}</option>
							</c:forEach>
					</select>
					<input type="hidden" name="command" value="listTrips" />
						<input type="submit" value="Trip is completed" /></div>
				</c:if>

	</form>
</body>
</html>