$(document).ready(function(){

	$("#registerForm").validate({
		rules:{
			username:"required",
			email:{
				required:true,
				email: true
			},
			password:{
				required:true,
				minlength: 6
			},
			confirmPassword:{
				required:true,
				equalTo: "#password"
			}
		},
		messages:{
			username:"Enter your username.",
			email:{
				required:"Enter your email address.",
				email:"Enter a valid email address."
			},
			password:{
				required:"Enter your password.",
				minlength:"Password must have at least 6 characters."
			},
			confirmPassword:{
				required:"Repeat your password.",
				equalTo:"The two passwords don't match."
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