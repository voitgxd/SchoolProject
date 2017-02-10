//验证长度
function checkStringLength(value, length){
	if(value.length<length){
		return true;
	} else {
		return false;
	}
}

//弹窗成功信息
function alertBoxSuccess(msg){
	var message = "操作成功!";
	if(msg){
		message = msg;
	}
	bootbox.dialog({
       message: message,
       title: "提示信息"
	});
}
//弹窗失败信息
function alertBoxFailed(msg){
	var message = "操作失败!";
	if(msg){
		message = msg;
	}
	bootbox.dialog({
        message: message,
		title: "提示信息"
	});
}
//弹窗成功信息
function alertSuccess(msg, ishide){
	var message = "操作成功";
	if(msg!=undefined){
		message = msg;
	}
	if($('#alert_id').length>0){
    	$('#alert_id').remove();
	}
	$('body').append('<div id="alert_id" class="alert alert-success my-alert">'+message+'</div>') ;
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
	$('#alert_id').css('top',winHeight/2-100) ;
	var top = parseInt($('#alert_id').css('top')) ;
	//出现
	$('#alert_id').animate({opacity: 1,top: (top-30)+'px'},'slow') ;
	if(ishide==undefined) ishide = true;
	if(ishide){
		//3000ms之后隐藏，并删除
		setTimeout("$('#alert_id').animate({opacity: 0,top: top+'px'},'slow');$('#alert_id').remove();",3000) ;
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
	$('body').append('<div id="alert_id" class="alert alert-warning my-alert"><strong>Warning!</strong>  '+message+'</div>') ;
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
	$('#alert_id').css('top',winHeight/2-100) ;
	var top = parseInt($('#alert_id').css('top')) ;
	//出现
	$('#alert_id').animate({opacity: 1,top: (top-30)+'px'},'slow') ;
	if(ishide==undefined) ishide = true;
	if(ishide){
		//3000ms之后隐藏，并删除
		setTimeout("$('#alert_id').animate({opacity: 0,top: top+'px'},'slow');$('#alert_id').remove();",5000) ;
	}
}
function subForm(formName){
	$('#'+formName).find('button').attr("disabled", true);
	$('#'+formName).find('button[name="submit"]').text('上传中');
}
function cancleForm(formName){
	$('#'+formName).find('button').attr("disabled", false);
	$('#'+formName).find('button[name="submit"]').text('上传');
}
//输入校验错误
function checkError(formName,inputName,errorMsg){
	var input = $('#'+formName+' [name="'+inputName+'"]');
	$(input).focus();
	$(input).parent().parent().addClass('has-error');
	$(input).parent().parent().addClass('has-feedback');
	$(input).parent().next().empty();
	$(input).parent().next().append('<span class="glyphicon glyphicon-remove form-control-feedback"> '+errorMsg+'</span>');
}
//输入校验警告
function checkWarning(formName,inputName,errorMsg){
	var input = $('#'+formName+' [name="'+inputName+'"]');
	$(input).parent().parent().addClass('has-warning');
	$(input).parent().parent().addClass('has-feedback');
	$(input).parent().next().empty();
	$(input).parent().next().append('<span class="glyphicon glyphicon-warning-sign form-control-feedback"> '+errorMsg+'</span>');
}
//输入校验成功
function checkSuccess(formName,inputName){
	var input = $('#'+formName+' [name="'+inputName+'"]');
	$(input).parent().parent().removeClass('has-error');
	$(input).parent().parent().removeClass('has-warning');
	$(input).parent().next().empty();
}