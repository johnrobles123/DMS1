<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Device List</title>

<script type='text/javascript' src='http://code.jquery.com/jquery-1.6.2.js'></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="http://trirand.com/blog/jqgrid/themes/ui.jqgrid.css">
<script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/i18n/grid.locale-en.js"></script>
<script type='text/javascript' src="http://trirand.com/blog/jqgrid/js/jquery.jqGrid.min.js"></script>

<script type="text/javascript">

function deleteDevice(serialNo) {
	
        	$.ajax({
                url : '/DMS1/devicelist/' + serialNo + '/delete',
                type: 'DELETE',
                success : function(data) {
                }
            });

}

</script> 
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

		<h1>All Devices</h1>

		<spring:url value="/devicelist/${device.serialNo}" var="userUrl" />
		<spring:url value="/devicelist/add" var="addUrl" /> 
		<button class="btn btn-primary" onclick="location.href='${addUrl}'">Add</button>

		<table class="table table-striped" border='1'>
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
						<spring:url value="/devicelist/${device.serialNo}" var="userUrl" />
						<spring:url value="/devicelist/add" var="addUrl" /> 
						<spring:url value="/devicelist/${device.serialNo}/delete" var="deleteUrl" /> 
						<spring:url value="/devicelist/${device.serialNo}/update" var="updateUrl" />
					
						<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button id="deleteBtn" class="btn btn-danger" onclick="deleteDevice('${device.serialNo}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>