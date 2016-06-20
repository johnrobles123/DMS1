<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
</head>
<body>
	<form method="post" action="useradd.jsp">
		<center>
			<table border="1" width="30%" cellpadding="5">
				<thead>
					<tr>
						<th colspan="2">Enter Information Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>First Name</td>
						<td><input type="text" name="fname" value="" /></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input type="text" name="lname" value="" /></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value="" /></td>
					</tr>
					<tr>
						<td>User Name</td>
						<td><input type="text" name="uname" value="" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="pass" value="" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit" /></td>
					</tr>
					<tr>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
</body>
</html>
