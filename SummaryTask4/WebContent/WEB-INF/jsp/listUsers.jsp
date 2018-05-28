<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/style/myStyle3.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header1.jspf"%>
<h2>LIST USERS</h2>
<form action="Controller" method="post"  >
<input type="hidden" name="command" value="regNewUser"/>
<input type="submit" value="Make new user" id="newUser"/>
</form>
<form action="Controller" method="POST">

<table>
<tr>

				<td id="mainTd">
				
				<table>
						<tr>
										
										<th>Login</th>
										<th>Role</th>
										
										<th>First name</th>
										<th>Last name</th>
										<th>Ban</th>
										<th>Action</th>
										<th>Delete</th>
									</tr>
									<c:forEach var="user" items="${listUs}" >
									<tr>
								<td>${user.getLogin()}</td>
											<c:if test="${user.getRoleId() == 1}">
									<td>Administrator</td>
									</c:if>
									<c:if test="${user.getRoleId() == 2}">
									<td>Driver</td>
									</c:if>
									<c:if test="${user.getRoleId() == 3}">
									<td>Dispatcher</td>
									</c:if>
									
										<td>${user.getFirstName()}</td>
											<td>${user.getSecondName()}</td>
									<c:if test="${user.isBan()== true}">
									<td ><div id="green" style="text-align: center;	background-color:green;	height: 20px;	width: 70px;	border:0px solid black;	border-bottom: 1px solid black;padding: 2px; color: white;">Not in ban</div> </td>
									<td><input type="radio" name="ban" value="${user.getId()} "/><label>Ban</label></td>
									</c:if>
									<c:if test="${user.isBan()== false}">
									<td > <div id="red" style="text-align: center;	background-color:red;	height: 20px;	width: 70px;	border:0px solid black;color: white;	border-bottom: 1px solid black;padding: 2px;">Banned</div></td>
									<td><input type="radio" name="Unban" value="${user.getId()} "/><label>Unban</label></td>						
									
									</c:if>
									<td>
									<a href="Controller?command=deleteUser&id=${user.getId()}">Delete</a>
										</td>
									</tr>
								
									
									</c:forEach>
									
				
				</table>
				</td>
				</tr>
			<tr>
			<td>
			<input type="hidden" name="command" value="makeBan">
			 <input type="submit" value="Do it"></td>
			</tr>

</table>


</form>

</body>
</html>