//翻页
function to_page(pageIndex) {
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
$(function(){
	//初始化selectpicker
    $('#platformType').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#platformType').selectpicker('val',$('#platformType').attr('value'));
	
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
	//增加,弹出modal模态对话框
    $("#appTopic").click(function(){
		$('#myModal .check-span').empty();
    	$('#myModal .form-group').removeClass('has-error has-warning has-feedback');
        $("#myModal #myModalLabel").text("添加专题");
        //标示表单是提交向添加保存的
        $("#myModal #flag").val(-1);
        $("#myModal #topicId").val("");                           
        $("#myModal #topicName").val("");
        $("#myModal #topicDesc").val("");
       	$("#myModal #platformType").val(""); 
       	$("#myModal #expireTime").val("");
       	//重新渲染select
       	$("#platformType").attr("value","");
		$('#platformType').selectpicker('val', $('#platformType option:first').attr('value'));
        $('#myModal').modal({keyboard:false,show:true});
    });
     //获取修改时显示的数据，弹出modal模态对话框
    $(document).delegate("#update","click",function(){
		$('#topicForm .check-span').empty();
    	$('#topicForm .form-group').removeClass('has-error has-warning has-feedback');
		
        $("#myModal #myModalLabel").text("修改分类");
        var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
		id = $(inputObj).val();
        data={id:id};
        $.post('./toUpdate',data,
        	function(json) {
            	$("#topicForm #flag").val(1);
                $("#topicForm #topicId").val(json.data.topicId);                           
                $("#topicForm #topicName").val(json.data.topicName);
                $("#topicForm #topicDesc").val(json.data.topicDesc);
                $("#topicForm #platformType").val(json.data.platformType);
                $("#topicForm #expireTime").val(json.data.expireTime);
               	//刷新下拉框
               	$('#platformType').selectpicker('refresh');
                //显示模态框
                $('#myModal').modal({keyboard:false,show:true});
        	},'json'
        );
	});
    $(document).delegate("#delete","click",function(){
	    //bootbox框
		var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
		id = $(inputObj).val();
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
	        message: '确定删除选中专题吗？',  
	        callback: function(result) {  
	            if(result) {
				    $.post('./delete',data,
				    	function(json) {
				    		if(json.status == 1){
				    			alertSuccess(json.msg);
				    			setTimeout(function(){ 
								    document.forms[0].submit();
				    			},1000);
				    		} else {
				    			alertFailed(json.msg);
				    		}         
				    	},'json'
				    );
	            } else {  
	                return;  
	            }  
	        },  
	        title: "删除专题",  
        });
	});
    $(document).delegate("#setApp","click",function(){
	    //bootbox框
		var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
		var inputObj2 = $(this).parent().parent().parent().children().eq(0).children().eq(1);
		var topicId = $(inputObj).val();
		var topicName = $(inputObj2).val();
	    window.location.href="./toSetApp?topicId=" + topicId+"&topicName="+topicName;
	});
    $(document).delegate("#remove","click",function(){
	    //bootbox框
		var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
	    var appId = $(inputObj).val();
	    var topicId = $("#topicId").val();
	    var data = {'appId':appId,'topicId':topicId};
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
	        message: '确定从该专题中删除该游戏吗？',  
	        callback: function(result) {  
	            if(result) {
				    $.post('./deleteAppFromTopic',data,
				    	function(json) {
				    		if(json.status == 1){
				    			alertSuccess(json.msg);
				    			setTimeout(function(){ 
								    document.forms[0].submit();
				    			},1000);
				    		} else {
				    			alertFailed(json.msg);
				    		}         
				    	},'json'
				    );
	            } else {  
	                return;  
	            }  
	        },  
	        title: "删除专题游戏",  
        });
	});
    //上升
	$(document).delegate("#up","click",function(){
		var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
	    var appId = $(inputObj).val();
	    var topicId = $("#topicId").val();
	    var flag = 1;
	    var data={'flag':flag,'topicId':topicId,'appId':appId};
	    $.post('./upOrDown',data,
	    	function(json) {
	    		if(json.status == 1){
	    			alertSuccess(json.msg);
	    			setTimeout(function(){  document.forms[0].submit(); },1000);
	    		} else {
	    			alertFailed(json.msg);
	    		}         
	    	},'json'
	    );
	});
	//下降
	$(document).delegate("#down","click",function(){
		var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
	    var appId = $(inputObj).val();
	    var topicId = $("#topicId").val();
	    var flag = -1;//控制上下移动的状态
	    var data={'flag':flag,'topicId':topicId,'appId':appId};
	    $.post('./upOrDown',data,
	    	function(json) {
	    		if(json.status == 1){
	    			alertSuccess(json.msg);
	    			setTimeout(function(){  document.forms[0].submit(); },1000);
	    		} else {
	    			alertFailed(json.msg);
	    		}         
	    	},'json'
	    );
	});
});
//选择游戏上传文件
function chooseFile(th, formName) {
	var fileName = $(th).val();
	$('#' + formName + ' [name="file_info"]').val(fileName);
}
//添加游戏类型
function addTopic(){
	var formName = "topicForm" ;
	$('#'+formName).find('button').attr("disabled", true);
	$('#'+formName).find('button[name="submit"]').text('提交中');
	var topicName = $.trim($('#'+formName+' #topicName').val());
	var topicDesc = $.trim($('#'+formName+' #topicDesc').val());
	var platformType = $('#'+formName+' #platformType').val();
	var expireTime = $.trim($('#'+formName+' #expireTime').val());
	
	var flag = $("#flag").val();
	var topicId = $('#'+formName+" #topicId").val();
	
	if(topicName==''){
		checkError(formName,'topicName', '请输入专题名称！') ;
		cancleForm(formName);
		return false;
	}else if(!checkStringLength(topicName,20)){
		checkError(formName,'topicName', '专题名称最多20个字符！') ;
		cancleForm(formName);
		return false;
	}else{
		checkSuccess(formName,'topicName');
	}
	
	if(topicDesc==''){
		checkError(formName,'topicDesc', '请输入专题描述！') ;
		cancleForm(formName);
		return false;
	}else if(!checkStringLength(topicDesc,50)){
		checkError(formName,'topicDesc', '专题描述最多50个字符！') ;
		cancleForm(formName);
		return false;
	}else{
		checkSuccess(formName,'topicDesc');
	}
	
	if(platformType==''){
		checkError(formName,'platformType', '请选择平台类型！') ;
		cancleForm(formName);
		return false;
	}else{
		checkSuccess(formName,'platformType');
	}
	
	if(expireTime==''){
		checkError(formName,'expireTime', '请选择过期时间！') ;
		cancleForm(formName);
		return false;
	}else{
		checkSuccess(formName,'expireTime');
	}
	
	if(flag == -1){
		//添加游戏类型，需要校验图标文件
		var file = $('#'+formName+' [name="data"]').val();
		if(file==''){
			checkError(formName,'data', '请选择待上传图标！') ;
			cancleForm(formName);
			return false;
		}else{
			var suffix = file.substring(file.lastIndexOf('.') + 1);
			if(suffix!='gif' && suffix!='jpg' && suffix!='png' && suffix!='swf'){
				checkError(formName, 'data', '仅支持.gif/.jpg/.png/.swf') ;
				cancleForm(formName);
				return false;
			}else{
				checkSuccess(formName,'data');
			}
		}
	}
	
	alertSuccess("正在提交，请稍后...",false);
	$('#myModal').modal('hide');
	
	if(flag == -1){
		//新增保存
		var fileId = $('#'+formName+' [name="data"]').attr('id');//topicIconData
		var url = './save';
		var data = {'flag':flag,'topicName':topicName,'topicDesc':topicDesc,'platformType':platformType,'expireTime':expireTime};
		// 提交
		$.ajaxFileUpload
	    (
	        {
	            url:url,// 用于文件上传的服务器端请求地址
	            data:data,
	            secureuri:false,// 一般设置为false
	            fileElementId: fileId,// 文件上传空间的id属性 <input type="file"
										// id="file" name="file" />
	            dataType: 'json',// 返回值类型 一般设置为json
	            success: function (data, status)  // 服务器成功响应处理函数
	            {
	        		cancleForm(formName);
	            	if(data.status==1){
	                	alertSuccess('上传成功');
	                	setTimeout(function(){  window.location.href="./toIndex"; },1000);
	                }else{
	                	alertFailed(data.msg);
		            }
	            },
	            error: function (data, status, e)// 服务器响应失败处理函数
	            {
	            	cancleForm(formName);
	            	alertFailed('上传失败，请稍后重试');
	            }
	        }
	    );
	} else {
		var fileName = $('#' + formName + ' [name="file_info"]').val();
		if(fileName == ""){
			//修改保存
			var data = {'flag':flag,'topicId':topicId,'topicName':topicName,'topicDesc':topicDesc,'platformType':platformType,'expireTime':expireTime};
		   	$.ajax({
		        type:"POST",
		        data:data,
		        url:"./update",
		        dataType:"json",
		        success: function(json){
		   			cancleForm(formName);
		            if(json.status==1){
		                alertSuccess(json.msg);
		                setTimeout(function(){  window.location.href="./toIndex"; },1000);
		            } else {
		            	alertFailed(json.msg);
		            }
		        }  
		    });
		} else {
			//修改保存2
			var fileId = $('#'+formName+' [name="data"]').attr('id');//picData
			var url = './updateSave';
			var data = {'flag':flag,'topicId':topicId,'topicName':topicName,'topicDesc':topicDesc,'platformType':platformType,'expireTime':expireTime};
			// 提交
			$.ajaxFileUpload
		    (
		        {
		            url:url,// 用于文件上传的服务器端请求地址
		            data:data,
		            secureuri:false,// 一般设置为false
		            fileElementId: fileId,// 文件上传空间的id属性 <input type="file"
											// id="file" name="file" />
		            dataType: 'json',// 返回值类型 一般设置为json
		            success: function (data, status)  // 服务器成功响应处理函数
		            {
		        		cancleForm(formName);
		            	if(data.status==1){
		                	alertSuccess('上传成功');
		                	setTimeout(function(){  window.location.href="./toIndex"; },1000);
		                }else{
		                	alertFailed(data.msg);
			            }
		            },
		            error: function (data, status, e)// 服务器响应失败处理函数
		            {
		            	cancleForm(formName);
		            	alertFailed('上传失败，请稍后重试');
		            }
		        }
		    );
		}
	}
}
//调出图标上传模态框
function toUploadIcon(picDir, topicId){
	$("#picTopicId").val(topicId);
	$('#picForm .check-span').empty();
    $('#picForm .form-group').removeClass('has-error has-warning has-feedback');
	$('#myModal2').modal({keyboard:false,show:true});
}
//上传分类图标
function uploadIcon(){
	var formName = "picForm";
	subForm(formName);
	var file = $('#'+formName+' [name="data"]').val();
	
	if(file==''){
		checkError(formName,'data', '请选择待上传图标！') ;
		cancleForm(formName);
		return false;
	}else{
		var suffix = file.substring(file.lastIndexOf('.') + 1);
		if(suffix!='gif' && suffix!='jpg' && suffix!='png' && suffix!='swf'){
			checkError(formName, 'data', '仅支持.gif/.jpg/.png/.swf') ;
			cancleForm(formName);
			return false;
		}else{
			checkSuccess(formName,'data');
		}
	}
	
	
	var fileId = $('#'+formName+' [name="data"]').attr('id');//picData
	var url = './uploadPic';
	var topicId = $("#picTopicId").val();
	var data = {'topicId':topicId};
	alertSuccess("正在上传，请稍后...",false);
	$('#myModal2').modal('hide');
	// 提交
	$.ajaxFileUpload
    (
        {
            url:url,// 用于文件上传的服务器端请求地址
            data:data,
            secureuri:false,// 一般设置为false
            fileElementId: fileId,// 文件上传空间的id属性 <input type="file"
									// id="file" name="file" />
            dataType: 'json',// 返回值类型 一般设置为json
            success: function (data, status)  // 服务器成功响应处理函数
            {
        		cancleForm(formName);
            	if(data.status==1){
                	alertSuccess("修改成功");
                	setTimeout(function(){  document.forms[0].submit(); },1000);
                }else{
                	alertFailed('修改失败');
	            }
            },
            error: function (data, status, e)// 服务器响应失败处理函数
            {
            	cancleForm(formName);
            	alertFailed('上传失败');
            }
        }
    );
}
//置顶
function setAppToTop(){
	var topicId = $("#topicId").val();
	var appId = $("#appId").val();
	var expireTime = $("#expireTime").val();
	var formName = "timeForm";
	if(expireTime==''){
		checkError(formName,'expireTime', '请选择过期时间！') ;
		cancleForm(formName);
		return false;
	}else{
		checkSuccess(formName,'expireTime');
	}
	var data = {'topicId':topicId,'appId':appId,'expireTime':expireTime};
	$('#myModal').modal('hide');
	$.post("./setAppToTop",data,
		function(data){
			if(data.status == 1){
				alertSuccess(data.msg);
				setTimeout(function(){  document.forms[0].submit(); },1000);
			} else {
				alertFailed(data.msg);
			}
	},'json');
}
//弹出置顶模态框
function toChooseExpireTime(appId){
	$("#appId").val(appId);
	$("#expireTime").val("");
	
	$('#timeForm .check-span').empty();
    $('#timeForm .form-group').removeClass('has-error has-warning has-feedback');
	$('#myModal').modal({keyboard:false,show:true});
}
$(function(){
	//翻页的删除漏洞
    var pageIndex = $("#pageIndex").val();
    var pageNum = $("#pageNum").val();
    if(pageIndex > pageNum && pageNum!=0){
    	$("#pageIndex").val(pageNum);
    	document.forms[0].submit();
    }
    return true;
});
//跳到添加类型游戏界面
function showAddApp(topicId){
	var topicName = $("#topicName").val();
	window.location.href="./toAddTopicApp?topicId=" + topicId+"&topicName="+topicName;
}
//全选
function selectApps(inputObj) {
    var inputArray = document.getElementById("appsList").getElementsByTagName("input");
    for (var i = 1; i < inputArray.length; i++) {
        if (inputArray[i].type == "checkbox") {
        	if(inputObj.checked){
        		inputArray[i].checked = inputObj.checked;
            	inputArray[i].disabled = "disabled";
        	} else {
        		inputArray[i].checked = "";
        		inputArray[i].disabled = "";
        	}  
        }
    }
}
//异步分页
//翻页
var everyPageIds = new Array();//用来记录每页选中的id
var currentPage = 1;//用于记录当前页的页码

//更新ID全局变量(翻页之前调用此方法)
function selectAppToTopic(){
	var checkObjs = $(":checkbox[name='check_app']:checked");
    var ids = new Array();
    for(var i=0;i<checkObjs.length;i++){
    	var labelObj = $(checkObjs[i]).parent();
    	var inputObj = $(labelObj).children().eq(1);
    	ids.push($(inputObj).val());
    }
    var appIds = $.trim(ids.toString());
    //将当前页选中的id放入全局数组中
    everyPageIds[currentPage] = appIds+",";
}

function to_page2(pageIndex){
	//检查选中放入全局变量中
	selectAppToTopic();
	
	document.getElementById("pageIndex").value = pageIndex;
	var pageIndex = $("#pageIndex").val();
	var topicId = $("#topicId").val();
	var queryGameName = $.trim($("#appNameForSearch").val());
	//异步发请求，重置页面
	//1.清空table和分页两个div
	$("#needAppsDiv").empty();
	$("#pageDiv").empty();
	//2.异步发请求,返回数据后以节点形式加载到页面上
	var data = {'topicId':topicId,'pageIndex':pageIndex,'queryGameName':queryGameName};
	$.post(
    	"./getNeedApps",
    	data,
    	function(json) {
    		//给全局变量当前页赋值
    		currentPage = json.pageInfo.pageIndex;
    		
    		var nd = '<table class="table table-hover" id="appsList"><thead><tr><th><span class="checkbox">'+
						 '<label><input type="checkbox" onclick="selectApps(this);" />全选</label></span></th>'+	
						 '<th>应用名称</th>'+
						 '<th>应用图标</th>'+
						 '<th>应用地址</th>'+
						 '<th>平台类型</th>'+
						 '<th>应用类型</th></tr></thead><tbody>';
    		for(var i=0; i<json.pageInfo.list.length; i++) 
    		{
    			var platformType;
    			var appType;
    			if(json.pageInfo.list[i].platformType==1){
    				platformType='H5';
    			} else {
    				platformType='8864平台';
    			}
    			if(json.pageInfo.list[i].appType==1){
    				appType='H5';
    			} else {
    				appType='手游';
    			}
    			
    			//查询全局id数组并初始化选中
    			var currentAppId = ''+json.pageInfo.list[i].appId+',';
    			var ndCheck = '';
    			if(everyPageIds[json.pageInfo.pageIndex]){
    				if(everyPageIds[json.pageInfo.pageIndex].indexOf(currentAppId) != -1){
	    				//该行数据被选中
	    				ndCheck = '<input type="checkbox" name="check_app" checked />';
	    			} else {
	    				ndCheck = '<input type="checkbox" name="check_app" />';
	    			}
    			} else {
    				ndCheck = '<input type="checkbox" name="check_app" />';
    			}
    			
				nd = nd +'<tr>'+
						 '<td><span class="checkbox"><label>'+ndCheck+'<input type="hidden" value="'+json.pageInfo.list[i].appId+'" />选择</label></span></td>'+
						 '<td>'+json.pageInfo.list[i].appName+'</td>'+
						 '<td><img src="'+json.pageInfo.list[i].appIcon+'" width="40" height="40" /></td>'+
						 '<td><a href="'+json.pageInfo.list[i].appUrl+'" target="_blank">'+json.pageInfo.list[i].appUrl+'</a></td>'+
						 '<td>'+platformType+'</td>'+
						 '<td>'+appType+'</td></tr>';
    		}
    		nd = nd + '</tbody></table>';
    		var node = $(nd);
			$('#needAppsDiv').append(node);
			
			//构建分页节点
			var nd1='';
			var nd2='';
			var nd3='';
			var nd4='';
			
			var nd5='';
			
			var nd6='';
			
			if(json.pageInfo.pageIndex==1) {
				nd1 = '<li><span href="#">&larr;</span></li>';
			} else {
				nd1 = '<li><a href="javascript:to_page2('+(json.pageInfo.pageIndex-1)+');">&larr;</a></li>';
			}
			
			if(json.pageInfo.pageNum>5){
				for(var i=1; i<=5; i++){
					if(json.pageInfo.pageIndex==i){
						nd2 = nd2 + '<li class="active"><span href="javascript:to_page2('+i+');">'+i+'</span></li>';
					} else {
						nd2 = nd2 + '<li><a href="javascript:to_page2('+i+');">'+i+'</a></li>';
					}
				}
			} else {
				for(var i=1; i<=json.pageInfo.pageNum; i++){
					if(json.pageInfo.pageIndex==i){
						nd2 = nd2 + '<li class="active"><span href="javascript:to_page2('+i+');">'+i+'</span></li>';
					} else {
						nd2 = nd2 + '<li><a href="javascript:to_page2('+i+');">'+i+'</a></li>';
					}
				}
			}
			
		
			for(var i=json.pageInfo.pageIndex-2; i<=json.pageInfo.pageIndex+2; i++){
				if(json.pageInfo.pageIndex==i){
					nd3 = nd3 + '<li class="active"><span href="javascript:to_page2('+i+');">'+i+'</span></li>';
				} else {
					nd3 = nd3 + '<li><a href="javascript:to_page2('+i+');">'+i+'</a></li>';
				}
			}
		
			
			if(json.pageInfo.pageNum<6){
				for(var i=1; i<=json.pageInfo.pageNum; i++){
					if(json.pageInfo.pageIndex==i){
						nd4 = nd4 + '<li class="active"><span href="javascript:to_page2('+i+');">'+i+'</span></li>';
					} else {
						nd4 = nd4 + '<li><a href="javascript:to_page2('+i+');">'+i+'</a></li>';
					}
				}
			} else {
				for(var i=json.pageInfo.pageNum-5; i<=json.pageInfo.pageNum; i++){
					if(json.pageInfo.pageIndex==i){
						nd4 = nd4 + '<li class="active"><span href="javascript:to_page2('+i+');">'+i+'</span></li>';
					} else {
						nd4 = nd4 + '<li><a href="javascript:to_page2('+i+');">'+i+'</a></li>';
					}
				}
			}
			
			if(json.pageInfo.pageIndex < 3){
				nd5 = nd5 + nd2;
			} else if(json.pageInfo.pageIndex < json.pageInfo.pageNum-2){
				nd5 = nd5 + nd3;
			} else {
				nd5 = nd5 + nd4;
			}
			
			if(json.pageInfo.pageIndex==json.pageInfo.pageNum){
				nd6 = '<li><span href="#">&rarr;</span></li>';
			} else {
				nd6 = '<li class="next"><a href="javascript:to_page2('+(json.pageInfo.pageIndex+1)+');">&rarr;</a></li>';
			}
			
			var node2 = $('<table class="table" frame="void">'+
						  '	<tr>'+
						  '		<td style="text-align:left;">'+
						  '	<!--分页-->'+
						  '	<nav>'+
						  '	<ul class="pagination">'+
						  '		<!-- 首页 -->'+
						  '		<li>'+
						  '			<a href="javascript:to_page2(1);">首页</a>'+
						  '		</li>'+ 
						  		nd1 +
						 		nd5 +
						  		nd6 +
						  '		<li>'+
						  '			<a>'+ json.pageInfo.pageIndex + '/' + json.pageInfo.pageNum+'</a>'+
						  '		</li>'+
						  '		<!-- 末页 -->'+
						  '		<li>'+
						  '			<a href="javascript:to_page2('+json.pageInfo.pageNum+');">末页</a>'+
						  '		</li>'+
						  '	</ul>'+
						  '	</nav>'+
						  '	</td>'+
						  '		<td style="text-align:right;">'+
						  '			<button class="btn btn-primary" onclick="history.back();">返回</button>'+
						  '		</td>'+
						  '	</tr>'+
						  '</table>');
			$('#pageDiv').append(node2);
    	},
    	"json"
    );
}
//添加游戏到该专题
function addAppToTopic(){
	//提交前先更新
	selectAppToTopic();
	//提交
	var topicId = $("#topicId").val();
	var topicName = $("#topicName").val();
	var appIds = $.trim(everyPageIds.toString());
	var data = {'topicId':topicId,'appIds':appIds};
	//校验
	if(!appIds.replace(/\,/g, '')){
		alertFailed("请至少选择一款游戏.");
		return false;
	}
	$.post("./addAppToTopic",
		   data,
		   function(json){
		   		if(json.status==1){
	                alertSuccess(json.msg);
	                setTimeout(function(){ window.location.href="./toSetApp?topicId="+topicId+"&topicName="+topicName; },1000);
	            } else {
	            	alertFailed(json.msg);
	            }
		   },'json');
}
//取消置顶
function cancelTopState(topicId,appId){
	//bootbox框
	var data = {'topicId':topicId,'appId':appId};
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
        message: '确定将该游戏取消置顶吗？',  
        callback: function(result) {  
            if(result) {
			    $.post('./cancelTop',data,
			    	function(json) {
			    		if(json.status == 1){
			    			alertSuccess(json.msg);
			    			setTimeout(function(){ 
							    document.forms[0].submit();
			    			},1000);
			    		} else {
			    			alertFailed(json.msg);
			    		}         
			    	},'json'
			    );
            } else {  
                return;  
            }  
        },  
        title: "取消置顶",  
    });
}
//搜索
function searchGame(){
	to_page2(1);
}