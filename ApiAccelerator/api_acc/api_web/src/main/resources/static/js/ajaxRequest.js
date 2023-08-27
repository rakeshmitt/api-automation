
$(document).ready(function () {
	$('#overview').hide();
	
	//Handling the Deployment Choice
//	var deployChoice = $('#deployChoice input:radio:checked').val()
	
	
    $("#muleDeployChoice").click(function(){
        var radioValue = $("input[name=muleApiDeployDecision]:checked").val();
        console.log(radioValue);
//      alert("Your are a - " + radioValue);
        if(radioValue === "muleCreateAndDeploy"){
            $('#muleDeployElements').show();
        }else if(radioValue === "muleCreateOnly"){
            $('#muleDeployElements').hide();
        }
    });
	
    $("#awsDeployChoice").click(function(){
        var radioValue = $("input[name=awsApiDeployDecision]:checked").val();
        console.log(radioValue);
    });
	
	
	$('#apiForm').validate({
//		errorClass: 'errors',
		rules: {
			muleOrgId: {
				required: '#mulesoft:selected',
				required: true
			},
			muleEnvId: {
				required: '#mulesoft:selected',
				minlength: 10,
				required: true
			},
			muleUsername: {
				required: '#mulesoft:selected',
				minlength: 2,
				required: true
			},
			mulePassword: {
				required: '#mulesoft:selected',
				minlength: 2,
				required: true
			},
			apiDeployDecision: {
				required: '#mulesoft:selected',
				required: true
			},
			awsAccessKey: {
				required: '#aws:selected',
				minlength: 2,
				required: true
			},
			awsSecretKey: {
				required: '#aws:selected',
				minlength: 2,
				required: true
			},
			file: {
				required: true
				//				extension : "xls|xlsx"
			}
		},
		messages: {
			muleOrgId: {
				required:  "&nbsp;Enter Mule Organization ID"
			},
			muleEnvId: {
				required:  "&nbsp;Enter Mule Environment ID"
			},
			muleUsername: {
				required:  "&nbsp;Enter Mule Anypoint Platform Username"
			},
			mulePassword: {
				required:  "&nbsp;Enter Mule Anypoint Platform Password"
			},
			apiDeployDecision: {
				required:  "&nbsp;Choose the Deployment Choice"
			},
			awsAccessKey: {
				required:  "&nbsp;Enter AWS Access Key"
			},
			awsSecretKey: {
				required:  "&nbsp;Enter AWS Secret Key"
			},
			file: {
				required:  "&nbsp; Kindly upload API config sheet with .xls/.xlsx format"
			}
		},
		highlight: function (element) {
            $(element).parent().addClass('error')
        },
        unhighlight: function (element) {
            $(element).parent().removeClass('error')
        },
		submitHandler: function () {
				event.preventDefault();
				var formValues = $("#apiForm")[0];
				var apiData = new FormData(formValues);
				$("#ajaxBtn").prop("disabled", false); //This has to be reverted as true
				$('#spinner').show();
				$.ajax({
					type: "POST",
					enctype: "multipart/form-data",
					contentType: false,
					url: "/api/upload",
					data: apiData,
					cache: "false",
					processData: false,
					datatype: 'json',
					success: function (result) {
						
						$('#spinner').hide();
//						$('.modal-body').html(result); 
						console.log(result);
						var jData = JSON.parse(result);
						var responseUI = buildResponse(jData);
						
						$('.modal-body').html(responseUI.formattedResponse); 
						
					    $('#verifyModal').modal('show');

					    // RE-enabling Button here
						$("#ajaxBtn").prop("disabled", false);
						
						$('#verifyModal').on('hidden.bs.modal', function () {
//							$("#apiForm")[0].html("");
							if(responseUI.apiStatus === 'SUCCESS'){
								location.reload();
							}else {
								$("#ajaxBtn").prop("disabled", false);
							}
							})
						
					},
						error: function (e) {
							alert("something went wrong");
							console.log(e);
							$('#spinner').hide();
							if(result){
								$('.modal-body').html(result);
							}else {
								$('.modal-body').html("Remote Service Not Available");
							}
						    $('#verifyModal').modal('show');
						    $("#ajaxBtn").prop("disabled", false);
						}
					});
				}		
		});
	});


function buildResponse(responseJson){
	
	var formattedResponse = "";
	formattedResponse+= '<ul class="list-unstyled">';
	var apiStatus = "SUCCESS";
	$(responseJson.apiServerResponse).each(function(index, item){
		formattedResponse += '<li>' ;
		console.log(this.apiDetail.apiName);
		formattedResponse += '<ul> <b>'+ this.apiDetail.apiName +"</b> API version " + this.apiDetail.apiVersion + " for " + this.apiDetail.productType + " - ";
		
		if(item.apiState === 'FAILED'){
		$(item.apiResponse).each(function(k, element){
			if((typeof element != 'undefined') && (element.apiError != null)){
				formattedResponse+="<font color='red'> <b> " + item.apiState + " </b> </font><br/>";
				formattedResponse+="<b>Cause: </b> <font color='red'>" + element.apiError.message + "</font><br/>";
				apiStatus = item.apiState;
			}/*else {
				formattedResponse+= "<font color='green'>   "+ item.apiState + "</font><br/>";
			}*/
		});
		}else if(item.apiState === 'SUCCESS'){
			formattedResponse+= "Created <font color='green'>"+ "SUCCESSFULLY" + "</font><br/>";
		}
			
		formattedResponse += '</ul>';
		formattedResponse += '</li>';
	});
	formattedResponse += '</ul>';
	console.log(formattedResponse);
	return {
		formattedResponse: formattedResponse,
		apiStatus: apiStatus
	};
}



/**
 * This method for parsing the response from Server with all APIs
 * TODO - Commented for future Release
 * @param responseJson
 * @returns
 */
/*
function buildResponse(responseJson){
	console.log(responseJson.apiServerResponse);
	
	var formattedResponse = "Following Policies have been successfully applied on ";
	
	$(responseJson.apiServerResponse).each(function(){
		formattedResponse+= '<ul class="list-unstyled">';
		formattedResponse += '<li>' + this.apiDetail.apiName;
		console.log(this.apiDetail.apiName);
		formattedResponse += '<ul>';
		$.each(this.apiResponses, function(k, v){
			if(v.apiErrorResponse != null){
				formattedResponse+="<li><font color='red'>"+ k + " has " + v.apiErrorResponse.name + "</font><br/>";
				formattedResponse+= "Reason : <font color='red'>" + v.apiErrorResponse.message +"</font> </li>" ;
				
			}else {
				formattedResponse+='<li>' + k + " is " + v.httpStatus+ '</li>';
			}
		});
		formattedResponse += '</ul>';
		formattedResponse += '</li>';
		formattedResponse += '</ul>';
	});
	
	return formattedResponse;
}

*/
