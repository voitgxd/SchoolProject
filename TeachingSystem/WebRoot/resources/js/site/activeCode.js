function to_page(pageIndex) {
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
//异步删除激活码数据
function deleteOne(codeId) {
bootbox.confirm({  
        buttons: {  
            confirm: {  
                label: '确定',  
                className: 'btn-danger'  
            },  
            cancel: {  
                label: '取消',  
                className: 'btn-default'  
            }  
        },  
        message: '确定要删除该条激活码信息吗？',  
        callback: function(result) {  
            if(result) {
	$.post('./deleteActiveCode', {
		'codeId' : codeId
	}, function(data) {
		if(data.result==1){
			alertSuccess(data.msg);
			setTimeout(function(){  document.forms[0].submit(); },1000);
		}else{
			alertFaile(data.msg);
		}
				
	}, 'json');
	}  
        },  
        title: "激活码删除",  
        });
}
function chooseFile(th, formName) {
	var fileName = $(th).val();
	$('#' + formName + ' [name="file_info"]').val(fileName);
}
$(function(){
	$("#insert").click(function(){
		var file = $("#activeCode").val();
		var f = file.substr(file.indexOf("."));
		if(!file){
			alert("请选择要导入的文件！");
			return false;
		}else{
			if(f!=".xls"){
				alert("请选择后缀名为xls的文件！");
				return false;
			}
		}
		
	})
	$("#download").click(function(){
		window.location.href="./getFileName";
	})
	
});

