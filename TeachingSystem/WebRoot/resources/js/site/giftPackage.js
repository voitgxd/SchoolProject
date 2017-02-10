$(document).ready(function() {
	//下拉菜单

		$('#packageType').selectpicker( {
			size : 80,
			noneSelectedText : '请选择',
			width : 343
		});
		$('#packageType').selectpicker('val', $('#packageType').attr('value'));

		$('#gameId').selectpicker( {
			size : 80,
			noneSelectedText : '请选择',
			width : 343
		});
		$('#gameId').selectpicker('val', $('#gameId').attr('value'));
		//日期选择
		$('.form_datetime').datetimepicker( {
			//language:  'fr',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1
		});
		//dateTable
		$('#basicTable').DataTable( {
			"sPaginationType" : "bootstrap",//分页样式使用bootstrap
			"responsive" : true,
			"bPaginate" : true,
			"bFilter" : true,
			"sScrollX" : "100%",
			"bLengthChange" : false, //每页显示的记录数
			"aaSorting" : [ [ 0, "asc" ] ],
			"aoColumns" : [ {
				"bVisible" : true,
				"bSearchable" : false,
				"bSortable" : true
			}, {
				"bVisible" : true,
				"bSearchable" : true,
				"bSortable" : false
			}, {
				"bVisible" : true,
				"bSearchable" : true,
				"bSortable" : false
			}, {
				"bVisible" : true,
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"bVisible" : true,
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"bVisible" : true,
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"bVisible" : true,
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"bVisible" : true,
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"bVisible" : true,
				"bSearchable" : false,
				"bSortable" : false
			}, {
				"bVisible" : true,
				"bSearchable" : false,
				"bSortable" : false
			} ],
			"bStateSave" : true,
			"bProcessing" : true, //当datatable获取数据时候是否显示正在处理提示信息。
			"oLanguage" : {
				"sLengthMenu" : "每页显示 _MENU_ 条记录",
				"sZeroRecords" : "对不起，查询不到任何相关数据",
				"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
				"sInfoEmtpy" : "找不到相关数据",
				"sInfoFiltered" : "数据表中共为 _MAX_ 条记录)",
				"sProcessing" : "正在加载中...",
				"sSearch" : "搜索(礼包ID/礼包类型)",
				"sUrl" : "", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
				"oPaginate" : {
					"sFirst" : "第一页",
					"sPrevious" : "",
					"sNext" : "",
					"sLast" : "最后一页"
				}
			}

		});
	});
// 拍寻显示隐藏排序按钮
jQuery('#shCol').click(function(event) {
	event.stopPropagation();
});

// DataTables分页选择框大小设定
jQuery('div.dataTables_length select').removeClass('form-control input-sm');
jQuery('div.dataTables_length select').css( {
	width : '100px'
});
//	});
//异步删除激活码数据
function deleteOne(packageId) {
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
        message: '确定要删除该礼包吗？',  
        callback: function(result) {  
            if(result) {
	$.post('./deleteGiftPackage', {
		'packageId' : packageId
	}, function(data) {
		if(data.status==1){
			alertSuccess(data.msg);
			setTimeout(function(){  document.forms[0].submit(); },1000);
		}else{
			alertFailed(data.msg);
		}
	}, 'json');
	} else {  
                return;  
            }  
        },  
        title: "激活码删除",  
        });
}
//增加礼包信息
$(function(){
    $("#add_giftPackage").click(function(){
        $("#myModal #myModalLabel").text("增加礼包信息");
        loadGameInfo();
        //标示表单是提交向添加保存的
    	$("#myModal #packageId").val(-1);                           
        $("#myModal #gameId").val("");
        $("#myModal #packageType").val("");
        $("#myModal #packageTitle").val("");
       	$("#myModal #packageDescribe").val(""); 
       	$("#myModal #beginTime").val("");
       	$("#myModal #endTime").val("");
       	$("#myModal #useMethod").val("");
       	$("#messageInfo").html("");
       	$("#gameId").attr("value","");
       	$("#packageType").attr("value","");
       	//重新渲染selectpicker
       	$('#packageType').selectpicker('val', $('#packageType').attr('value'));
       	$('#gameId').selectpicker('val', $('#gameId').attr('value'));
        $('#myModal').modal({keyboard:false,show:true})
    });    
    //保存,增加和修改时都使用这个方法提交表单，区别在于修改时有ID参数而增加时没有
    $(document).delegate(".modal-dialog .btn.btn-save","click",function(){
    	if(checkForm()){
    		
    	} else {
    		return false;
    	
    }
        data = $(".form-horizontal").serialize();
        $.ajax({
            type:"POST",
            data:data,
            url:"./save",
            dataType:"json",
            success: function(json){
        	if(json.status==1){
        		$('#myModal').modal("hide");
        		alertSuccess(data.msg);
                 setTimeout(function(){
                	 window.location.href="./showGiftPackage";
                 },1000);  
        	}else {
        		alertFailed(data.msg);
        	}
                            
            }  
        });
        
    }); 
    
});
//获取要修改的数据 
function updateOne(packageId){
    		$("#myModal #myModalLabel").text("修改礼包信息");
    		loadGameInfo();
    		data={packageId:packageId}
    		$.post('./getUpdateInfo',data,
        	function(data) {     
    	$("#myModal #packageId").val(data.oneGift.packageId); 
    	$("#myModal #gameId").attr("value",data.oneGift.gameId);
    	$('#myModal #gameId').selectpicker('val',$('#gameId').attr('value'));
        $("#myModal #gameId").val(data.oneGift.gameId);
        $("#myModal #packageType").attr("value",data.oneGift.packageType);
        $('#myModal #packageType').selectpicker('val',$('#packageType').attr('value'));
        $("#myModal #packageType").val(data.oneGift.packageType);
        $("#myModal #packageTitle").val(data.oneGift.packageTitle);
       	$("#myModal #packageDescribe").val(data.oneGift.packageDescribe); 
       	$("#myModal #beginTime").val(data.oneGift.beginTime);
       	$("#myModal #endTime").val(data.oneGift.endTime);
       	$("#myModal #useMethod").val(data.oneGift.useMethod);
       	$("#messageInfo").html("");
       	$("#packageType").selectpicker("refresh");
       	$("#gameId").selectpicker("refresh");
                    //显示模态框
                    $('#myModal').modal({keyboard:false,show:true})
                
        	},'json'
        );
    }

//验证长度
function checkStringLength(value, length){
	if(value.length<length){
		return true;
	} else {
		return false;
	}
}

//表单校验
function checkForm(){
	var gameId = $("#myModal #gameId").val();
    var packageType = $("#myModal #packageType").val();
    var packageTitle = $("#myModal #packageTitle").val().trim();
   	var packageDescribe = $("#myModal #packageDescribe").val().trim(); 
   	var beginTime = $("#myModal #beginTime").val();
   	var endTime = $("#myModal #endTime").val();
   	var useMethod = $("#myModal #useMethod").val().trim();
   	//校验是否为空
   	if(gameId==null || gameId==""){
   		$("#gameId").parent().next().text("游戏id不能为空！");
   		return false;
   	}
   	if(packageType==null || packageType==""){
   		$("#packageType").parent().next().text("礼包类型不能为空！");
   		return false;
   	}
   	if(!checkStringLength(packageTitle,20)){
   		$("#packageTitle").parent().next().text("礼包标题最大长度不能超过20个字符！");
   		return false;
   	} 
   	if(packageTitle==null||packageTitle==""){
   		$("#packageTitle").parent().next().text("礼包标题不能为空！");
   		return false;
   	}
   	if(packageDescribe==null || packageDescribe ==""){
   		$("#packageDescribe").parent().next().text("礼包描述不能为空！");
   		return false;
   	}
   		if(!checkStringLength(packageDescribe,50)){
   			$("#packageDescribe").parent().next().text("礼包描述最大长度不能超过50个字符！");
   		return false;
   	} 
   	if(beginTime==null || beginTime ==""){
   		$("#beginTime").parent().parent().next().text("开始时间不能为空！");
   		return false;
   	}
   	if(endTime==null || endTime ==""){
   		$("#endTime").parent().parent().next().text("结束时间不能为空！");
   		return false;
   	}
   	if(useMethod==null || useMethod ==""){
   		$("#useMethod").parent().next().text("礼包使用方法不能为空！");
   		return false;
   	}
   	if(!checkStringLength(useMethod,50)){
   		$("#useMethod").parent().next().text("礼包使用方法描述最大长度不能超过50个字符！");
   		return false;
   	} 
   	  
   	//校验日期大小
   	 if(beginTime.replace(/\-/g, "\/")>endTime.replace(/\-/g, "\/")){
   	$("#messageInfo").html("<font color='red'>开始日期不能大于结束日期！</font>");	 
   		 return false;
   	 }else{
   		 return true;
   	 }
return true;
   	 //校验长度
   	
   	
   		
}
	//清空提示信息
function clean(){
	$(".tip").text("");
}
//获取游戏信息
function loadGameInfo(){
	$('#gameId').children('option').remove() ;//清空游戏select信息
	$.post("./loadGameInfo",
		   "",
		   function(data){
				
				
					//将游戏类型信息加载到select节点中
					for(var i=0;i<data.games.length;i+=1){
					    var optionNode = $('<option value = '+data.games[i].gameId+'>'+data.games[i].gameName+'</option>') ;
					    $('#gameId').append(optionNode) ;
					}
					$('#gameId').selectpicker('refresh');
					// 初始化选中第一个渠道
					if($('#gameId option').length>0){
					  $('#gameId').selectpicker('val', $('#gameId').attr('value'));
					}
	   				return true;
	          	
     	},"json");
}
