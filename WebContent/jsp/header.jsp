<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />	
	<spring:url value="/resources/css/style.css" var="styleCss" />
	<spring:url value="/resources/css/font-awesome.min.css" var="fontAwesomeCss" />
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
	<spring:url value="/resources/js/index.js" var="mainJs" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${styleCss}" rel="stylesheet" />
	<link href="${fontAwesomeCss}" rel="stylesheet" />
	<script src="${mainJs}"></script>
</head>
<body>

	<div id="header">
		<h1 style="display: inline-block; margin-right: -66px;">Device Monitoring System</h1>
		<div id="welcome">
			<p style="margin:0">Welcome ${loggedInUser}</p>
			<a id="iconified" style="font-family: FontAwesome;" href="/DMS1/logout">Logout &#xf011;</a>
		</div>
		<!-- <a href="/DMS1/logout">Logout</a> -->
	</div>
	<ul class="nav nav-pills nav-justified">
	    <li><a href="/DMS1/dashboard">Home</a></li>
	    <li><a class="hsubs" href="#">My Reservations</a></li>
	    <li><a class="hsubs" href = "/DMS1/admin">Admin</a></li>
	    <li><a class="hsubs" href="#">About</a></li>
	</ul>
	<%-- <div id="nav">
		<form action="/DMS1/logout" method="post">
			<p>Welcome ${loggedInUser}</p>
			<table style="width:100%">
			  <tr>
			    <td><p><a href = "/DMS1/dashboard">Home</a></p></td>
			    <td>My Reservations</td> 
			    <td><p><a href = "/DMS1/admin">Admin</a></p></td>
			    <td>About</td>
			    <!-- td><p><a href = "/DMS1/logout">Logout</a></p></td-->
			    <td><input type="submit" value="Logout" /></td>
			  </tr>
			</table>
		</form>
	</div> --%>
</body>
</html>