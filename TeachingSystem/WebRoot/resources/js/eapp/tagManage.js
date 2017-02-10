$(function() {
	//初始化selectpicker
	$('#platformType').selectpicker( {
		size : 80,
		noneSelectedText : '请选择'
	});
	$('#platformType').selectpicker('val', $('#platformType').attr('value'));
	$('#platformType').val(2);
	$("#addTag").click(function() {
		$("#myModal #myModalLabel").text("增加标签");
		//标示表单是提交向添加保存的
			$("#myModal #tagId").val(-1);
			$("#myModal #tagName").val("");
			$("#myModal #platformType").val("");

			$("#myModal #platformType").attr("value", "2");
			//重新渲染selectpicker
			$('#platformType').selectpicker('val',
					$('#platformType').attr('value'));
			$("#platformType").selectpicker("refresh");
			$('#myModal').modal( {
				keyboard : false,
				show : true
			})
		});
	//保存,增加标签信息
	$(document).delegate("#save", "click", function() {
		data = $(".form-horizontal").serialize();
		if (checkForm()) {

		} else {
			return false;

		}
		$.ajax( {
			type : "POST",
			data : data,
			url : "./save",
			dataType : "json",
			success : function(json) {
				$('#myModal').modal("hide");
				if (json.status == 1) {
					alertSuccess(json.msg);
					setTimeout(function() {
						document.forms[0].submit();
					}, 1000);
				} else {
					alertFailed(json.msg);
				}
			}
		});

	});
});
//异步删除激活码数据
function deleteOne(tagId) {
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
        message: '确定要删除该标签吗？',  
        callback: function(result) {  
            if(result) {
	$.post('./deleteTagInfo', {
		'tagId' : tagId
	}, function(data) {
		if(data.result==1){
			alertSuccess(data.msg);
			
				setTimeout(function(){  document.forms[0].submit(); },1000);
			
		}else{
			alertFaile(data.msg);
		}
				
	}, 'json');
	}  
        },  
        title: "激活码删除",  
        }); 
}
function updateOne(tagId){
	$("#myModal #myModalLabel").text("修改礼包信息");
	var data={'tagId':tagId};
	$.post('./getUpdateInfo',data,
	function(data) {
		$("#myModal #tagId").val(data.oneTag.tagId);
    	$("#myModal #tagName").val(data.oneTag.tagName); 
    	$("#myModal #platformType").attr("value",data.oneTag.platformType);
    	$('#myModal #platformType').selectpicker('val',$('#platformType').attr('value'));
        $("#myModal #platformType").val(data.oneTag.platformType);
       	$("#platformType").selectpicker("refresh");
        //显示模态框
        $('#myModal').modal({keyboard:false,show:true});
	},'json');
}

function to_page(pageIndex){
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
function to_page2(pageIndex){
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
//表单校验
function checkForm(){
	var tagName = $("#tagName").val().trim();
   	var platformType = $("#platformType").val();

   	//校验空
   	if(tagName==""){
   		$("#tagName").parent().next().text('应用标签名不能为空.');
   		return false;
   	}
   	if(platformType=="" ||platformType==null){
   		$("#platformType").parent().next().text('平台类型不能为空.');
   		return false;
   	}
   	//校验长度
   	if(!checkStringLength(tagName,10)){
   		$("#tagName").parent().next().text("标签名称不能超过10个字符.");
   		return false;
   	}
   	return true;
}
//清空提示信息
function clean(){
	$(".tip").text("");
}
//给游戏贴标签
function toBookTags(tagId){
	window.location.href = "./showApps?tagId="+tagId;
}
//删除游戏标签
function deleteAppTag(appId){
	var tagId = $("#tagId").val();
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
        message: '确定要取消该游戏的此标签吗？',  
        callback: function(result) {  
            if(result) {
	$.post('./deleteAppTag', {
		'tagId' : tagId,'appId':appId
	}, function(data) {
		if(data.status==1){
			alertSuccess(data.msg);
			
				setTimeout(function(){  document.forms[0].submit(); },1000);
			
		}else{
			alertFaile(data.msg);
		}
				
	}, 'json');
	}  
        },  
        title: "激活码删除",  
        });
	
	
}
//查询获取没有该标签的游戏列表
function showAddApp(tagId){
	window.location.href = "./showAddApp?tagId="+tagId;
}

//全选
$(function(){
	$(document).on( "click" , ".chooseAll", function() {
    	$("input[name='check_app']").prop("checked",this.checked);
    });
	$(document).on( "click" , "input[name='check_app']", function() {
    $("#all").prop("checked",$("input[name='check_app']").length == $("input[name='check_app']:checked").length?true:false);
    });
})
//异步分页
//翻页
var everyPageIds = new Array();//用来记录每页选中的id
var currentPage = 1;//用于记录当前页的页码

function to_page2(pageIndex){
	//检查选中放入全局变量中
	selectAppToTag();
	
	document.getElementById("pageIndex").value = pageIndex;
	var pageIndex = $("#pageIndex").val();
	var tagId = $("#tagId").val();
	//异步发请求，重置页面
	//1.清空table和分页两个div
	$("#needAppsDiv").empty();
	$("#pageDiv").empty();
	//2.异步发请求,返回数据后以节点形式加载到页面上
	var data = {'tagId':tagId,'pageIndex':pageIndex};
	$.post(
    	"./getNeedApps",
    	data,
    	function(json) {
    		//给全局变量当前页赋值
    		currentPage = json.pageInfo.pageIndex;
    		
    		var nd = '<table class="table table-hover" id="appsList"><thead><tr><th width="10%">'+
						 '<input type="checkbox" id="all" class="chooseAll"/>全选</th>'+
						 '<th width="10%">游戏图标</th>'+
						 '<th width="10%">游戏名称</th>'+
						 '<th width="60%">游戏地址</th>'
						 +'<th width="10%">游戏状态</th></tr></thead><tbody>';
    		for(var i=0; i<json.pageInfo.list.length; i++) 
    		{    
    			var appState;
    			if(json.pageInfo.list[i].appState==1){
    				appState='正在审核';
    			} 
    			else if(json.pageInfo.list[i].appState==2){
    				appState='通过';
    			}
    			else if(json.pageInfo.list[i].appState==3){
    				appState='未通过';
    			}
    			else if(json.pageInfo.list[i].appState==4){
    				appState='失效';
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
						 '<td><span class="checkbox"><label>'+ndCheck+'<input type="hidden" value="'+json.pageInfo.list[i].appId+'" />请选择</label></span></td>'+
						 '<td><img alt="'+json.pageInfo.list[i].appIcon+'"src="'+json.pageInfo.list[i].appIcon+'" width="40px" height="40px"></td>'+
						 '<td>'+json.pageInfo.list[i].appName+'</td>'+
						 '<td><a href="'+json.pageInfo.list[i].appUrl+ '"target="_blank">'+json.pageInfo.list[i].appUrl+'</a></td>'+
						 '<td>'+appState+'</td>'+
						 '</td></tr>';
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
						  '		<li><a href="#">共'+json.pageInfo.totalSize+'款游戏</a>'+
						  '		</li>'+
						  '	</ul>'+
						  '	</nav>'+
						  '	</td>'+
						  '		<td style="text-align:right;">'+
						  '			<button class="btn btn-primary" onclick="history.back();">返回</button>'+
						  '		</td>'+
						  '	</tr>'+
						  '</table>');
			$('#pageDiv').html(node2);
    	},
    	"json"
    );
}
//更新ID全局变量(翻页之前调用此方法)
function selectAppToTag(){
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
//添加游戏到该标签(翻页之前调用此方法)
function addAppToTag(){
	//提交前先更新
	selectAppToTag();
	//提交
	var tagId = $("#tagId").val();
	var appIds = $.trim(everyPageIds.toString());
	var data = {'tagId':tagId,'appIds':appIds};
	//校验
	if(!appIds.replace(/\,/g, '')){
		alertFailed("请至少先选择一款游戏,才能给它贴标签.");
		return false;
	} 
	$.post("./addAppToTag",
		   data,
		   function(json){
		   		if(json.status==1){
	                alertSuccess(json.msg);
	                var tagId = $("#tagId").val();
	                setTimeout(function(){ 
	                	window.location.href="./showApps?tagId="+tagId;
	                },1000);
	                
	                
	            } else {
	            	alertFailed(json.msg);
	            }
		   },'json');
}
