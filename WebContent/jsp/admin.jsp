<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>


	<head>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <title>Administrator</title>
	    <!-- <script type='text/javascript' src='http://code.jDetails.com/jDetails-1.6.2.js'></script> 
	    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jDetailsui/1.8.14/jDetails-ui.js"></script>
	    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jDetailsui/1.8.14/themes/base/jDetails-ui.css"> 
	    <link rel="stylesheet" type="text/css" href="http://trirand.com/blog/jqgrid/themes/ui.jqgrid.css">-->
	    <!-- <script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/jDetails.jqGrid.min.js"></script>  -->
		<!-- <script type="text/javascript" src="https://code.jDetails.com/jDetails-3.0.0.min.js"></script> -->
   	       
	    <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
	    <spring:url value="/resources/css/style.css" var="styleCss" />
		<spring:url value="/resources/css/font-awesome.min.css" var="fontAwesomeCss" />
		<spring:url value="/resources/css/jquery-ui.css" var="jqueryuiCss" />
		
		<spring:url value="/resources/js/index.js" var="mainJs" />
		<spring:url value="/resources/js/grid.locale-en.js" var="gridJs" />
		<spring:url value="/resources/js/jquery-1.6.2.js" var="jqueryJs" />
		<spring:url value="/resources/js/jquery-ui.js" var="jqueryuiJs" />
		
		<link href="${bootstrapCss}" rel="stylesheet" />
		<link href="${styleCss}" rel="stylesheet" />
	  	<link href="${fontAwesomeCss}" rel="stylesheet" />
	  	<link href="${jqueryuiCss}" rel="stylesheet" />
	  	
  	    <script src="${mainJs}"></script>  	
  	    <script src="${gridJs}"></script>  	
  	    <script src="${jqueryJs}"></script>  	
  	    <script src="${jqueryuiJs}"></script> 
	 </head>
	 
	 <body>		
		<font color="red">${message}</font>
    
    	<div id="confirmDeleteDialogue" style="display:none">Are you sure you want to delete the record?</div>	

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

			<spring:url value="/devicelist/${device.serialNo}" var="userUrl" />
			<spring:url value="/devicelist/add" var="addUrl" /> 
			<button class="btn btn-default" id="addDevice" onclick="location.href='${addUrl}'">Add New Device</button>
			
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
					
					<tbody>
						<c:forEach var="device" items="${deviceList}">
							<tr>
								<td>
									${device.serialNo}
								</td>
								<td>${device.deviceName}</td>
								<td>${device.additionalInfo}</td>
								<td>
									<spring:url value="/devicelist/${device.serialNo}" var="userUrl" />
									<spring:url value="/devicelist/add" var="addUrl" /> 
									<spring:url value="/devicelist/${device.serialNo}/delete" var="deleteUrl" /> 
									<spring:url value="/devicelist/${device.serialNo}/update" var="updateUrl" />
								
									<button class="btn btn-info" onclick="location.href='${userUrl}'">Details</button>
									<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
									<button id="deleteBtn" class="btn btn-danger" onclick="confirmDelete('${device.serialNo}')">Delete</button></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
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
					</tfoot>
				</table>
			</div>
			<br>
			<h2>All Users</h2>
			<button class="btn btn-default" id="addUser" href="addUser">Add New User</button>			
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
		
								<button class="btn btn-info" onclick="location.href='${userUrl}'">Details</button>
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