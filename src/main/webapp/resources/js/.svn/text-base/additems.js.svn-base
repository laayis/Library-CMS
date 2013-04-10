//jQuery for CheckIn / CheckOut forms fadeIn
if ($("#checkInForm").length){
	$("#checkInForm").fadeIn();
}
else if ($("#checkOutForm").length){
	$("#checkOutForm").fadeIn();
}
else {
	//jQuery to toggle between displayed forms
	var selected  = ($("#itemChoice option:selected").text());
	if (selected == "Add Book"){	
		$("#addBookForm").fadeIn();
	}
	else if (selected == "Add Journal Issue"){
		$("#addIssueForm").fadeIn();
	}
	else if (selected == "Add Copy"){
		$("#addCopyForm").fadeIn();
	}	
	else if (selected == "Delete Copy"){
		$("#deleteCopyForm").fadeIn();
	}
	else {
		$("#addBookForm").fadeIn();
	}
	
	$(document).ready(function() {
		$("#itemChoice").bind("change", function() {
			if ($(this).val() == "book") {
				$("#addBookForm").fadeIn();
				$("#addIssueForm").hide();
				$("#addCopyForm").hide();
				$("#deleteCopyForm").hide();
			} else if ($(this).val() == "journal") {
				$("#addIssueForm").fadeIn();
				$("#addBookForm").hide();
				$("#addCopyForm").hide();
				$("#deleteCopyForm").hide();
			} else if ($(this).val() == "addCopy"){
				$("#addIssueForm").hide();
				$("#addBookForm").hide();
				$("#addCopyForm").fadeIn();
				$("#deleteCopyForm").hide();
			}
			else if ($(this).val() == "deleteCopy"){
				$("#addIssueForm").hide();
				$("#addBookForm").hide();
				$("#addCopyForm").hide();
				$("#deleteCopyForm").fadeIn();
			}

			$(".alert").hide();
		});
		
		$("#selectedJournal").bind("change", function() {
			if ($(this).val() != -1){
				$("#journal_title").val("");
				$("#journalPublisherName").val("");
				$("#journal_data").hide();
			}
			else {
				$("#journal_data").fadeIn();
			}
		});
	});
}


