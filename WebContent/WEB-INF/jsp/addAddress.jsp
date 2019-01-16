<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="style/myStyle2.css"/>


<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header1.jspf"%>
<h2>ADD ADRESS</h2>
 <form id="trip_input_form" action="Controller" method=POST>
		
		<table class="table-make">
 			<tr><td><label>Name of address:</label></td><td>
 			<input type="text" name="nameAdrC"/>
		</td></tr>
		<tr>
		<td colspan="2"><input  type="submit" id="butMake" value="Add address"/></td>
		</tr>
				</table>
				
				<input type="hidden" name="command" value="listAddress"/> 
				
		</form>
		
</body>
</html>