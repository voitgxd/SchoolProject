$(document).ready(function() {
	//下拉菜单
		$('#osType').selectpicker( {
			size : 80,
			noneSelectedText : '请选择',
			width : 343
		});
		$('#osType').selectpicker('val', $('#osType').attr('value'));
		$('#gameState').selectpicker( {
			size : 80,
			noneSelectedText : '请选择',
			width : 343
		});
		$('#gameState').selectpicker('val', $('#gameState').attr('value'));
		//初始化selectpicker
		$('#gameType').selectpicker( {
			size : 80,
			noneSelectedText : '请选择'
		});
		$('#gameType').selectpicker('val', $('#gameType').attr('value'));
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
				"sSearch" : "搜索(游戏名称/游戏类型)",
				"sUrl" : "", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
				"oPaginate" : {
					"sFirst" : "第一页",
					"sPrevious" : "",
					"sNext" : "",
					"sLast" : "最后一页"
				}
			}

		});

		// 拍寻显示隐藏排序按钮
		jQuery('#shCol').click(function(event) {
			event.stopPropagation();
		});

		// DataTables分页选择框大小设定
		jQuery('div.dataTables_length select').removeClass(
				'form-control input-sm');
		jQuery('div.dataTables_length select').css( {
			width : '100px'
		});
	});
//异步删除游戏信息数据
function deleteOne(gameId) {
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
        message: '确定要删除该游戏信息吗？',  
        callback: function(result) {  
            if(result) {
	$.post('./deleteGameInfo', {
		'gameId' : gameId
	}, function(data) {
		if(data.retCode==1){
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
        title: "游戏信息删除",  
        });
}
//增加礼包信息
$(function(){
    $("#add_gameInfo").click(function(){
        $("#myModal #myModalLabel").text("增加游戏信息");
        loadGameTypeInfo();
        //标示表单是提交向添加保存的
    	$("#myModal #gameId").val(-1);                           
        $("#myModal #gameName").val("");
        $("#myModal #gameType").val("");
        $("#myModal #osType").val("");
       	$("#myModal #gameState").val(""); 
       	$("#myModal #rankingOrder").val("");
       	$("#myModal #dataPacket").val("");
       	$("#myModal #gameEvaluate").val("");
   		$("#myModal #pictureUrl").val("");
   		$("#myModal #downloadUrl").val("");
       	$("#myModal #forumUrl").val("");
       	$("#myModal #officialUrl").val("");
       	$("#myModal #gameDescribe").val("");
       	$("#messageInfo").html("");
       	$("#gameType").attr("value","");
       	$("#gameState").attr("value","");
       	$("#osType").attr("value","");
       	$('#gameType').selectpicker('val', $('#gameType').attr('value'));
		$('#gameState').selectpicker('val', $('#gameState').attr('value'));
		$('#osType').selectpicker('val', $('#osType').attr('value'));
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
        	if(json.retCode==1){
        		alertSuccess(json.msg);
        		$('#myModal').modal("hide"); 
        		setTimeout(function(){
        			window.location.href="./showGameInfo";
        		},1000);
                    
        	}else{
        		alertFailed(json.msg);
        	}
                            
            }  
        });
        
    }); 
    
});
//获取要修改的数据 
function updateOne(gameId){
    		$("#myModal #myModalLabel").text("修改游戏信息");
    		loadGameTypeInfo();
    		data={gameId:gameId}
    		$.post('./getUpdateGameInfo',data,
        	function(data) {     
    	$("#myModal #gameId").val(data.updateGame.gameId);                           
        $("#myModal #gameName").val(data.updateGame.gameName);
        $("#myModal #gameType").attr("value",data.updateGame.gameType);
        $('#myModal #gameType').selectpicker('val',$('#gameType').attr('value'));
        $("#myModal #gameType").val(data.updateGame.gameType);
        $("#myModal #osType").attr("value",data.updateGame.osType);
        $('#myModal #osType').selectpicker('val',$('#osType').attr('value'));
        $("#myModal #osType").val(data.updateGame.osType);
        $("#myModal #gameState").attr("value",data.updateGame.gameState);
       	$('#myModal #gameState').selectpicker('val',$('#gameState').attr('value'));
       	$("#myModal #gameState").val(data.updateGame.gameState); 
       	$("#myModal #rankingOrder").val(data.updateGame.rankingOrder);
       	$("#myModal #dataPacket").val(data.updateGame.dataPacket);
       	$("#myModal #gameEvaluate").val(data.updateGame.gameEvaluate);
   		$("#myModal #pictureUrl").val(data.updateGame.pictureUrl);
   		$("#myModal #downloadUrl").val(data.updateGame.downloadUrl);
       	$("#myModal #forumUrl").val(data.updateGame.forumUrl);
       	$("#myModal #officialUrl").val(data.updateGame.officialUrl);
       	$("#myModal #gameDescribe").val(data.updateGame.gameDescribe);
       	$("#messageInfo").html("");
       $("#osType").selectpicker("refresh");
       $("#gameState").selectpicker("refresh");
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
	var gameName = $("#myModal #gameName").val().trim();
	var gameType = $("#myModal #gameType").val();
	//var rankingOrder = $("#myModal #rankingOrder").val().trim();
	var dataPacket = $("#myModal #dataPacket").val().trim();
	var gameEvaluate = $("#myModal #gameEvaluate").val().trim();
    var pictureUrl = $("#myModal #pictureUrl").val().trim();
    var downloadUrl = $("#myModal #downloadUrl").val().trim();
   	var officialUrl = $("#myModal #officialUrl").val().trim(); 
   	var forumUrl = $("#myModal #forumUrl").val().trim();
   	var gameDescribe = $("#myModal #gameDescribe").val().trim();
   	var osType = $("#myModal #osType").val();
   	var gameState = $("#myModal #gameState").val();
   	//校验空
   	
   	if(gameName =="" || gameName == null){
   		$("#gameName").parent().next().text("游戏名称不能为空！");
   		return false;
   	}
   	if(!checkStringLength(gameName,20)){
   		$("#gameName").parent().next().text("游戏名称不能超过20个字符！");
   		return false;
   	}
   	if(gameType =="" || gameType == null){
   		$("#gameType").parent().next().text("游戏类型不能为空！");
   		return false;
   	}
   	if(osType =="" || osType == null){
   		$("#osType").parent().next().text("操作系统类型不能为空！");
   		return false;
   	}
   	if(gameState =="" || gameState == null){
   		$("#gameState").parent().next().text("运营状态不能为空！");
   		return false;
   	}
//   	if(rankingOrder =="" || rankingOrder == null){
//   		$("#rankingOrder").parent().next().text("游戏排名不能为空！");
//   		return false;
//   	}
//   	if(!checkStringLength(rankingOrder,10)){
//   		$("#rankingOrder").parent().next().text("游戏排名不能超过10个字符！");
//   		return false;
//   	}
   	if(!checkStringLength(gameEvaluate,10)){
   		$("#gameEvaluate").parent().next().text("星级评价不能超过10个字符！");
   		return false;
   	}
   	if(!checkStringLength(pictureUrl,50)){
   		$("#pictureUrl").parent().next().text("图片地址不能超过50个字符！");
   		return false;
   	}
   	if(!checkStringLength(downloadUrl,30)){
   		$("#downloadUrl").parent().next().text("游戏下载地址不能超过30个字符！");
   		return false;
   	}
   	if(!checkStringLength(forumUrl,30)){
   		$("#forumUrl").parent().next().text("论坛地址不能超过30个字符！");
   		return false;
   	}
   	if(!checkStringLength(officialUrl,30)){
   		$("#officialUrl").parent().next().text("官方地址不能超过30个字符！");
   		return false;
   	}
   	if(!checkStringLength(gameDescribe,30)){
   		$("#gameDescribe").parent().next().text("游戏描述不能超过30个字符！");
   		return false;
   	}
   	return true;
}
//清空提示信息
function clean(){
	$(".tip").text("");
}
//获取游戏类型渠道信息
function loadGameTypeInfo(){
	$('#gameType').children('option').remove() ;//清空渠道信息
	$.post("../gameDownload/loadGameTypes",
		   "",
		   function(data){
				
				if(data.status==1){
					//将游戏类型信息加载到select节点中
					for(var i=0;i<data.types.length;i+=1){
					    var optionNode = $('<option value = '+data.types[i].gameTypeId+'>'+data.types[i].gameTypeName+'</option>') ;
					    $('#gameType').append(optionNode) ;
					}
					$('#gameType').selectpicker('refresh');
					// 初始化选中第一个渠道
					if($('#gameType option').length>0){
					  $('#gameType').selectpicker('val', $('#gameType').attr('value'));
					}
	   				return true;
	          	} 
     	},"json");
}
