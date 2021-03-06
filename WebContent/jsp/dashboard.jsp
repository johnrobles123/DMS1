<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
	<head>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <title>Dashboard</title>
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
		
		<div id="confirmReturnDialogue">Confirm return?</div>
		<div id="confirmCancelDialogue">Confirm cancellation?</div>
		
		<div class="container">
			<div class="filters">
				<table>
					<tr>
						<td>Select a Device Name :</td>
						<td> 
							<form action="listdevice" >
	                            <select id="ddDeviceList" onchange="ddListDeviceOnChange()">
	                            	<option value="*">ALL</option>
	                                <c:forEach var="devicelist" items="${deviceList}">
	                                    <!-- option value="${devicelist.serialNo}">${devicelist.deviceName}</option-->
										<c:choose>
											<c:when test="${devicelist.serialNo eq selectedDevice}">
												<option value="${devicelist.serialNo}" selected>${devicelist.deviceName}</option>
											</c:when>
											<c:otherwise>
												<option value="${devicelist.serialNo}">${devicelist.deviceName}</option>
											</c:otherwise>
										</c:choose>
	                                    
	                                </c:forEach>
	                            </select>
                        	</form>
                    	</td>
					</tr>
					<tr>
						<td>Current Status :</td>
						<td>${deviceStatus}</td>
					</tr>
				</table>
			</div>
		
			<div >
					<c:if test="${not empty msg}">
					    <div class="alert alert-${css} alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" 
			                                aria-label="Close">
							<span aria-hidden="true">�</span>
						</button>
						<strong>${msg}</strong>
					    </div>
					</c:if>
			
					<h1>Device Schedule</h1>
          
					<div class="datagrid">
						<table id="devicejournaltable">
							<thead>
								<tr>
									<th>Seq No</th>
									<th>Device Name</th>
									<th>User Name</th>
									<th>Reserve Date</th>
									<th>Time From</th>
									<th>Time To</th>
									<th>Action</th>
									<th id="thCenter" align="center" !important>Returned?</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="dj" items="${deviceJournal}">
									<tr>
										<td id="seqNo" value="${dj.seqNo}">${dj.seqNo}</td>
										<td>${dj.deviceName}</td>
										<td>${dj.userName}</td>
										<td>${dj.reserveDate}</td>
										<td>${dj.timeFrom}</td>
										<td>${dj.timeTo}</td>
										<td>
											<spring:url value="/viewreserve/${dj.seqNo}" var="queryReserveUrl" />
											<spring:url value="/reserve/${dj.seqNo}/update" var="updateReservationUrl" /> 
											<spring:url value="/reserve/${dj.seqNo}/cancel" var="cancelDeviceUrl" />
									  		<spring:url value="/reserve/${dj.seqNo}/return" var="returnDeviceUrl" />
					
											<button class="btn btn-info" onclick="location.href='${queryReserveUrl}'">Query</button>
											<button class="btn btn-primary" onclick="location.href='${updateReservationUrl}'">Update</button>
									  		<button class="btn btn-danger" id="cancelReservation" onclick="processCancel('${dj.seqNo}')">Cancel</button>
					                	</td>
						                <td width="10%" align="center">
										  	<input type="checkbox" class="checkbox" id="returned_chk", value="${dj.returned}" onclick="checkBoxValidation()" />
						                </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
				    <div id="pageNavPosition" align="center"></div>
				    <script type="text/javascript">
			            var pager = new Pager("devicejournaltable", 2);  
			            pager.init();  
			            pager.showPageNav('pager', 'pageNavPosition');  
			            pager.showPage(1);
				    </script>
					<div style="float:right">
						<button class="btn btn-default" id="reserveLink" href="reserve" style="float:right">Make a Reservation</button>
						<button class="btn btn-default" id="returnLink" href="returnLink" >Return</button>
					</div>
			</div>
		</div>
				
		<script>
			$("#returnLink").click(function() {
				$("#confirmReturnDialogue").dialog({
					modal: true,
					title: "Confirmation",
					buttons: {
						"YES": function() {
							processReturn();
							$(this).dialog("close");
							location.reload(true);
						},
						"NO": function() {
							$(this).dialog("close");
						}
					}
				});
			});
		</script>
	</body>
</html>