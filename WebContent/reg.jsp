<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/myStyle2.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<form action="Controller" method="post">
<table class="table-make">
		<tr class="tr-make">
		<td class="td-make"> <label>Login:</label></td>
		<td class="td-make"><input type="text" name="log"></td>
		</tr>
 			<tr><td><label>Password:</label></td><td><input type="text" name="pasw"></td></tr>
 		<tr class="tr-make">
 		<td class="td-make">	<label>First name</label></td>
 		<td class="td-make">
		<p><input id="text" name="fname"></td></tr>
		
		<tr class="tr-make">
 		<td class="td-make">	<label>Last name</label></td>
 		<td class="td-make">
		<p><input id="text" name="lname"></td></tr>
	<tr class="tr-make">
 		<td class="td-make">	<label>Email</label></td>
 		<td class="td-make">
		<p><input id="text" name="mail"></td></tr>
	
	<tr class="tr-make">
 		<td class="td-make">	<label>Role</label></td>
 		<td class="td-make">
		<p><select name="role">
		
		<option value="1">Administrator</option>
		<option value="2">Driver</option>
		<option value="3">Dispatcher</option>
		</select></td></tr>
		<tr >
	<td  class="td-make2" colspan="2"><input type="submit"/></td>
	
	</tr>
				</table>
				
				<input type="hidden" name="command" value="reg"/> 

</form>
</body>
</html>