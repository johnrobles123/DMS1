<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<spring:url value="/resources/css/style.css" var="styleCss" />
	<spring:url value="/resources/css/font-awesome.min.css" var="fontAwesomeCss" />
	<spring:url value="/resources/js/index.js" var="mainJs" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
	<link href="${styleCss}" rel="stylesheet" />
	<link href="${fontAwesomeCss}" rel="stylesheet" />
	<script src="${mainJs}"></script>
</head>
<body>
	<div id="header">
		<h1>Device Monitoring System</h1>
	</div>
	
	<div id="nav">
		<table style="width:100%">
		  <tr>
		    <td><p><a href = "/DMS1/dashboard">Home</a></p></td>
		    <td>My Reservations</td> 
		    <td><p><a href = "/DMS1/admin">Admin</a></p></td>
		    <td>About</td>
		    <td><p><a href = "/DMS1/logout">Logout</a></p></td>
		  </tr>
		</table>
	</div>
</body>
</html>