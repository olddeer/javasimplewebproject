<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.nure.sutyagin.SummaryTask4.resources.resources" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<fmt:message key="list.trips.sort.date" var="sDate"/>
<fmt:message key="list.trips.sort.status" var="sStat"/>
<fmt:message key="list.trips.sort.number" var="Num"/>
<fmt:message key="list.trips.header.date" var="hDate"/>
<fmt:message key="list.trips.header.status" var="hStat"/>
<fmt:message key="list.trips.header.destination" var="Dest"/>
<fmt:message key="list.trips.header.date.setoff" var="setOff"/>
<fmt:message key="list.trips.header.from" var="from"/>
<fmt:message key="list.trips.header.dispatcher" var="disp"/>
<fmt:message key="list.trips.button.sort" var="sort"/>
<fmt:message key="list.trips.button.makereq" var="make"/>
<fmt:message key="list.trips.header" var="header2"/>
	<%@ include file="/WEB-INF/jspf/header1.jspf"%>
	<form action="Controller" method="post">
<select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
                       </select>
                       <input type="hidden" name="command" value="setLocale"/>
                       <input type="hidden" name="page" value="/WEB-INF/jsp/listTrips.jsp"/>
</form>
<h2>${header2}</h2>
		<form action="Controller" method="post" id="sortForm" >
				<input type="hidden" name="command"
										value="listTrips" />
					<select name="${sort}" id="sortSel">
					<option value="byDate">${sDate}</option>
					<option value="byStatus">${sStat }</option>
					<option value="byNumber">${Num}</option>
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
										<th>${hDate}</th>
										<th>${hStat }</th>
										<th>${Dest }</th>
										<th>${setOff }</th>
										<th>${from }</th>
										<th>${disp }</th>
									</tr>
								</thead>


								<c:forEach var="trip" items="${trips}">

									<tr>
										<td><a href="Controller?command=map&id=${trip.getId()}">${trip.getId()}</a></td>
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
									 <input type="submit"	value="${make}" id="subAut"/>
					</div>
												</c:otherwise>
					</c:choose> 
											
		</table>
	</form>
</body>
</html>