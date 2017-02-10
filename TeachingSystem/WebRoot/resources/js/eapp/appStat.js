function to_page(pageIndex) {
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
$(function() {
	//初始化selectpicker
	$('#loginState').selectpicker( {
		size : 80,
		noneSelectedText : '请选择查询条件'
	});
	$('#loginState').selectpicker('val', $('#loginState').attr('value'));
	//日期选择
	$('.form_datetime').datetimepicker( {
		//language:  'fr',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1
	});
});
//校验日期大小
function judgeDate() {
	//校验数字

	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var passportId = $("#passportId").val().trim();
	var loginState = $("#loginState").val();
	var intReg = /^[1-9]\d*$/;//非零的正整数
	if (passportId == null || passportId == "") {
		return true;
	}
	if (!intReg.test(passportId)) {
		alertFailed("passportID由数字构成，不能为字符!");
		return false;
	}

	//校验长度
	else if (!checkStringLength(passportId, 15)) {
		alertFailed("标签名称不能超过15个字符.");
		return false;
	}
	//校验日期大小
	if (beginTime.replace(/\-/g, "\/") > endTime.replace(/\-/g, "\/")) {
		alertFailed("开始日期不能大于结束日期！");
		return false;
	}

	return true;
}
//多条件查询
function searchUserLogin() {
	if ($("#loginState").val() == null || $("#loginState").val() == "") {
		$("#loginState").val(-1);
	}
	if (judgeDate()) {

	} else {
		return false;
	}

	to_page(1);
}
function cleanForm() {
	var beginTime = $("#beginTime").val("");
	var endTime = $("#endTime").val("");
	var passportId = $("#passportId").val("");
	var loginState = $("#loginState").val("");
}