<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/style/myStyle2.css"/> 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logging</title>
</head>
<body>


<form id="login_form" action="Controller" method="post">
	
					<input type="hidden" name="command" value="login"/>

							<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/header1.jspf"%>

					<input type="hidden" name="command" value="login"/>

					
						
						<input type="text" name="login" placeholder="Login"/><br/>
						<input type="password" name="password" placeholder="Password"/><br/>
								
					<input type="submit" value="Login">	
					
				</form>
				
			<%-- CONTENT --%>

</body>
</html>