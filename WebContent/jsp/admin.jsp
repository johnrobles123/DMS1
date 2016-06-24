<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
	<head>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <title>Administrator</title>
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
	 </head>
	 
	 <body>		
		<div id="header">
			<h1>Device Monitoring System</h1>
		</div>
		
		<ul id="menu">
		  <li>Home</li>
		  <li>My Reservations</li>
		  <li>Admin</li>
		  <li>About</li>
		</ul>
		
		<font color="red">${message}</font>
		
		<div class="container">
		
			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
			<h1>Administration</h1>
			
			<h2>All Devices</h2>
			<div class="datagrid">
				<table>
					<thead>
						<tr>
							<th>Serial No</th>
							<th>Device Name</th>
							<th>Additional Info</th>
							<th>Action</th>
						</tr>
					</thead>
					<!-- <tfoot>
						<tr>
							<td colspan="4">
								<div id="paging">
									<ul>
										<li><a href="#"><span>Previous</span></a></li>
										<li><a href="#" class="active"><span>1</span></a></li>
										<li><a href="#"><span>2</span></a></li>
										<li><a href="#"><span>3</span></a></li>
										<li><a href="#"><span>4</span></a></li>
										<li><a href="#"><span>5</span></a></li>
										<li><a href="#"><span>Next</span></a></li>
									</ul>
								</div>
							</td>
						</tr>
					</tfoot> -->
					<tbody>
						<c:forEach var="device" items="${deviceList}">
						<tr>
							<td>${device.serialNo}</td>
							<td>${device.deviceName}</td>
							<td>${device.additionalInfo}</td>
							<td>
								<spring:url value="/device/${device.serialNo}" var="userUrl" />
								<spring:url value="/device/${device.serialNo}/delete" var="deleteUrl" /> 
								<spring:url value="/device/${device.serialNo}/update" var="updateUrl" />
		
								<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
								<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
								<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<h2>All Users</h2>
			<div class="datagrid">
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Username</th>
							<th>Password</th>
							<th>Action</th>
						</tr>
					</thead>
					<!-- <tfoot>
						<tr>
							<td colspan="4">
								<div id="paging">
									<ul>
										<li><a href="#"><span>Previous</span></a></li>
										<li><a href="#" class="active"><span>1</span></a></li>
										<li><a href="#"><span>2</span></a></li>
										<li><a href="#"><span>3</span></a></li>
										<li><a href="#"><span>4</span></a></li>
										<li><a href="#"><span>5</span></a></li>
										<li><a href="#"><span>Next</span></a></li>
									</ul>
								</div>
							</td>
						</tr>
					</tfoot> -->
					<tbody>
						<c:forEach var="user" items="${userList}">
						<tr>
							<td>${user.id}</td>
							<td>${user.uname}</td>
							<td>${user.password}</td>
							<td>
								<spring:url value="/user/${user.id}" var="userUrl" />
								<spring:url value="/user/${user.id}/delete" var="deleteUrl" /> 
								<spring:url value="/user/${user.id}/update" var="updateUrl" />
		
								<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
								<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
								<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		</div>
	</body>
</html>