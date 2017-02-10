$(function(){
    var id = 0;//修改数据的ID
    var table = $('#downloadRecords');//表格对象
    //初始化selectpicker
    $('#gameType').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#gameType').selectpicker('val',$('#gameType').attr('value'));
	$('#gameTypeQuery').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#gameTypeQuery').selectpicker('val',$('#gameTypeQuery').attr('value'));
    //日期选择
	$('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('select#gameTypeQuery').change(function(){
		$("#queryGameType").val($("#gameTypeQuery").val());
		to_page(1);
	});
    //获取修改时显示的数据，弹出modal模态对话框
    $(document).delegate("#update","click",function(){
        $("#myModal #myModalLabel").text("修改游戏下载信息");
        var tdObj = $(this).parent().parent().parent().children().eq(0);
        id = $(tdObj).text();
        data={id:id};
        $.post('./toUpdate',data,
        	function(json) {
                if(json.status==1){
                    $("#myModal #logId").val(json.data.logId);                           
                    $("#myModal #queryType").val(json.data.queryType);
                    $("#myModal #gameType").val(json.data.gameType);
                    $("#myModal #downloadTime").val(json.data.downloadTime);
                   	$("#myModal #downloadUrl").val(json.data.downloadUrl); 
                   	$("#myModal #downloadSource").val(json.data.downloadSource);                
                   	$("#myModal #downloadIp").val(json.data.downloadIp);
                   	$("#myModal #userAgent").val(json.data.userAgent);
                   	//刷新下拉框
                   	$('#gameType').selectpicker('refresh');
                    //显示模态框
                    $('#myModal').modal({keyboard:false,show:true});
                } else {
                    alertFailed(json.msg);
                }
        	},'json'
        );
	})
	//删除
	 $(document).delegate("#delete","click",function(){
	    //bootbox框
		var tdObj = $(this).parent().parent().parent().children().eq(0);
	    id = $(tdObj).text();
	    data={id:id};
		bootbox.confirm({  
	        buttons: {  
	            cancel: {  
	                label: '取消',  
	                className: 'btn-default'  
	            },
	            confirm: {  
	                label: '确定',  
	                className: 'btn-primary'  
	            }
	        },  
	        message: '确定删除选中删除游戏下载信息吗？',  
	        callback: function(result) {  
	            if(result) {
				    $.post('./delete',data,
				    	function(json) {
				    		if(json.status == 1){
				    			alertSuccess(json.msg);
				    			setTimeout(function(){  document.forms[0].submit(); },1000);
				    		} else {
				    			alertFailed(json.msg);
				    		}         
				    	},'json'
				    );
	            } else {  
	                return;  
	            }  
	        },  
	        title: "删除游戏游戏下载信息",  
        });
	})
                  
    //保存,增加和修改时都使用这个方法提交表单，区别在于修改时有ID参数而增加时没有
    $(document).delegate("#save","click",function(){
        data = $(".form-horizontal").serialize();
        $.ajax({
            type:"POST",
            data:data,
            url:"./save",
            dataType:"json",
            success: function(json){
                $('#myModal').modal("hide");
                if(json.status==1){
                    alertSuccess(json.msg);
                    setTimeout(function(){  document.forms[0].submit(); },1000);
                } else {
                	alertFailed(json.msg);
                }
            }  
        });
        
    });
});

function to_page(pageIndex){
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
//清空提示信息
function clean(){
	$(".tip").text("");
}
//多条件查询
function queryByCondition(){
	if($("#gameTypeQuery").val()==null){
		$("#queryGameType").val(-1);
	} else {
		$("#queryGameType").val($("#gameTypeQuery").val());
	}
	to_page(1);
}