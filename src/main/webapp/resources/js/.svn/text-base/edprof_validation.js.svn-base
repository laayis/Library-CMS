$(document).ready(function(){
	
	$("#editProfileForm").validate({
		rules:{
			email:{
				required:true,
				email: true
			},
			phone1:{
				required:false,
				minlength:10,
				maxlength:10
			},
			phone2:{
				required:false,
				minlength:10,
				maxlength:10
			}
		},
		messages:{
			
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