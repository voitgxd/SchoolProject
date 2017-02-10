//翻页
function to_page(pageIndex) {
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
$(function(){
	//初始化selectpicker
    $('#recommendTypeSearch').selectpicker({size:80,noneSelectedText:'请选择'});
	$('#recommendTypeSearch').selectpicker('val',$('#recommendType').attr('value'));
	
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
	$('select#recommendTypeSearch').change(function(){
		$("#recommendType").val($("#recommendTypeSearch").val());
		document.forms[0].submit();
	});
	//下架
	$(document).delegate("#cancelRecommend","click",function(){
	    //bootbox框
		var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
		var appId = $(inputObj).val();
		var recommendType = $("#recommendType").val();
	    data={'appId':appId,'recommendType':recommendType};
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
	        message: '确定取消推荐选中应用吗？',  
	        callback: function(result) {  
	            if(result) {
				    $.post('./cancelRecommend',data,
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
	        title: "取消推荐应用",  
        });
	});
	//上升
	$(document).delegate("#up","click",function(){
		var inputObj = $(this).parent().parent().parent().children().eq(0).children().eq(0);
	    var appId = $(inputObj).val();
	    var recommendType = $("#recommendType").val();
	    var flag = 1;
	    var data={'flag':flag,'recommendType':recommendType,'appId':appId};
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
	    var recommendType = $("#recommendType").val();
	    var flag = -1;//控制上下移动的状态
	    var data={'flag':flag,'recommendType':recommendType,'appId':appId};
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
//查询未在该推荐中的app
function toRecommendNeedApp(recommendType){
	window.location.href="./toRecommendNeedApp?recommendType=" + recommendType;
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
function selectAppToRecommend(){
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
	selectAppToRecommend();
	
	document.getElementById("pageIndex").value = pageIndex;
	var pageIndex = $("#pageIndex").val();
	var recommendType = $("#recommendType").val();
	var queryGameName = $.trim($("#appNameForSearch").val());
	//异步发请求，重置页面
	//1.清空table和分页两个div
	$("#needAppsDiv").empty();
	$("#pageDiv").empty();
	//2.异步发请求,返回数据后以节点形式加载到页面上
	var data = {'recommendType':recommendType,'pageIndex':pageIndex,'queryGameName':queryGameName};
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
//弹出置顶模态框
function toChooseTime(recommendType){
	//提交前先更新
	selectAppToRecommend();
	var appIds = $.trim(everyPageIds.toString());
	//校验
	if(!appIds.replace(/\,/g, '')){
		alertFailed("请至少选择一款游戏.");
		return false;
	}
	$("#formRecommendType").val(recommendType);
	$("#recommendReason").val("");
	$("#effectTime").val("");
	$("#expireTime").val("");
	
	$('#timeForm .check-span').empty();
    $('#timeForm .form-group').removeClass('has-error has-warning has-feedback');
	$('#myModal').modal({keyboard:false,show:true});
}
//添加游戏到该推荐
function addAppToRecommend(){
	//提交
	var recommendType = $("#recommendType").val();
	var recommendReason = $("#recommendReason").val();
	var effectTime = $("#effectTime").val();
	var expireTime = $("#expireTime").val();
	var appIds = $.trim(everyPageIds.toString());
	
	var formName = "timeForm";
	if(recommendReason==''){
		checkError(formName,'recommendReason', '请输入推荐理由！') ;
		cancleForm(formName);
		return false;
	}else if(!checkStringLength(recommendReason,20)){
		checkError(formName,'recommendReason', '推荐理由最多20个字符！') ;
		cancleForm(formName);
		return false;
	}else{
		checkSuccess(formName,'recommendReason');
	}
	
	if(effectTime == '' && expireTime != ''){
		checkError(formName,'effectTime', '请选择生效时间！') ;
		return false;
	}else if(effectTime != '' && expireTime == ''){
		alertFailed('请选择过期时间！');
		return false;
	}
	
	if(effectTime != "" && expireTime != ""){
		if (effectTime > expireTime){
			alertFailed('生效时间不能大于失效时间！');
			return false;
		}
	}
	var data = {'recommendType':recommendType,'recommendReason':recommendReason,'effectTime':effectTime,
		'expireTime':expireTime,'appIds':appIds};
	
	$('#myModal').modal('hide');
	
	$.post("./addAppToRecommend",
		   data,
		   function(json){
		   		if(json.status==1){
	                alertSuccess(json.msg);
	                setTimeout(function(){ window.location.href="./toIndex?recommendType="+recommendType; },1000);
	            } else {
	            	alertFailed(json.msg);
	            }
		   },'json');
}
//搜索
function searchApp(){
	to_page2(1);
}