$(function() {
	//下拉菜单
	$('.selectpicker').selectpicker({size:80,noneSelectedText:'请选择',width:433});
	$('#gameTypes').DataTable({
		"sPaginationType": "bootstrap",//分页样式使用bootstrap
		"responsive" : true,
      	"bPaginate": true,
      	"sScrollX":"100%",
      	"bFilter": true,
      	"bLengthChange": false,  //每页显示的记录数
      	"aaSorting": [[3, "asc"]],
      	"aoColumns": [
		     {"bVisible": true, "bSearchable": true, "bSortable":false},
		     {"bVisible": true, "bSearchable": true, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":false},
		     {"bVisible": true, "bSearchable": false, "bSortable":true},
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
	     "sSearch": "搜索(游戏类型ID/游戏类型名称)",
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
    var table = $('#gameTypes');//表格对象
         
    //获取修改时显示的数据，弹出modal模态对话框
    $(document).delegate("#update","click",function(){
        $("#myModal #myModalLabel").text("修改游戏类型信息");
        var tdObj = $(this).parent().parent().parent().children().eq(0);
        id = $(tdObj).text();
        data={id:id};
        $.post('./toUpdate',data,
        	function(json) {
                if(json.status==1){
                    $("#myModal #gameTypeId").val(json.data.gameTypeId);                           
                    $("#myModal #gameTypeName").val(json.data.gameTypeName);
                    $("#myModal #typeDescribe").val(json.data.typeDescribe);
                    $("#myModal #gameNumbers").val(json.data.gameNumbers);
                   	$("#myModal #typeOrder").val(json.data.typeOrder); 
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
	        message: '确定删除选中删除游戏类型信息吗？',  
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
	        title: "删除游戏类型信息",  
        });
	})
                  
    //保存,增加和修改时都使用这个方法提交表单，区别在于修改时有ID参数而增加时没有
    $(document).delegate("#save","click",function(){
    	if(!checkForm()){
    		return false;
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
    $("#type_add").click(function(){
        $("#myModal #myModalLabel").text("增加游戏类型信息");
        //标示表单是提交向添加保存的
    	$("#myModal #gameTypeId").val(-1);                           
        $("#myModal #gameTypeName").val("");
        $("#myModal #typeDescribe").val("");
        $("#myModal #gameNumbers").val("");
       	$("#myModal #typeOrder").val(""); 
       	clean();
        $('#myModal').modal({keyboard:false,show:true});
    });           
});
//表单校验
function checkForm(){
	var gameTypeName = $("#myModal #gameTypeName").val().trim();
    var typeDescribe = $("#myModal #typeDescribe").val().trim();
    var gameNumbers = $("#myModal #gameNumbers").val().trim();
   	var typeOrder = $("#myModal #typeOrder").val().trim(); 
   	//校验空
   	if(gameTypeName==""){
   		$("#gameTypeName").parent().next().text('游戏类型名称不能为空.');
   		return false;
   	}
   	if(typeDescribe==""){
   		$("#typeDescribe").parent().next().text('类型描述不能为空.');
   		return false;
   	}
   	if(gameNumbers==""){
   		$("#gameNumbers").parent().next().text('游戏数量不能为空.');
   		return false;
   	}
   	if(typeOrder==""){
   		$("#typeOrder").parent().next().text('类型排序不能为空.');
   		return false;
   	}
   	//校验数字
   	var intReg = /^[1-9]\d*$/;//非零的正整数
   	
   	if(!intReg.test(gameNumbers)){
   		$("#gameNumbers").parent().next().text("请输入非零的正整数.");
   		return false;
   	}
   	if(!intReg.test(typeOrder)){
   		$("#typeOrder").parent().next().text("请输入非零的正整数.");
   		return false;
   	}
   	//校验长度
   	if(!checkStringLength(gameTypeName,20)){
   		$("#gameTypeName").parent().next().text("游戏类型名称不能超过20位.");
   		return false;
   	}
   	if(!checkStringLength(typeDescribe,20)){
   		$("#typeDescribe").parent().next().text("类型描述不能超过20位.");
   		return false;
   	}
   	if(!checkStringLength(gameNumbers,8)){
   		$("#gameNumbers").parent().next().text("游戏数量不能超过8位.");
   		return false;
   	}
   	if(!checkStringLength(typeOrder,8)){
   		$("#typeOrder").parent().next().text("类型排序不能超过8位.");
   		return false;
   	}
   	return true;
}
//清空提示信息
function clean(){
	$(".tip").text("");
}