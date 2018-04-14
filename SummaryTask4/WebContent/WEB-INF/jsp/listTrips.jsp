<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<%@ include file="/WEB-INF/jspf/header1.jspf"%>
<h2>LIST TRIPS</h2>
		<form action="Controller" method="post" id="sortForm" >
				<input type="hidden" name="command"
										value="listTrips" />
					<select name="sort" id="sortSel">
					<option value="byDate">Date</option>
					<option value="byStatus">Status</option>
					<option value="byNumber">Number</option>
					</select>
					 <input type="submit"
										value="Sort" id="sortBut"/>
				</form>
	<form action="Controller" method=POST>
		<table>
			<tr>
				<td id="mainTd">
					 <c:choose>
						<c:when test="${fn:length(trips) == 0}">No such orders</c:when>

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


								<c:forEach var="trip" items="${trips}">

									<tr>
										<td>${trip.getId()}</td>
										<td>${trip.getDateCreation()}</td>
										<td>${trip.getStatusName()}</td>
										<td>${trip.getDestination()}</td>
										<td>${trip.getDateSetOff().toString()}</td>
										<td>${trip.getFrom()}</td>
										<td>${trip.getDispatcherName()}</td>
										<c:if test="${trip.getStatusName() == 'open'}">
											
											<td><input type="radio" name="tripId"
												value="${trip.getId()}" />Make a request</td>
										</c:if>
									</tr>

								</c:forEach>
							</table>
							
							<div id="typeChoose">
								
											<select name="autoTypeId" id="selAut">
											<c:forEach var="type" items="${auto_types}">
												<option value="${type}">${type}</option>
											</c:forEach>
									</select>
	
									<input type="hidden" name="command"	value="makeRequest" />
									 <input type="submit"	value="Make a request" id="subAut"/>
					</div>
												</c:otherwise>
					</c:choose> 
											
		</table>
	</form>
</body>
</html>