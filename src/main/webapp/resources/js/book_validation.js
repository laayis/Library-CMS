
$(document).ready(function(){
	$.validator.addMethod('integer', function(value, element, param) {
		return (value != 0) && (value == parseInt(value, 10));
	}, 'Please enter a non zero integer value!');
	 
	$("#advancedSearchForm").validate({
		rules:{		
			ISBN:{
				required:false,
				number:true,
				minlength: 13
			},
			authorNames:{
				required:false
			},
			publisherName:{
				required:false
			},
			year:{
				required: false,
				minlength: 4,
				number:true
			},
			qty:{
				required:false,
				number:true
			}
		},
		messages:{
			ISBN:{				
				number:"ISBN cannot contain letters, enter numbers only.",
				minlength:"Make use of the standard 13-digit ISBN."
			},
			qty:{
				number:"Enter digits only."
			},
			year:{		
				minlength:"Enter a valid year.",
				number:"Enter digits only.",
				integer:"Years are integers."
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	$("#advancedSearchFormJourn").validate({
		rules:{
			
			ISSN:{
				number:true,
				minlength: 8,
				maxlength: 8
			},
			volume_no:{
				number:true
			},
			issue_no:{
				number:true
			},
			year:{
				number:true,
				minlength: 4,
				maxlength: 8
			}
		},
		messages:{
			ISSN:{
				number:"ISSN cannot contain letters, enter numbers only.",
				minlength:"Make use standard 8-digit ISSN"
			},
			volume_no:{
				number:"Enter digits only."
			},
			issue_no:{
				
				number:"Enter digits only."
			},
			year:{
				minlength:"Enter a valid year.",
				number:"Enter digits only."
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	$("#addBookForm").validate({
		rules:{
			
			title:"required",
			ISBN:{
				required:true,
				number:true,
				minlength: 13,
				maxlength: 13,
				integer: true
			},
			authorNames:{
				required:true
			},
			publisherName:{
				required:true
			},
			year:{
				required:true,
				number:true,
				minlength: 4,
				maxlength: 4,
				integer:true
			},
			edition:
			{
				required:true,
				number:true,
				integer:true
			},
			qty:{
				required:true,
				number:true,
				integer:true
			}
		},
		messages:{
			title:"Enter a title for your book.",
			ISBN:{
				required:"Enter the book's ISBN.",
				number:"ISBNs cannot contain letters, enter numbers only.",
				minlength:"Make use of the standard 13-digit ISBN.",
				integer:"ISBNs are integers!"
			},
			authorNames:{
				required:"Enter the name of the book's author."
			},
			publisherName:{
				required:"Enter the publisher."
			},
			edition:{
				required:"Enter the edition number.",
				number:"Enter the edition in the form of a number (e.g. 1, 2).",
				integer:"Editions are integers!"
			},
			qty:{
				required:"Enter the number of copies the library has.",
				number:"Enter digits only.",
				integer:"Enter an integer quantity."
			},
			year:{
				required:"Enter the year of publication.",
				minlength:"Enter a valid year.",
				number:"Enter digits only.",
				integer:"Years are integers!"
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	
	$("#addIssueForm").validate({
		rules:{
			
			journal_title:{
				required:true
			},
			publisherName:{
				required:true
			},
			journal_type:{
				required:true
			},
			ISSN:{
				required:true,
				number:true,
				minlength: 8,
				maxlength: 8,
				integer:true
			},
			volume_no:{
				required:true,
				number:true,
				integer:true
			},
			year:{
				required:true,
				number:true,
				maxlength: 4,
				integer:true
			},
			month:{
				required:true
			},
			qty:{
				required:true,
				number:true,
				integer:true
			},
			issue_title:{
				required:true
			}
		},
		messages:{
			journal_title:"Enter the journal title.",
			publisherName:"Enter the publisher.",
			journal_type:{
				required:"Enter the classification type of the journal; e.g. \"Aerospace\""
			},
			ISSN:{
				required:"Enter the journal's ISSN.",
				number:"ISSN cannot contain letters, enter numbers only.",
				minlength:"Make use of the standard 8-digit ISSN.",
				integer:"ISSNs are integers!"
			},
			volume_no:{
				required:"Enter the volume number, enter 1 if not applicable.",
				number:"Enter digits only.",
				integer:"Volume numbers are integers!"
			},
			qty:{
				required:"Enter the number of copies the library has.",
				number:"Enter digits only.",
				integer:"Enter an integer quantity."
			},
			year:{
				required:"Enter the year of publication.",
				minlength:"Enter a valid year.",
				number:"Enter digits only.",
				integer:"Years are integers!"
			},
			month:{
				required:"Enter the month of publication."
			},
			issue_title:{
				required:"Enter the title of the issue."
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	
	$("#addCopyForm").validate({
		rules:{		
			isbn:{
				required:true,
				number:true,
				minlength: 8,
				maxlength: 13,
				integer:true
			},
			qty:{
				required:true,
				number:true,
				min: 1,
				integer:true
			},
		},
		messages:{
			isbn:{				
				number:"ISBNs/ISSNs cannot contain letters, enter numbers only.",
				minlength:"Make use of the standard 8 digit/13 digit ISBN/ISSN.",
				integer:"ISBNs/ISSNs are integers!"
			},
			qty:{
				number:"Enter digits only.",
				min:"This field must be at least 1.",
				integer:"Enter an integer!"
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	
	$("#deleteCopyForm").validate({
		rules:{		
			isbn:{
				required:true,
				number:true,
				minlength: 8,
				maxlength: 13,
				integer:true
			},
			copy_no:{
				required:true,
				number:true,
				min: 1,
				integer:true
			}
		},
		messages:{
			isbn:{				
				number:"ISBNs/ISSNs cannot contain letters, enter numbers only.",
				minlength:"Make use of the standard 8 digit/13 digit ISBN/ISSN.",
				integer:"ISBNs/ISSNs are integers!"
			},
			copy_no:{
				number:"Enter digits only.",
				min:"This field must be at least 1.",
				integer:"Enter an integer!"
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('success');
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	
});

