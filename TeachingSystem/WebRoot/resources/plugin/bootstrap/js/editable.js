var productIdMain = -1;// 产品ID全局变量
$(function(){
 $('#productName').editable({
     type: 'text',
     pk: productIdMain,//必须有
     url: './editable!updateProduct',
     title: '输入产品名称',
     name: 'productName',
     emptyclass: '',
     ajaxOptions: {
      type: 'post',
      dataType: 'json'
  },
     validate: function(value) {
      if($.trim(value) == '') {
       return '产品名称不能为空';
      }else if(!checkStringLength($.trim(value),20)){
    return '产品名称最多20个字符';
   }
  },
  success: function(response, newValue) {
   if(response.resultCode==1){
    $('[ref="productName"]').text($.trim(newValue));
    $('#product_'+productIdMain).children('div').text($.trim(newValue));
    $('#product_'+productIdMain).attr('title',$.trim(newValue));
   }
   if(response.resultCode<0)return response.message;//错误，返回错误信息
  },
     error: function(response, newValue) {
      if(response.status === 500) {
       return 'Service unavailable. Please try later.';
      }else{
       return response.message;
      }
  }
 });
});
function checkStringLength(value, length){
 if(value.length<20){
  return true;
 } else {
  return false;
 }
}
function test(){
	bootbox.confirm({  
        buttons: {  
            confirm: {  
                label: '确定',  
                className: 'btn-primary'  
            },  
            cancel: {  
                label: '取消',  
                className: 'btn-default'  
            }  
        },  
        message: '确定给选中用户发送邮件吗？',  
        callback: function(result) {  
            if(result) {  
                alert('点击确认按钮了');  
            } else {  
                alert('点击取消按钮了');  
            }  
        },  
        title: "发送邮件",  
        });  
}
//弹窗成功信息
function alertSuccess(msg, ishide){
	var message = "操作成功";
	if(msg!=undefined){
		message = msg;
	}
	if($('#alert_id').length>0){
    	$('#alert_id').remove() ;
	}
	$('body').append('<div id="alert_id" class="alert alert-success my-alert"><a href="#" class="close" data-dismiss="alert">&times;</a>'+message+'</div>') ;
	// 获取窗口宽度
	var winWidth = 0 ;
	var winHeight = 0 ;
	if (window.innerWidth){
		winWidth = window.innerWidth;
	}else if ((document.body) && (document.body.clientWidth)){
		winWidth = document.body.clientWidth;
	}
	// 获取窗口高度
	if (window.innerHeight){
		winHeight = window.innerHeight;
	}else if ((document.body) && (document.body.clientHeight)){
		winHeight = document.body.clientHeight;
	}
	//修改警告框的位置
	$('#alert_id').css('left',(winWidth-$('#alert_id').width())/2-20) ;
	$('#alert_id').css('top',winHeight/2) ;
	var top = parseInt($('#alert_id').css('top')) ;
	//出现
	$('#alert_id').animate({opacity: 1,top: (top-30)+'px'},'slow') ;
	if(ishide==undefined) ishide = true;
	if(ishide){
		//3000ms之后隐藏，并删除
		setTimeout("$('#alert_id').animate({opacity: 0,top: top+'px'},'slow');$('#alert_id').remove();",5000) ;
	}
}
//弹窗失败信息
function alertFailed(msg, ishide){
	var message = "操作失败";
	if(msg!=undefined){
		message = msg;
	}
    if($('#alert_id').length>0){
    	$('#alert_id').remove() ;
	}
	$('body').append('<div id="alert_id" class="alert alert-warning my-alert"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Warning!</strong>  '+message+'</div>') ;
	// 获取窗口宽度
	var winWidth = 0 ;
	var winHeight = 0 ;
	if (window.innerWidth){
		winWidth = window.innerWidth;
	}else if ((document.body) && (document.body.clientWidth)){
		winWidth = document.body.clientWidth;
	}
	// 获取窗口高度
	if (window.innerHeight){
		winHeight = window.innerHeight;
	}else if ((document.body) && (document.body.clientHeight)){
		winHeight = document.body.clientHeight;
	}
	//修改警告框的位置
	$('#alert_id').css('z-index',10001) ;
	$('#alert_id').css('left',(winWidth-$('#alert_id').width())/2-20) ;
	$('#alert_id').css('top',winHeight/2) ;
	var top = parseInt($('#alert_id').css('top')) ;
	//出现
	$('#alert_id').animate({opacity: 1,top: (top-30)+'px'},'slow') ;
	if(ishide==undefined) ishide = true;
	if(ishide){
		//3000ms之后隐藏，并删除
		setTimeout("$('#alert_id').animate({opacity: 0,top: top+'px'},'slow');$('#alert_id').remove();",10000) ;
	}
}
