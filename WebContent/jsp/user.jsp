<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<spring:url value="/user" var="addActionUrl" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
</head>
<body>
	<font color="red">${message}</font>
	<form:form class="form-horizontal" method="post"
		modelAttribute="addUser" action="${addActionUrl}">

		<center>
			<table border="2" width="25%" cellpadding="5">
				<thead>
					<tr>
						<th colspan="2">Enter User Information Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>First Name :</td>
						<td><input type="text" name="fname" id="fname"
							required="true" size="35" maxlength="50" /></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td>Last Name :</td>
						<td><input type="text" name="lname" id="lname"
							required="true" size="35" maxlength="50" /></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td>Email :</td>
						<td><input type="text" name="email" id="email" size="35"
							maxlength="50" /></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td>User Name :</td>
						<td><input type="text" name="uname" id="uname"
							required="true" size="35" maxlength="50" /></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td>Password :</td>
						<td><input type="password" name="password" id="password"
							required="true" size="35" maxlength="50" /></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td>Admin :</td>
						<td><select name="admin" id="admin" required="true" size="1"
							maxlength="1">
								<option></option>
								<option>N</option>
								<option>Y</option>
						</select></td>
				</tbody>
				<tbody>
					<tr>
						<td></td>
						<td><input type="submit" value="Submit" /></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form:form>
</body>
</html>
