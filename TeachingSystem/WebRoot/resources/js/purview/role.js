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
			"sInfoFiltered" : "(全部记录数 _MAX_  条)",
 			"sSearch": "搜索(角色名):",
			"oPaginate" : {
				"sFirst" : "第一页",
				"sPrevious" : " 上一页 ",
				"sNext" : " 下一页 ",
				"sLast" : "最后一页"
			}
		},
		"bServerSide":true,
		"bDestroy": true,
		"sAjaxSource":"./roles/getAllRoles?rand="+Math.random(),
		"bPaginate":true,
		"bProcessing":true,
		"bFilter":true,
		"bLengthChange":true,
		"sPaginationType": 'bootstrap',
		"bSort":true,// 排序 
		"aaSorting": [[1, "asc"]],
		"bAutoWidth": false,
		"aoColumns":[
			{ "mData": null,"bSortable":false,"bVisible":false},
			{ "mData": "roleId","bSortable":true},
			{ "mData": "roleName","bSortable":false },
			{ "mData": "createTime","bSortable":false},
			{ "mData": null,"bSortable":false }
		],
		
		"aoColumnDefs": [ 
       		 {
				"aTargets":[0],  
	            "mRender": function ( data, type, full ) { 
	                return "<input type='checkbox' value='"+full.roleName+"' />";  
	            	}
			},
//			{
//				"aTargets":[1],  
//	            "mRender": function ( data, type, full ) { 
//	                return "<a href=\"#\">"+full.roleId+"</a>";  
//	       	 		}
//			},
       
	   		{
			   	"aTargets":[4],
				"mRender": function(data, type, full) {
		        	return "<a style=\"align:center;\" href='./roles/prepareUpdate?roleId=" + full.roleId + "'>"
					+" <i class=\"glyphicon glyphicon-cog\" title=\"修改\"></i></a>               "
					+"<a href=\"#\"><i class=\"glyphicon glyphicon-trash\" title=\"删除\" onclick=\"deleteRole("+full.roleId+");\" " 
					+"></i></a>";
		 
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


   function deleteRole(roleId){
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
	        message: '确定删除该角色吗？',  
	        callback: function(result) {  
	            if(result) {
	            	window.location.href = "./roles/deleteRole?roleId="+roleId;
	            } else {  
	                return;  
	            }  
	        },  
	        title: "删除角色",  
	    });
	   
	   
   };

$(".selectpicker").selectpicker();




