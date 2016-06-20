<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
</head>
<body>
	<h1>Add User</h1>
	<h2>User Details</h2>
	<font color="red">${message}</font>
	<form:form id="adduserForm" method="post" action="adduser"
		modelAttribute="AddUserBean">

		<form:label path="firstname">Enter your First Name :</form:label>
		<form:input id="firstname" name="firstname" path="" />
		<br>

		<form:label path="lastname">Enter your Last Name :</form:label>
		<form:input id="lastname" name="lastname" path="" />
		<br>

		<form:label path="isAdmin">Admin (Y/N) :</form:label>
		<form:input id="isAdmin" name="isAdmin" path="" />
		<br>

		<input type="save" value="Save">

	</form:form>
</body>
</html>
