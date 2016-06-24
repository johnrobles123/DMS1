<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<script type='text/javascript' src='http://code.jquery.com/jquery-1.6.2.js'></script>
	    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.js"></script>
	    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
	    <link rel="stylesheet" type="text/css" href="http://trirand.com/blog/jqgrid/themes/ui.jqgrid.css">
	    <script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/i18n/grid.locale-en.js"></script>
	    <script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/jquery.jqGrid.min.js"></script>
	    <spring:url value="/resources/css/style.css" var="styleCss" />
		<spring:url value="/resources/css/font-awesome.min.css" var="fontAwesomeCss" />
		<spring:url value="/resources/js/index.js" var="mainJs" />
	    <script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
	  	<link href="${styleCss}" rel="stylesheet" />
	  	<link href="${fontAwesomeCss}" rel="stylesheet" />
  	    <script src="${mainJs}"></script>  	
		<title>Administration Screen</title>
	</head>
<body>
	<div id="header">
		<h1>Device Monitoring System</h1>
	</div>
	
	<div id="nav">
		<table style="width:100%">
		  <tr>
		    <td>Home</td>
		    <td>My Reservations</td> 
		    <td>Admin</td>
		    <td>About</td>
		  </tr>
		</table>
	</div>
	
	<font color="red">${message}</font>
	<div class="container">
			
		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>

		<h1>Administration Screen</h1>

		<table class="table table-striped" style="width:100%">
			<thead>
				<tr>
					<th>Serial_No</th>
					<th>Device_Name</th>
					<th>Additional_Info</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="device" items="${deviceList}">
				<tr>
					<td>
						${device.serialNo}
					</td>
					<td>${device.deviceName}</td>
					<td>${device.additionalInfo}</td>
					<td>
						<spring:url value="/device/${device.serialNo}" var="userUrl" />
						<spring:url value="/device/${device.serialNo}/delete" var="deleteUrl" /> 
						<spring:url value="/device/${device.serialNo}/update" var="updateUrl" />

						<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>
		
	    <div id="pageNavPosition" align="center"></div>
	    <script type="text/javascript">
            var pager = new Pager("devicejournaltable", 2);  
            pager.init();  
            pager.showPageNav('pager', 'pageNavPosition');  
            pager.showPage(1);
	    </script>
		<div style="width:30%; float:right">
			<tr align="left">
				<td><p><a href="reserve">Make a Reservation</a></p></td>
			</tr>
		</div>
	</div>

</body>
</html>