/**
 *  Add the following code if you want the name of the file appear on select
 */

$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});

function displayProductField() {
	var aws = document.getElementById("aws");
	var mule = document.getElementById("mulesoft");
	var product = document.getElementById("productName").value;
	
	var deployChoice = ('#').deployChoice
	
	$('#deployElements').hide();
	
	if (product == "AWS API Gateway"){
		if(aws.style.display="none"){
			aws.style.display = "block";
			mule.style.display="none";
			//alert("Inside AWS clearing Mule");
			clearInputFields("mulesoft");
			
		}
    } 
	else if(product == "Mulesoft"){
		if(mule.style.display="none"){
			mule.style.display = "block";
			aws.style.display="none";
			clearInputFields("aws");
			//alert("Inside Mulesoft clearing AWS");
		}
	}
	else{
		mule.style.display = "none";
		aws.style.display="none";
//		clearInputFields("mulesoft");
//		clearInputFields("aws");
	}
}


setTimeout(function() {
    $('#apiResponse').fadeOut('fast');
}, 15000);

setTimeout(function() {
    $('#apiError').fadeOut('fast');
}, 15000);

function showOverview(){
	$('#overview').show();
	$('#productForm').hide();
}

function showHome(){
	$('#overview').hide();
	$('#productForm').show();
}


function clearInputFields(divElement) {
    var ele = document.getElementById(divElement);

    // IT WILL READ ALL THE ELEMENTS. <p>, <div>, <input> ETC.
    for (i = 0; i < ele.childNodes.length; i++) {

        // SINCE THE <input> FIELDS ARE INSIDE A <p> TAG, 
        // I'LL USE THE "firstChild" PROPERTY TO GET THE <input> TAG.
        var child = ele.childNodes[i].firstChild;
        console.log(child);
        //alert(child);

        // CHECK IF CHILD NOT NULL.
        // THIS IS IMPORTANT AS IT WILL RETURN A TEXT FOR EVERY "Whitespace".
        // 'Whitespace' IS A TEXT OR NODE BETWEEN <div> AND <p> AND AFTER <p>.
        if (child) {
            switch (child.type) {
            	case 'select':
                case 'button':
                case 'text':
                case 'submit':
                case 'password':
                case 'file':
                case 'email':
                case 'date':
                case 'number':
                    child.value = '';
                case 'checkbox':
                case 'radio':
                    child.checked = false;
            }
        }
    }
}