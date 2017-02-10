$(function(){
	var id = 0;//修改数据的ID
    //初始化selectpicker
    $('#platformType').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#platformType').selectpicker('val',$('#platformType').attr('value'));
	$('#appType').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#appType').selectpicker('val',$('#appType').attr('value'));
	$('#type').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#type').selectpicker('val', $('#type option:first').attr('value'));
	
	$('#appState').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#appState').selectpicker('val', $('#appState').attr('value'));
	$('select#appState').change(function(){
		$("#appStateFlag").val($("#appState").val());
		document.forms[0].submit();
	});
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
	//获取修改时显示的数据，弹出modal模态对话框
    $(document).delegate("#update","click",function(){
		$('#myModal .check-span').empty();
    	$('#myModal .form-group').removeClass('has-error has-warning has-feedback');
        $("#myModal #myModalLabel").text("修改应用");
        var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
		id = $(inputObj).val();
        data={'id':id};
        $.post('./toUpdate',data,
        	function(json) {
                if(json.status==1){
                	$("#myModal #flag").val(1);
                    $("#myModal #appId").val(json.data.appId);                           
                    $("#myModal #appName").val(json.data.appName);
                    $("#myModal #appIcon").attr("disabled", true);
                    $("#myModal #appUrl").val(json.data.appUrl);
                   	$("#myModal #platformType").val(json.data.platformType); 
                   	$("#myModal #appType").val(json.data.appType);
                   	$("#myModal #appSummary").val(json.data.appSummary);
                   	$("#myModal #appDesc").val(json.data.appDesc);
                   	$("#myModal #addTime").val(json.data.addTime);
                   	$("#myModal #appState").val(json.data.appState);
                   	$("#myModal #developerId").val(json.data.developerId);
                   	//刷新下拉框
                   	$('#platformType').selectpicker('refresh');
                   	$('#appType').selectpicker('refresh');
                    //显示模态框
                    $('#myModal').modal({keyboard:false,show:true});
                } else {
                    alertFailed(json.msg);
                }
        	},'json'
        );
	});
    //保存,增加和修改时都使用这个方法提交表单，区别在于修改时有ID参数而增加时没有
    $(document).delegate("#save","click",function(){
    	var formName = "myModal";
    	var flag = $("#myModal #flag").val();
        var appId = $("#myModal #appId").val();                           
        var appName = $("#myModal #appName").val();
        var appUrl = $("#myModal #appUrl").val();
       	var platformType = $("#myModal #platformType").val(); 
       	var appType = $("#myModal #appType").val();  
       	var appSummary = $("#myModal #appSummary").val();
       	var appDesc = $("#myModal #appDesc").val();
       	var developerId = $("#myModal #developerId").val();
       	
       	if(appName==''){
			checkError(formName,'appName', '请输入应用名称！') ;
			cancleForm(formName);
			return false;
		}else if(!checkStringLength(appName,20)){
			checkError(formName,'appName', '应用名称最多20个字符！') ;
			cancleForm(formName);
			return false;
		}else{
			checkSuccess(formName,'appName');
		}
       	
       	if(appUrl==''){
			checkError(formName,'appUrl', '请输入应用地址！') ;
			cancleForm(formName);
			return false;
		}else if(!checkStringLength(appUrl,100)){
			checkError(formName,'appUrl', '应用地址最多100个字符！') ;
			cancleForm(formName);
			return false;
		}else{
			checkSuccess(formName,'appUrl');
		}
       	
       	if(platformType==''){
			checkError(formName,'platformType', '请选择平台类型！') ;
			cancleForm(formName);
			return false;
		}else{
			checkSuccess(formName,'platformType');
		}
       	
       	if(appType==''){
			checkError(formName,'appType', '请选择应用类型！') ;
			cancleForm(formName);
			return false;
		}else{
			checkSuccess(formName,'appType');
		}
       	
       	if(appSummary==''){
			checkError(formName,'appSummary', '请输入应用简介！') ;
			cancleForm(formName);
			return false;
		}else if(!checkStringLength(appSummary,20)){
			checkError(formName,'appSummary', '应用简介最多20个字符！') ;
			cancleForm(formName);
			return false;
		}else{
			checkSuccess(formName,'appSummary');
		}
       	
       if(appDesc==''){
			checkError(formName,'appDesc', '请输入应用描述！') ;
			cancleForm(formName);
			return false;
		}else if(!checkStringLength(appDesc,100)){
			checkError(formName,'appDesc', '应用描述最多100个字符！') ;
			cancleForm(formName);
			return false;
		}else{
			checkSuccess(formName,'appDesc');
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
       	
       	//根据flag序列化不同内容
       	if(flag == -1){
       		var fileId = "appIconId";//appIconId
			var url = './save';
       		var data = {'flag':flag,'appId':appId,'appName':appName,'appUrl':appUrl,'platformType':platformType,'appType':appType,
       			'appSummary':appSummary,'appDesc':appDesc,'developerId':developerId};
			alertSuccess("正在上传，请稍后...",false);
			$('#myModal').modal('hide');
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
		            	if(data.status==1){
		                	alertSuccess('操作成功');
		                	setTimeout(function(){  window.location.href="./toIndex"; },1000);
		                }else{
		                	alertFailed(data.msg);
			            }
		            },
		            error: function (data, status, e)// 服务器响应失败处理函数
		            {
		            	alertFailed('操作失败');
		            }
		        }
		    );
       	} else {
       		 var fileName = $('#' + formName + ' [name="file_info"]').val();
       		 if(fileName == ""){
       			 var data = {'flag':flag,'appId':appId,'appName':appName,'appUrl':appUrl,'platformType':platformType,'appType':appType,
       				'appSummary':appSummary,'appDesc':appDesc,'developerId':developerId};
	       		 $.ajax({
		            type:"POST",
		            data:data,
		            url:"./updateSave",
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
       		 } else {
       			var fileId = "appIconId";//appIconId
				var url = './updateSaveWithIcon';
	       		var data = {'flag':flag,'appId':appId,'appName':appName,'appUrl':appUrl,'platformType':platformType,'appType':appType,
	       			'appSummary':appSummary,'appDesc':appDesc,'developerId':developerId};
				alertSuccess("正在上传，请稍后...",false);
				$('#myModal').modal('hide');
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
			            	if(data.status==1){
			                	alertSuccess('操作成功');
			                	setTimeout(function(){  window.location.href="./toIndex"; },1000);
			                }else{
			                	alertFailed(data.msg);
				            }
			            },
			            error: function (data, status, e)// 服务器响应失败处理函数
			            {
			            	alertFailed('操作失败');
			            }
			        }
			    );
       		 }
       	}
    });
	//下架
	$(document).delegate("#delete","click",function(){
	    //bootbox框
		var inputObj = $(this).parent().parent().parent().children().eq(1).children().eq(0);
		id = $(inputObj).val();
	    data={'id':id};
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
	        message: '确定下架选中应用吗？',  
	        callback: function(result) {  
	            if(result) {
				    $.post('./delete',data,
				    	function(json) {
				    		if(json.status == 1){
				    			alertSuccess(json.msg);
				    			setTimeout(function(){ document.forms[0].submit(); },1000);
				    		} else {
				    			alertFailed(json.msg);
				    		}         
				    	},'json'
				    );
	            } else {  
	                return;  
	            }  
	        },  
	        title: "下架应用",  
        });
	});
	//增加,弹出modal模态对话框
    $("#app_add").click(function(){
		$('#myModal .check-span').empty();
    	$('#myModal .form-group').removeClass('has-error has-warning has-feedback');
        $("#myModal #myModalLabel").text("添加应用");
        //标示表单是提交向添加保存的
        $("#myModal #flag").val(-1);
        $("#myModal #appId").val("");                           
        $("#myModal #appName").val("");
//        $("#myModal #appIcon").val("");
//        $("#myModal #appIcon").attr("disabled", false);
        $("#myModal #appUrl").val("");
       	$("#myModal #platformType").val(""); 
       	$("#myModal #appType").val("");
       	$("#myModal #appSummary").val("");
       	$("#myModal #appDesc").val("");
       	$("#myModal #developerId").val("");
       	clean();
       	//重新渲染select
       	$("#platformType").attr("value","");
       	$("#appType").attr("value","");
		refreshPlugin();
		$('#myModal .tip').empty();
        $('#myModal').modal({keyboard:false,show:true});
    });  
});
//翻页
function to_page(pageIndex){
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
//清空提示信息
function clean(){
	$(".tip").text("");
}

//重新渲染select
function refreshPlugin(){
	$('#platformType').selectpicker('val', $('#platformType option:first').attr('value'));
	$('#appType').selectpicker('val', $('#appType option:first').attr('value'));
}
//选择游戏上传文件
function chooseFile(th, formName) {
	var fileName = $(th).val();
	$('#' + formName + ' [name="file_info"]').val(fileName);
}
//到上传游戏界面
var appTypeMain;
function toUploadApp(appType){
	appTypeMain = appType;
	$('#appForm #changeFormDiv').empty();
	$('#appForm .check-span').empty();
    $('#appForm .form-group').removeClass('has-error has-warning has-feedback');
    if(null==appType){
		return;
	}
	if(appType==1){
		//手游
		var node = $('<div class="form-group">'+
					 '<label for="file_info" class="col-sm-2 control-label" style="padding-left: 0px;">'+
					 '手游安装包：'+
					 '</label>'+
					 '<div class="col-sm-4">'+
					 '<input type="text" class="form-control" name="file_info" readonly="readonly" placeholder="选择手游安装包">'+
					 '</div>'+
					 '<div class="col-sm-2" style="text-align: center;">'+
					 '<button type="button" class="btn btn-primary my-btn btn-input">'+
					 '<i class="fa fa-upload" style="line-height: 2;"></i> 选择安装包'+
					 '</button>'+
					 '<input id="appApk" type="file" class="upload" name="data" onchange=\'chooseFile(this,"appForm");\'>'+
					 '</div>'+
					 '<span class="col-sm-4 check-span check-spe-span2"></span>'+
					 '</div>');
		$('#appForm #changeFormDiv').append(node);
	} else {
		//H5
		var node = $('<div class="form-group">'+
					 '<label for="file_info" class="col-sm-2 control-label" style="padding-left: 0px;">'+
					 'H5游戏压缩包：'+
					 '</label>'+
					 '<div class="col-sm-4">'+
					 '<input type="text" class="form-control" name="file_info" readonly="readonly" placeholder="选择H5游戏压缩包(只支持.zip)">'+
					 '</div>'+
					 '<div class="col-sm-2" style="text-align: center;">'+
					 '<button type="button" class="btn btn-primary my-btn btn-input">'+
					 '<i class="fa fa-upload" style="line-height: 2;"></i> 选择压缩包'+
					 '</button>'+
					 '<input id="appApk" type="file" class="upload" name="data" onchange=\'chooseFile(this,"appForm");\'>'+
					 '</div>'+
					 '<span class="col-sm-4 check-span check-spe-span2"></span>'+
					 '</div>');
		$('#appForm #changeFormDiv').append(node);
	}
	$('#myModal2').modal({keyboard:false,show:true});
}
//调出图标上传模态框
function toUploadIcon(appId){
	$("#iconAppId").val(appId);
	$('#iconForm .check-span').empty();
    $('#iconForm .form-group').removeClass('has-error has-warning has-feedback');
	$('#myModal3').modal({keyboard:false,show:true});
}
// 上传游戏
function uploadApp(){
	var formName = "appForm" ;
	subForm(formName);
	var inputObj = $(this).parent().children().eq(0);
	var appId = $(inputObj).val();
	var address = $.trim($('#'+formName+' [name="address"]').val());
	var file = $('#'+formName+' [name="data"]').val();
	if(address==''){
		checkError(formName,'address', '请输入唯一上传地址！') ;
		cancleForm(formName);
		return false;
	}else if(!checkStringLength(address,100)){
		checkError(formName,'address', '上传地址最多100个字符！') ;
		cancleForm(formName);
		return false;
	}else if(!/^([a-zA-Z0-9]|[\/]|[.]){0,20}$/.test(address)){
		checkError(formName,'address', '上传地址字母数字和"/"！') ;
		cancleForm(formName);
		return false;
	}else{
		checkSuccess(formName,'address');
	}
	if(appTypeMain == 1){
		//手游
		if(file==''){
			checkError(formName,'data', '请选择待上传安装包！') ;
			cancleForm(formName);
			return false;
		}else{
			var suffix = file.substring(file.lastIndexOf('.') + 1);
			if(suffix!='apk'){
				checkError(formName, 'data', '仅支持apk格式') ;
				cancleForm(formName);
				return false;
			}else{
				checkSuccess(formName,'data');
			}
		}
	} else {
		if(file==''){
			checkError(formName,'data', '请选择待上传压缩包！') ;
			cancleForm(formName);
			return false;
		}else{
			var suffix = file.substring(file.lastIndexOf('.') + 1);
			if(suffix!='zip'){
				checkError(formName, 'data', '仅支持zip格式') ;
				cancleForm(formName);
				return false;
			}else{
				checkSuccess(formName,'data');
			}
		}
	}
	
	var fileId = $('#'+formName+' [name="data"]').attr('id');//appApk
	var url = './uploadApp';
	var data = {'appId':appId,'address':address};
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
                	alertSuccess('上传成功');
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
function uploadIcon(){
	var formName = "iconForm";
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
	
	
	var fileId = $('#'+formName+' [name="data"]').attr('id');//appApk
	var url = './uploadIcon';
	var appId = $("#iconAppId").val();
	var data = {'appId':appId};
	alertSuccess("正在上传，请稍后...",false);
	$('#myModal3').modal('hide');
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
            	alertFailed('上传失败');
            }
        }
    );
}

function uploadExcell(){
	var formName = "excellForm";
	var fileId = $('#'+formName+' [name="data"]').attr('id');//appExcell
	var url = './uploadExcell';
	alertSuccess("正在上传，请稍后...",false);
	$('#myModal4').modal('hide');
	// 提交
	$.ajaxFileUpload
    (
        {
            url:url,// 用于文件上传的服务器端请求地址
            secureuri:false,// 一般设置为false
            fileElementId: fileId,// 文件上传空间的id属性 <input type="file"
									// id="file" name="file" />
            dataType: 'json',// 返回值类型 一般设置为json
            success: function (data, status)  // 服务器成功响应处理函数
            {
        		cancleForm(formName);
            	if(data.status==1){
                	alertSuccess('导入成功');
                	setTimeout(function(){  window.location.href="./toIndex"; },1000);
                }else{
                	alertFailed(data.msg);
	            }
            },
            error: function (data, status, e)// 服务器响应失败处理函数
            {
            	cancleForm(formName);
            	alertFailed('导入失败');
            }
        }
    );
}
//下载模板
function downloadExcell(){
	window.location.href="./downloadExcell";
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
//批量审核
function toCheckChoosedApps(flag){
	var checkObjs = $(":checkbox[name='check_app']:checked");
	//判断是否选中了checkbox
	if(checkObjs.length==0){
		alertFailed("请至少选择一个应用！");
		return;
	}
	//提示
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
        message: '确定审核选中应用吗？',  
        callback: function(result) {  
            if(result) {
            	checkChoosedApps(flag);
            } else {  
                return;  
            }
        },  
        title: "审核应用",  
        });
}
//审核选中应用
function checkChoosedApps(flag) {
    //取得选中的checkbox行的id值
	var checkObjs = $(":checkbox[name='check_app']:checked");
    var ids = new Array();
    for(var i=0;i<checkObjs.length;i++){
    	var labelObj = $(checkObjs[i]).parent();
    	var inputObj = $(labelObj).children().eq(1);
    	ids.push($(inputObj).val());
    }
    var appIds = $.trim(ids.toString());
    var statusCode;
    if(flag == true){
    	statusCode = 1;
    } else {
    	statusCode = -1;
    }
    var data = {'appIds':appIds,'statusCode':statusCode};
    //提交请求更新
    $.post(
    	"./checkChooseApp",
    	data,
    	function(json) {
			if(json.status==1){
                alertSuccess(json.msg);
                setTimeout(function(){  document.forms[0].submit(); },1000);
            } else {
            	alertFailed(json.msg);
            }
    	},'json'
    );
}
//切换应用状态展示应用
function changeAppState(){
	alert('sssss');
	$("#appStateFlag").val($("#appState").val());
	document.forms[0].submit();
}
//搜索
function searchApp(){
	$("#queryGameName").val($.trim($("#appNameForSearch").val()));
	document.forms[0].submit();
}
