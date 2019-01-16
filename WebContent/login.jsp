<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.nure.sutyagin.SummaryTask4.resources.resources" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>

 <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/style/myStyle2.css"/> 
<style>
   .c {
    border: 1px solid #333; 
    display: inline-block;
    padding: 5px 15px; 
    text-decoration: none; 
    color: #000; /* Цвет текста */
  }
   .c:hover {
    box-shadow: 0 0 5px rgba(0,0,0,0.3); /* Тень */
    background: linear-gradient(to bottom, #fcfff4, #e9e9ce); /* Градиент */
    color: #a00;
   }
  </style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Logging</title>
</head>
<body>
<form action="Controller" method="post">
<select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
                       </select>
                       <input type="hidden" name="command" value="setLocale"/>
                       <input type="hidden" name="page" value="/login.jsp"/>
</form>
<%@ include file="/WEB-INF/jspf/header1.jspf"%>


<form id="login_form" action="Controller" method="post">
	
					<fmt:message key="login.placeholder.log" var="logPlace"/>
			<fmt:message key="login.placeholder.password" var="logPasw"/>
			<fmt:message key="login.submit" var="acpt"/>
		<fmt:message key="login.registration" var="reg"/>
							<c:set var="title" value="Login" />

					<input type="hidden" name="command" value="login"/>

					
						
						<input type="text" name="login" placeholder="${logPlace}"/><br/>
						<input type="password" name="password" placeholder="${logPasw} "/><br/>
								
					<input type="submit" value="${acpt}">	
					<a id="c" href="reg.jsp">${reg}</a>
				</form>
				
			

</body>
</html>