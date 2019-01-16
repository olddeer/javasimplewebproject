<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

		<%@ include file="/WEB-INF/jspf/header1.jspf" %>
	<h2>LIST OF TRIPS</h2>
	<form action="Controller" method=post id="mkTr">
					<input type="hidden" name="command" value="makeTrip"/>
					<input type="submit"  value="Make new trip" id="butMake">
					</form>
<table >

			
		<tr>
			<td>
			<%-- CONTENT --%>

			<c:choose>
			<c:when test="${fn:length(tripsDisp) == 0}">No such orders</c:when>
	
			<c:otherwise>
			<table  border="1">
				<thead>
					<tr>
						<th>№</th>
						<th>Date of creation</th>
						<th>Status</th>
						<th>Destination</th>
						<th>Date set off</th>
						<th>From</th>
						<th>Dispatcher</th>
					</tr>
				</thead>


				<c:forEach var="trip" items="${tripsDisp}">
					
					<tr>
						<td>${trip.getId()}</td>
						<td>${trip.getDateCreation()}</td>
						<td>${trip.getStatusName()}</td>
						<td>${trip.getDestination()}</td>
						<td>${trip.getDateSetOff().toString()}</td>
						<td>${trip.getFrom()}</td>
						<td>${trip.getDispatcherName()}</td>
					</tr>

				</c:forEach>
					
						
			</table>
			</c:otherwise>
			</c:choose>
						
			
			</td>
		</tr>
		
	</table>
		
</body>
</html>