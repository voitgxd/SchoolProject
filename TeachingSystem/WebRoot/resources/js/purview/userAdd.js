$(".selectpicker").selectpicker();
$("#useAddForm").validate({
	rules:{
		userName:{
			required:true
		},
		realName:{
			required:true
		}
	},
	messages:{
		userName:{
			required:"<span style='color:red'>用户名不能为空!</span>",
		},
		realName:{
			required:"<span style='color:red'>真实姓名不能为空!</span>",
		}
	}
	
});





