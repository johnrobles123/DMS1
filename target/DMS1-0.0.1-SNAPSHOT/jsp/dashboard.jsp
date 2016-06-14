<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	  </style>	   
	   
	  <script type='text/javascript'>
	   jQuery(document).ready(function () {
	  
	             jQuery("#projectTable").jqGrid({
	                 url: "LoadJsonDataServlet?type=BS21 7RH",
	                 datatype: "json",
	                 jsonReader: {repeatitems: false, id: "ref"},
	                 colNames:['ID','Reservation Date', 'User Name', 'Device Name', 'Status', 'Cancel?', 'Verify Return'],
	                 colModel:[
	                     {name:'id',index:'id', width:100},
	                     {name:'reserveDate',index:'reserveDate', width:100},
	                     {name:'userName',index:'userName', width:200},
	                     {name:'deviceName',index:'deviceName', width:200},
	                     {name:'status',index:'status', width:100},
	                     {name:'cancel',index:'cancel', width:100},
	                     {name:'verifyReturn',index:'verifyReturn', width:100}
	                 ],
	                 rowNum:20,
	                 rowList:[20,60,100],
	                 height:460,
	                 pager: "#pagingDiv",
	                 viewrecords: true,
	                 caption: "Reservation Journal"
	             });
	              
	             $("#pcSelect").change(function(){
	                 var postcode = $("#pcSelect").val();
	                 jQuery("#projectTable").jqGrid(
	                         "setGridParam",{
	                             url:"LoadJsonDataServlet?type="+ postcode,
	                             page:1})
	                         .trigger("reloadGrid");
	             });
	         });
	  </script>
	 </head>
	 <body>
	  <table id="grid"></table>
	    <hr>
	  <div>
	         <div>
	          <div style="float: left;">
	              <table id="projectTable"></table>
	              <div id="pagingDiv"></div>
	          </div>
	         </div>
	  </div>
	</body>
</html>