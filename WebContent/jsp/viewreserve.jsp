<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<spring:url value="/viewreserve" var="queryReserveUrl" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Reservation</title>
  <spring:url value="/resources/css/style.css" var="styleCss" />
		<spring:url value="/resources/css/font-awesome.min.css" var="fontAwesomeCss" />
		<spring:url value="/resources/js/index.js" var="mainJs" />
	    <script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
	  	<link href="${styleCss}" rel="stylesheet" />
	  	<link href="${fontAwesomeCss}" rel="stylesheet" />
  	  	<script src="${mainJs}"></script>
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
		
		#devicejournaltable thead tr th {
			text-align:left;
		}
		
		#thCenter {
			text-align:center !important;
		}
		
		
		#footer {
		    background-color:black;
		    color:white;
		    clear:both;
		    text-align:center;
		    padding:5px;
		}
	  </style>	   
</head>
<body>
	<font color="red">${message}</font>
	<form:form class="form-horizontal" method="get" modelAttribute="reservation" action="${reserveActionUrl}">
	    <table>
	    <tbody>
	    	<tr>
			<td>Device Name :</td> <td>${reservation.deviceName}</td>
			</tr>
	 	</tbody>
		<tbody>
			<tr>                              
			<td>Reservation Date:</td> <td>${reservation.reserveDate}</td>
		    </tr>
		</tbody>
		<tbody>
		    <tr>
		    <td>From time:</td>  <td>${reservation.timeFrom}</td>
		    <td>To time: </td> <td>${reservation.timeFrom}</td>
		    </tr>
	        <tr>      
	        <td>Location: </td><td>${reservation.location}</td>
	        </tr>
	        <tr>
	        <td>Additional Information:</td><td>${reservation.addInfo}</td>
	        </tr>
	        <tr><spring:url value="/reserve/${dj.seqNo}/update" var="updateReservationUrl" /> </tr>
	        <tr>
       		<td>
				<spring:url value="/reserve/${reservation.seqNo}/update" var="updateReservationUrl" /> 
				<button class="btn btn-primary" onclick="location.href='${updateReservationUrl}'">Update</button>
			</td>
			</tr>
	    </tbody>
	    
	    </table>
	</form:form>
</body>
</html>
