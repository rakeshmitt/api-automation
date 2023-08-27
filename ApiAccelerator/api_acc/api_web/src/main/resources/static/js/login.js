
$(document).ready(function () {

	$('#loginForm').validate({
		//		errorClass: 'errors',
		rules: {
			username: {
				required: '#username:selected',
				required: true,
				minlength: 8,
				messages : {
					minLength: jQuery.validator.format("Username should be atleast of {0} characters.")
				}
			},
			password: {
				required: '#password:selected',
				minlength: 6,
				required: true,
				messages : {
					required : "Required Input",
					minLength: jQuery.validator.format("Password should be atleast of {0} characters.")
				}
			}
		},
		messages: {
			username: {
				required: "&nbsp;Please Enter Username"
			},
			password: {
				required: "&nbsp;Please Enter Password"
			},
		},
		submitHandler: function () {
			event.preventDefault();
			var formValues = $("#loginForm")[0];
			var loginData = new FormData(formValues);

			//				$("#loginBtn").prop("disabled", true);
			//				$('#spinner').show();
			$.ajax({
				type: "POST",
				url: "/api/login",
				data: loginData,
				cache: "false",
				success: function (result) {
//					console.log(result);
					window.location.href = "/api/";

					// RE-enabling Button here
					$("#loginBtn").prop("disabled", false);

				},
				error: function (e) {
					alert("something went wrong");
					console.log(e);
					$('#spinner').hide();
					if (result) {
						$('.modal-body').html(result);
					} else {
						$('.modal-body').html("Remote Service Not Available");
					}
					$('#verifyModal').modal('show');
					$("#loginBtn").prop("disabled", false);
				}
			});
		}
	});
});
