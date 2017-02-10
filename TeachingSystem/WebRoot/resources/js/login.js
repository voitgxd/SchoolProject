jQuery(document).ready(function($) {

	$.backstretch([
      "./resources/images/bg1.png", 
      "./resources/images/bg2.png"
  	], {duration: 3000, fade: 750});
		
});


$("#loginForm").validate({
	
	rules:{
		passportName:{required:true},
		password:{required:true}
		
	},
	messages:{
		passportName:{required:"<h6 style=\"color:red\">用户名不能为空!</h6>"},
		password:{required:"<h6 style=\"color:red\">密码不能为空!</h6>"}
	}
});

$("#loginBtn").click(function(){
	$("#tipMsg").empty();
});

