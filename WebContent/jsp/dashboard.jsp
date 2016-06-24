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
			
					<table id="devicejournaltable" class="fullwidth">
						<thead>
							<tr>
								<th>Seq No</th>
								<th>Device Name</th>
								<th>User Name</th>
								<th>Reserve Date</th>
								<th>Time From</th>
								<th>Time To</th>
								<th>Action</th>
							</tr>
						</thead>
			
						<c:forEach var="dj" items="${deviceJournal}">
						    <tr>
								<td width="10%">${dj.seqNo}</td>
								<td width="15%">${dj.deviceName}</td>
								<td width="15%">${dj.userName}</td>
								<td width="10%">${dj.reserveDate}</td>
								<td width="10%">${dj.timeFrom}</td>
								<td width="10%">${dj.timeTo}</td>
								<td width="30%">
								  <spring:url value="/reserve/${dj.seqNo}" var="queryReserveUrl" />
								  <spring:url value="/reserve/${dj.seqNo}/update" var="updateReservationUrl" /> 
								  <spring:url value="/reserve/${dj.seqNo}/return" var="returnDeviceUrl" />
				
								  <button class="btn btn-info" 
				                                          onclick="location.href='${queryReserveUrl}'">Query</button>
								  <button class="btn btn-primary" 
				                                          onclick="location.href='${updateReservationUrl}'">Update</button>
								  <button class="btn btn-danger" 
				                                          onclick="this.disabled=true;post('${deleteReservationUrl}')">Delete</button>
				                </td>
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
		</div>
	</body>
</html>