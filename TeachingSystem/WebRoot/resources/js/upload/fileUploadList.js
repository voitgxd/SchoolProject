$(document).ready(function() {
	var tb="";
	tb=$("#mainTable").dataTable({
		"oLanguage" : { 	// 汉化
			"sProcessing" : "正在加载数据...",
			"sLengthMenu" : "每页显示_MENU_条 ",
			"sZeroRecords" : "没有您要搜索的内容",
			"sEmptyTable": "表中无数据存在!",
			"sInfo" : "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条",
			"sInfoEmpty" : "记录数为0",
			"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
			"sSearch":"搜索(用户ID):",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : " 上一页 ",
				"sNext" : " 下一页 ",
				"sLast" : " 最后一页 "
			}
		},
		"bServerSide":true,
		"bDestroy": true,
		"sAjaxSource":"../upload/getAllFileInfo?rand="+Math.random(),
		"bPaginate":true, 
		"bProcessing":true,
		"bFilter":true,
		"bLengthChange":true,
		"sPaginationType": 'bootstrap',
		"bSort":true,// 排序 
		"aaSorting": [[1, "asc"]],
		"sScrollX":"100%",
		//"sScrollY":"100%",
		"bAutoWidth": false,
		"aoColumns":[
			{ "mData": null,"bSortable":false,"bVisible":false },
			{ "mData": "passportId","bSortable":false },
			{ "mData": "url","bSortable":false },
			{ "mData": "url","bSortable":false }
			//{ "mData": "createTime","bSortable":false }
			//{ "mData": null,"bSortable":false }
		],
		
		"aoColumnDefs": [ 
       		 {
				"aTargets":[0],  
	            "mRender": function ( data, type, full ) { 
	                return "<input type='checkbox' value='"+full.fileId+"' />";  
	            	}
			},
			{
				"aTargets":[2],  
	            "mRender": function ( data, type, full ) { 
					return "<img src=\" "+full.url+" \" style=\"width: 40px;height: 40px;\"/>";
	       	 		}
			}
//	   		{
//			   	"aTargets":[3],
//				"mRender": function(data, type, full) {
//		        	return "<a href=\"#\">"+full.url+"</a>";
//		  			} 
//	   		}
      ],
	"fnServerData":function(sSource,aoData,fnCallback){
			$.ajax({
			"type" : 'GET',
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
	
	var sessionId=$("#sessionId").val();
	setTimeout(function(){
		$("#uploadify").uploadify({
			method   : 'post',
			height	 :30,
			width	 :120,
			swf		 :'../resources/plugin/uploadify/img/uploadify.swf',
			uploader : '../upload/fileUpload;JSESSIONID='+sessionId,
			cancelImg      : '../resources/plugin/uploadify/img/uploadify-cancel.png',
			buttonText     : '选择文件',
			formData  : {'jsessionid':sessionId},
			buttonCursor	:'hand',
			queueID        : 'fileQueue',
			auto           : false,
			multi          : true,
			wmode			 :'transparent',
			uploadLimit : 999,
			fileTypeExts	: '*.png;*.gif;*.jpg;*.bmp;*.jpeg;*.doc;*.pdf;*.rar',
			fileTypeDesc	: '可上传文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',
			removeCompleted : true,
			removeTimeout	:3,	
			onSelect: function (file) {
//                    $('#uploadify').uploadifySettings('formData', { 'jsessionid': sessionId});
//                    alert(formDate);
            }, 
			onUploadSuccess:function(file,data,response){
				}	
			});
	},10);
	
});


$("#btnExport").click(function(){
	
	$("#btnExport").attr("href",'../upload/fileReportExcel');
	
});
