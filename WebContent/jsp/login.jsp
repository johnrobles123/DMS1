<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<spring:url value="/resources/css/style.css" var="styleCss" />
		<spring:url value="/resources/css/font-awesome.min.css" var="fontAwesomeCss" />
		<spring:url value="/resources/js/index.js" var="mainJs" />
		<spring:url value="https://code.jquery.com/jquery-3.0.0.min.js" var="jqueryJs" /> 
	  	<link href="${styleCss}" rel="stylesheet" />
	  	<link href="${fontAwesomeCss}" rel="stylesheet" />
	  	<script src="${jqueryJs}"></script>
  	    <script src="${mainJs}"></script>  	
		<title>Login</title>
	</head>
	<body>
		<div class="login-page">
			<div class="form">
				<div class="errormsg">${message}</div>
				<form id="loginForm" method="post" action="login" modelAttribute="loginBean" class="login-form">
					<label path="username">Enter your user-name</label>
					<input id="username iconified" class="empty" name="username" path="" placeholder="&#xf007 Username" required/><br>
					<label path="username">Please enter your password</label>
					<input type="password" id="password iconified" class="empty" name="password" path="" placeholder="&#xf084 Password" required/><br>
					<button type="submit" value="Submit" />login</button>
				</form>
			</div>
		</div>
	</body>
</html>