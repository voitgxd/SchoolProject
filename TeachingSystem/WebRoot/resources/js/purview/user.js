$(".selectpicker").selectpicker();

var tb="";
$(function(){
	tb=$("#mainTable").dataTable({
		"oLanguage" : { 	// 汉化
			"sProcessing" : "正在加载数据...",
			"sLengthMenu" : "每页显示_MENU_条 ",
			"sZeroRecords" : "没有您要搜索的内容",
			"sEmptyTable": "表中无数据存在!",
			"sInfo" : "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条",
			"sInfoEmpty" : "记录数为0",
			"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
			"sSearch":"搜索(用户名):",
			"oPaginate" : {
				"sFirst" : "第一页",
				"sPrevious" : " 上一页 ",
				"sNext" : " 下一页 ",
				"sLast" : " 最后一页 "
			}
		},
		"bServerSide":true,
		"bDestroy": true,
		"sAjaxSource":"./users/getAllUsers?rand="+Math.random(),
		"bPaginate":true,
		"bProcessing":true,
		"bFilter":true,
		"bLengthChange":true,
		"sPaginationType": 'bootstrap',
		"bSort":true,// 排序 
		"aaSorting": [[1, "asc"]],
		"sScrollX":"100%",
		"bAutoWidth": false,
		"aoColumns":[
			{ "mData": null,"bSortable":false,"bVisible":false },
			{ "mData": "userId","bSortable":true},
			{ "mData": "userName","bSortable":false },
			{ "mData": "realName","bSortable":false },
			{ "mData": "roleName","bSortable":false },
			{ "mData": "createTime","bSortable":false },
			{ "mData": null,"bSortable":false }
		],
		
		"aoColumnDefs": [ 
       		 {
				"aTargets":[0],  
	            "mRender": function ( data, type, full ) { 
	                return "<input type='checkbox' value='"+full.userName+"' />";  
	            	}
			},
//			{
//				"aTargets":[1],  
//	            "mRender": function ( data, type, full ) { 
//	                return "<a id=\"userId\" href=\"#\">"+full.userId+"</a>";  
//	       	 		}
//			},
       
	   		{
			   	"aTargets":[6],
				"mRender": function(data, type, full) {
		        	return "<a href=\"#\"><i class=\"glyphicon glyphicon-cog\" title=\"修改\" onclick=\"updateUser("+full.userId+");\" " 
						+" data-toggle=\"modal\" data-target=\"#userUpdateModal\"></i></a>          "
						+"<a href=\"#\"><i class=\"glyphicon glyphicon-trash\" title=\"删除\" onclick=\"deleteUser("+full.userId+");\" " 
						+"></i></a>"
						;
		  			} 
					
					
	   		}
      ],
	"fnServerData":function(sSource,aoData,fnCallback){
			$.ajax({
			"type" : 'post',
			"url" : sSource,
			"dataType" : "json",
			"data" : {
				aoData : JSON.stringify(aoData)
				},
			"success" : function(resp) {
				fnCallback(resp);
				}
			});
		}
	});

});


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



function updateUser(obj){
	$.ajax({
		url:"./users/prepareUserUpdate",
		type:"GET",
		data:{userId:obj},
		contentType:"application/json",
		dataType:"json",
		success:function(data){
			$("#userUId").val(obj);
			$("#userUName").val(data.data.userName);
			$("#realUName").val(data.data.realName);
			$("#roleUName").attr('data-value',data.data.roleId);
			$("#roleUName").selectpicker('val',$("#roleUName").attr("data-value"));
		}
	});
}

function deleteUser(obj){
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
        message: '确定删除该用户吗？',  
        callback: function(result) {  
            if(result) {
			   /* $.post('./users/deleteUser',{userId:obj},
			    	function(date) {
			    		if(date.status == 1){
			    			alertSuccess(json.msg);
			    			setTimeout(function(){ 
							    document.forms[0].submit();
			    			},1000);
			    		} else {
			    			alertFailed(json.msg);
			    		}         
			    	},'json'
			    );*/
            	window.location.href = "./users/deleteUser?userId="+obj;
            } else {  
                return;  
            }  
        },  
        title: "删除用户",  
    });
}








