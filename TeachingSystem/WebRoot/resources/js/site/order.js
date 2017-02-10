$(function() {
	//下拉菜单
	$('#orderState').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#orderState').selectpicker('val',$('#orderState').attr('value'));
	$('#gameState').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#gameState').selectpicker('val',$('#gameState').attr('value'));
	//初始化selectpicker
    $('#gameName').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#gameName').selectpicker('val',$('#gameName').attr('value'));
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
	$('#gamesList').DataTable({
		"sPaginationType": "bootstrap",//分页样式使用bootstrap
		"responsive" : true,
      	"bPaginate": true,
      	"bFilter": true,
      	"sScrollX":"100%",
      	"bLengthChange": false,  //每页显示的记录数
      	"aaSorting": [[3, "asc"]],
      	"aoColumns": [
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": true, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":true},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": true, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false}
		  ],
		 "bStateSave": true,
		 "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
		 "oLanguage": {
	     "sLengthMenu": "每页显示 _MENU_ 条记录",
	     "sZeroRecords": "对不起，查询不到任何相关数据",
	     "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	     "sInfoEmtpy": "找不到相关数据",
	     "sInfoFiltered": "数据表中共为 _MAX_ 条记录)",
	     "sProcessing": "正在加载中...",
	     "sSearch": "搜索(游戏名称/排行状态)",
	     "sUrl": "", //多语言配置文件，可将oLanguage的设置放在一个txt文件中，例：Javascript/datatable/dtCH.txt
	     "oPaginate": {
	          "sFirst":    "第一页",
	          "sPrevious": "",
	          "sNext":     "",
	          "sLast":     "最后一页"
	     }
  }
      	
	});

	var shTable = $('#shTable').DataTable( {
		"fnDrawCallback" : function(oSettings) {
			$('#shTable_paginate ul').addClass('pagination-active-dark');
		},
		responsive : true
	});

	// Show/Hide Columns Dropdown
		$('#shCol').click(function(event) {
			event.stopPropagation();
		});

		$('#shCol input').on('click', function() {

			// Get the column API object
				var column = shTable.column($(this).val());

				// Toggle the visibility
				if ($(this).is(':checked'))
					column.visible(true);
				else
					column.visible(false);
			});

		
		// Add event listener for opening and closing details
		$('#exRowTable tbody').on('click', 'td.details-control',
				function() {
					var tr = $(this).closest('tr');
					var row = exRowTable.row(tr);

					if (row.child.isShown()) {
						// This row is already open - close it
				row.child.hide();
				tr.removeClass('shown');
			} else {
				// Open this row
				row.child(format(row.data())).show();
				tr.addClass('shown');
			}
		});

	});
//datatable的增删改
$(function(){
    var id = 0;//修改数据的ID
    var table = $('#gamesList');//表格对象
         
    //获取修改时显示的数据，弹出modal模态对话框
    $(document).delegate("#update","click",function(){
        $("#myModal #myModalLabel").text("修改游戏排行信息");
        var tdObj = $(this).parent().parent().parent().children().eq(0);
        id = $(tdObj).text();
        data={id:id};
        $.post('./toUpdate',data,
        	function(json) {
                if(json.status==1){
                	$("#flag").val(1);
                    $("#myModal #gameId").val(json.data.gameId);   
                    
                    $('#gameName').children('option').remove() ;//清空游戏名称信息
                    var optionNode = $('<option value = '+json.data.gameName+'>'+json.data.gameName+'</option>');
					$('#gameName').append(optionNode);
					$('#gameName').selectpicker('refresh');
                    
                    $("#myModal #queryType").val(json.data.queryType);
                    $("#myModal #orderId").val(json.data.orderId);
                   	$("#myModal #gameScore").val(json.data.gameScore); 
                   	$("#myModal #orderState").val(json.data.orderState);
                   	$("#orderState").attr("value",json.data.orderState);
                   	$("#myModal #initScore").val(json.data.initScore);
                   	$("#myModal #gameState").val(json.data.gameState);
                   	$("#gameState").attr("value",json.data.gameState);
                   	$("#myModal #statTime").val(json.data.statTime);
                   	$("#myModal #pictureUrl").val(json.data.pictureUrl);
                   	$("#myModal #innerName").val(json.data.innerName);
                    $("#myModal #gameUrl").val(json.data.gameUrl);
                    //重新渲染select
                    refreshPlugin();
                    $('#orderState').selectpicker('refresh');
    				$('#gameState').selectpicker('refresh');
                    clean();
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
	        message: '确定删除选中删除游戏排行信息吗？',  
	        callback: function(result) {  
	            if(result) {
				    $.post('./delete',data,
				    	function(json) {
				    		if(json.status == 1){
				    			alertSuccess(json.msg);
				    			setTimeout(function(){  window.location.href="./toIndex"; },1000);
				    		} else {
				    			alertFailed(json.msg);
				    		}         
				    	},'json'
				    );
	            } else {  
	                return;  
	            }  
	        },  
	        title: "删除游戏排行信息",  
        });
	})
	//上升
	$(document).delegate("#up","click",function(){
		var tdObj = $(this).parent().parent().parent().children().eq(0);
	    id = $(tdObj).text();
	    data={id:id};
	    $.post('./up',data,
	    	function(json) {
	    		if(json.status == 1){
	    			alertSuccess(json.msg);
	    			setTimeout(function(){  window.location.href="./toIndex"; },1000);
	    		} else {
	    			alertFailed(json.msg);
	    		}         
	    	},'json'
	    );
	})
	//下降
	$(document).delegate("#down","click",function(){
		var tdObj = $(this).parent().parent().parent().children().eq(0);
	    id = $(tdObj).text();
	    data={id:id};
	    $.post('./down',data,
	    	function(json) {
	    		if(json.status == 1){
	    			alertSuccess(json.msg);
	    			setTimeout(function(){  window.location.href="./toIndex"; },1000);
	    		} else {
	    			alertFailed(json.msg);
	    		}         
	    	},'json'
	    );
	})
                  
    //保存,增加和修改时都使用这个方法提交表单，区别在于修改时有ID参数而增加时没有
    $(document).delegate("#save","click",function(){
    	if(!checkForm()){
    		return false;
    	}

    	if($("#flag").val()==-1){
    		//根据游戏选中的游戏名称将gameId赋值
	    	$("#gameId").val($("#gameName").val());
	    	var gameName = $("#gameName").find("option:selected").text();
	    	//重新给该select赋值
	    	$('#gameName').children('option').remove() ;//清空游戏名称信息
	    	var optionNode = $('<option value = '+gameName+'>'+gameName+'</option>') ;
			$('#gameName').append(optionNode);
	    }
    	
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
                    setTimeout(function(){  window.location.href="./toIndex"; },1000);
                } else {
                	alertFailed(json.msg);
                }
            }  
        });
        
    });
       
    //增加,弹出modal模态对话框
    $("#order_add").click(function(){
        $("#myModal #myModalLabel").text("增加游戏排行信息");
        //标示表单是提交向添加保存的
        $("#myModal #flag").val(-1);
    	$("#myModal #gameId").val("");                           
        loadGameTypeInfo();
        $("#myModal #queryType").val("");
        $("#myModal #orderId").val("");
       	$("#myModal #gameScore").val(""); 
       	$("#myModal #orderState").val("");
       	$("#myModal #initScore").val("");
       	$("#myModal #gameState").val("");
       	$("#myModal #statTime").val("");
       	$("#myModal #pictureUrl").val("");
       	$("#myModal #innerName").val("");
       	$("#myModal #gameUrl").val("");
       	clean();
       	//重新渲染select
       	$("#orderState").attr("value","");
		$("#gameState").attr("value","");
		refreshPlugin();
        $('#myModal').modal({keyboard:false,show:true});
    });           
});
//表单校验
function checkForm(){
	var gameName = $.trim($("#myModal #gameName").val());
    var queryType = $.trim($("#myModal #queryType").val());
    var orderId = $.trim($("#myModal #orderId").val());
   	var gameScore = $.trim($("#myModal #gameScore").val());
   	var orderState = $("#myModal #orderState").val();
   	var gameState = $("#myModal #gameState").val();
   	var initScore = $.trim($("#myModal #initScore").val());
   	var statTime = $("#myModal #statTime").val();
   	var pictureUrl = $.trim($("#myModal #pictureUrl").val());
   	var innerName = $.trim($("#myModal #innerName").val());
   	var gameUrl = $.trim($("#myModal #gameUrl").val());
   	//校验空
   	if(gameName==""){
   		$("#gameName").parent().next().text('游戏名称不能为空.');
   		return false;
   	}
   	if(queryType==""){
   		$("#queryType").parent().next().text('查询类型不能为空.');
   		return false;
   	}
   	if(orderId==""){
   		$("#orderId").parent().next().text('排行ID不能为空.');
   		return false;
   	}
   	if(gameScore==""){
   		$("#gameScore").parent().next().text('游戏得分不能为空.');
   		return false;
   	}
   	if(orderState==null){
   		$("#orderState").parent().next().text('请选择排行状态.');
   		return false;
   	}
   	if(initScore==""){
   		$("#initScore").parent().next().text('初始得分不能为空.');
   		return false;
   	}
   	if(gameState==null){
   		$("#gameState").parent().next().text('请选择排行状态.');
   		return false;
   	}
   	if(statTime==""){
   		$("#statTime").parent().parent().next().text('统计时间不能为空.');
   		return false;
   	}
   	if(pictureUrl==""){
   		$("#pictureUrl").parent().next().text('图片地址不能为空.');
   		return false;
   	}
   	if(innerName==""){
   		$("#innerName").parent().next().text('Inner名不能为空.');
   		return false;
   	}
   	if(gameUrl==""){
   		$("#gameUrl").parent().next().text('链接地址不能为空.');
   		return false;
   	}
   	//校验数字
   	var floatReg = /^[-]?\d+(?:\.\d+)?$/;//带1-2位小数的正数或负数
   	var intReg = /^[1-9]\d*$/;//非零的正整数
   	
   	if(!intReg.test(queryType)){
   		$("#queryType").parent().next().text("请输入非零的正整数.");
   		return false;
   	}
   	if(!intReg.test(orderId)){
   		$("#orderId").parent().next().text("请输入非零的正整数.");
   		return false;
   	}
   	
   	if(!(floatReg.test(gameScore) || intReg.test(gameScore))){
   		$("#gameScore").parent().next().text("请输入带0-2位小数的正数或负数.");
   		return false;
   	}
   	if(!(floatReg.test(initScore) || intReg.test(initScore))){
   		$("#initScore").parent().next().text("请输入带0-2位小数的正数或负数.");
   		return false;
   	}
   	
   	//校验长度
   	if(!checkStringLength(gameName,20)){
   		$("#gameName").parent().next().text("游戏名称不能超过20位.");
   		return false;
   	}
   	if(!checkStringLength(queryType,8)){
   		$("#queryType").parent().next().text("查询类型不能超过8位.");
   		return false;
   	}
   	if(!checkStringLength(orderId,8)){
   		$("#orderId").parent().next().text("排行ID不能超过8位.");
   		return false;
   	}
   	if(!checkStringLength(gameScore,8)){
   		$("#gameScore").parent().next().text("游戏得分不能超过6位.");
   		return false;
   	}
   	if(!checkStringLength(initScore,8)){
   		$("#initScore").parent().next().text("初始得分不能超过8位.");
   		return false;
   	}	
   	if(!checkStringLength(pictureUrl,200)){
   		$("#pictureUrl").parent().next().text("图片地址不能超过200位.");
   		return false;
   	}	
   	if(!checkStringLength(innerName,20)){
   		$("#innerName").parent().next().text("Inner名不能超过20位.");
   		return false;
   	}	
   	if(!checkStringLength(gameUrl,20)){
   		$("#gameUrl").parent().next().text("链接地址不能超过20位.");
   		return false;
   	}
   	return true;
}
//清空提示信息
function clean(){
	$(".tip").text("");
}
//重新渲染select
function refreshPlugin(){
	$('#orderState').selectpicker('val',$('#orderState').attr('value'));
    $('#gameState').selectpicker('val',$('#gameState').attr('value'));
}
//获取游戏名称信息
function loadGameTypeInfo(){
	$('#gameName').children('option').remove() ;//清空游戏名称信息
	$.post("./loadGameNames",
		   "",
		   function(data){	
				if(data.status==1){
					//将游戏名称信息加载到select节点中
					for(var i=0;i<data.games.length;i+=1){
					    var optionNode = $('<option value = '+data.games[i].gameId+'>'+data.games[i].gameName+'</option>') ;
					    $('#gameName').append(optionNode) ;
					}
					$('#gameName').selectpicker('refresh');
					$('#gameName').selectpicker('val', $('#gameName').attr('value'));
	   				return true;
	          	} else {
	          		alertFailed(data.message);
	          		return false;
	          	}
     	},"json");
}