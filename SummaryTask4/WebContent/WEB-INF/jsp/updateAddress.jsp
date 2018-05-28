<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>

<title>Address</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf" %>
<form action="Controller" method="post">
<table class="table-make">
		<tr class="tr-make">
		<td class="td-make"> <label>Address name:</label></td>
		<td class="td-make"><input type="text" name="nameAdr" value="${adr.getName()}"></td>
		</tr>
		<tr>
	<td  class="td-make2" colspan="2"><input type="submit"/></td>
	
			</tr>
				</table>
				
				<input type="hidden" name="command" value="listAddress"/> 

</form>
</body>
</html>