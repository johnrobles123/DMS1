<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
	<head>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <title>jqGrid Example</title>
	    <script type='text/javascript' src='http://code.jquery.com/jquery-1.6.2.js'></script>
	    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.js"></script>
	    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
	    <link rel="stylesheet" type="text/css" href="http://trirand.com/blog/jqgrid/themes/ui.jqgrid.css">
	    <script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/i18n/grid.locale-en.js"></script>
	    <script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/jquery.jqGrid.min.js"></script>
	  <style type='text/css'>
		#header {
		    background-color:black;
		    color:white;
		    text-align:center;
		    padding:5px;
		}
		#nav {
		    line-height:30px;
		    background-color:#eeeeee;
		    padding:5px;
		}
		#section {
		    width:100%;
		    float:left;
		    padding:10px;
		}
		.devicejournaltable thead tr th {
			text-align:left;
		}
		#footer {
		    background-color:black;
		    color:white;
		    clear:both;
		    text-align:center;
		    padding:5px;
		}
	  </style>	   
	   
	  <!-- script type='text/javascript'>
	   jQuery(document).ready(function () {
	  
	             jQuery("#projectTable").jqGrid({
	                 url: "dashboard",
	                 datatype: "json",
	                 jsonReader: {repeatitems: false, id: "ref"},
	                 //colNames:['ID','Reservation Date', 'User Name', 'Device Name', 'Status', 'Cancel?', 'Verify Return'],
	                 colNames:['ID','Date', 'Device Name', 'Reserve Date'],
	                 colModel:[
	                     {name:'id',index:'id', width:100},
	                     {name:'dateTime',index:'dateTime', width:100},
	                     {name:'deviceName',index:'deviceName', width:200},
	                     {name:'reserveDate',index:'reserveDate', width:200}
	                 ],
	                 rowNum:20,
	                 rowList:[20,60,100],
	                 height:300,
	                 pager: "#pagingDiv",
	                 viewrecords: true,
	                 caption: "Reservation Journal"
	             });
	              
	             $("#pcSelect").change(function(){
	                 var postcode = $("#pcSelect").val();
	                 jQuery("#projectTable").jqGrid(
	                         "setGridParam",{
	                             url:"dashboard",
	                             page:1})
	                         .trigger("reloadGrid");
	             });
	         });
	  </script-->
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
		
		<div id="section">
			<div class="filters">
				<table>
					<tr>
						<td>Select a Device Name :</td>
						<td> 
							<form action="listdevice">
	
	                            <select name="ddDeviceList">
	                                <c:forEach var="devicelist" items="${deviceList}">
	                                    <option value="${devicelist.serialNo}">${devicelist.deviceName}</option>
	                                </c:forEach>
	                            </select>
                        	</form>
                    	</td>
					</tr>
					<tr>
						<td>Current Status :</td>
						<td><input type="text" name="status" disabled></td>
					</tr>
				</table>
			</div>
		
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
			
					<h1>Device Schedule</h1>
			
					<table class="devicejournaltable" style="width:100%">
						<thead>
							<tr>
								<th>#ID</th>
								<th>Date</th>
								<th>Device Name</th>
								<th>Reserve Date</th>
								<th>Action</th>
							</tr>
						</thead>
			
						<c:forEach var="user" items="${deviceJournal}">
						    <tr>
								<td width="10%">${user.id}</td>
								<td width="15%">${user.dateTime}</td>
								<td width="20%">${user.deviceName}</td>
								<td width="15%">${user.reserveDate}</td>
								<td width="40%">
								  <spring:url value="/users/${user.id}" var="userUrl" />
								  <spring:url value="/users/${user.id}/delete" var="deleteUrl" /> 
								  <spring:url value="/users/${user.id}/update" var="updateUrl" />
				
								  <button class="btn btn-info" 
				                                          onclick="location.href='${userUrl}'">Query</button>
								  <button class="btn btn-primary" 
				                                          onclick="location.href='${updateUrl}'">Update</button>
								  <button class="btn btn-danger" 
				                                          onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
				                </td>
						    </tr>
						</c:forEach>
					</table>
			
				</div>
			<!-- form:form id="dashboardForm" method="post" action="dashboard" modelAttribute="deviceJournal">
			  		<table id="grid"></table>
			         <div>
			          <div style="float: left;">
			              <table id="projectTable"></table>
			              <div id="pagingDiv"></div>
			          </div>
			         </div>
				<center>Welcome ${deviceJournal}</center>
			</form:form-->
		</div>
	</body>
</html>