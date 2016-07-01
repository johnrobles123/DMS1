<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Device List</title>
</head>
<body>

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>	

	<h1>Device Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">Device Serial Numer:</label>
		<div class="col-sm-10">${device.serialNo}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Device Name:</label>
		<div class="col-sm-10">${device.deviceName}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Additional Information</label>
		<div class="col-sm-10">${device.additionalInfo}</div>
	</div>

</div>

</body>
</html>