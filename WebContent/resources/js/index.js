$('#iconified').on('keyup', function() {
    var input = $(this);
    if(input.val().length === 0) {
        input.addClass('empty');
    } else {
        input.removeClass('empty');
    }
});

function Pager(tableName, itemsPerPage) { 
    this.tableName = tableName; 
    this.itemsPerPage = itemsPerPage; 
    this.currentPage = 1; 
    this.pages = 0; 
    this.inited = false; 
     
    this.showRecords = function(from, to) {         
        var rows = document.getElementById(tableName).rows; 
        // i starts from 1 to skip table header row 
        for (var i = 1; i < rows.length; i++) { 
            if (i < from || i > to)   
                rows[i].style.display = 'none'; 
            else 
                rows[i].style.display = ''; 
        } 
    } 
     
    this.showPage = function(pageNumber) { 
     if (! this.inited) { 
      alert("not inited"); 
      return; 
     } 
 
        var oldPageAnchor = document.getElementById('pg'+this.currentPage); 
        oldPageAnchor.className = 'pg-normal'; 
         
        this.currentPage = pageNumber; 
        var newPageAnchor = document.getElementById('pg'+this.currentPage); 
        newPageAnchor.className = 'pg-selected'; 
         
        var from = (pageNumber - 1) * itemsPerPage + 1; 
        var to = from + itemsPerPage - 1; 
        this.showRecords(from, to); 
    }    
     
    this.prev = function() { 
        if (this.currentPage > 1) 
            this.showPage(this.currentPage - 1); 
    } 
     
    this.next = function() { 
        if (this.currentPage < this.pages) { 
            this.showPage(this.currentPage + 1); 
        } 
    }                         
     
    this.init = function() { 
        var table = document.getElementById(tableName);
        var rows = table.rows;
        var records = rows.length - 1;
        this.pages = Math.ceil(records / itemsPerPage); 
        this.inited = true; 
    } 
 
    this.showPageNav = function(pagerName, positionId) { 
     if (! this.inited) { 
      alert("not inited"); 
      return; 
     } 
     var element = document.getElementById(positionId); 
      
     var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal" id="iconified"> &#xf060 Prev </span> | '; 
        for (var page = 1; page <= this.pages; page++)  
            pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | '; 
        pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal" id="iconified"> Next &#xf061</span>';             
         
        element.innerHTML = pagerHtml; 
    } 
}
function ddListDeviceOnChange() {
    var x = document.getElementById("ddDeviceList").value;
    var url = "/DMS1/dashboard/" + x + "/refresh"; // get selected value
    if (url) { // require a URL
    	location.href = url; // redirect
    }
    return false;
 }

function checkBoxValidation() {
	var displayReturn = "false";			
	$('#devicejournaltable').find('tr').each(function () {
        var row = $(this);
        if (row.find('input[type="checkbox"]').is(':checked')) {
        	displayReturn = "true";
        }
    }); 
    
    if (displayReturn == "true") {
    	document.getElementById("reserveLink").style.visibility = "hidden";
    	document.getElementById("returnLink").style.visibility = "visible";
    } else {
    	document.getElementById("reserveLink").style.visibility = "visible";
    	document.getElementById("returnLink").style.visibility = "hidden";            	
    }
}

function processReturn() {
	var displayReturn = "false";			
	$('#devicejournaltable').find('tr').each(function () {
        var row = $(this);
        if (row.find('input[type="checkbox"]').is(':checked')) {
        	var seqNo = parseInt(row.find("#seqNo").html());
        	$.ajax({
                url : '/DMS1/dashboard/' + seqNo + '/return',
                type: 'POST',
                success : function(data) {
                }
            });
        }
    });
}
	
function processCancel(seqNo) {
	/*
	var seqNo = $.map(table.rows('.selected').data(), function (item) {
		return item[0]
	});
	*/
	$("#confirmCancelDialogue").dialog({
		modal: true,
		title: "Confirmation",
		buttons: {
			"YES": function() {
	        	$.ajax({
	                url : '/DMS1/dashboard/' + seqNo + '/cancel',
	                type: 'POST',
	                success : function(data) {
	                }
	            });
	        	
				$(this).dialog("close");
				location.reload(true);
			},
			"NO": function() {
				$(this).dialog("close");
			}
		}
	});
}