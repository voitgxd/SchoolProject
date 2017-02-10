function to_page(pageIndex) {
	document.getElementById("pageIndex").value = pageIndex;
	document.forms[0].submit();
}
$(function() {
	//日期选择
	$('.form_date').datetimepicker( {
		format : "yyyy-mm-dd",
		//language : 'fr',
		weekStart : 1,
		todayBtn : 2,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
});
//校验日期大小
function judgeDate() {
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	//校验日期大小
	if(beginTime != '' && endTime != ''){
		if (beginTime.replace(/\-/g, "\/") > endTime.replace(/\-/g, "\/")) {
			alertFailed("开始日期不能大于结束日期！");
			return false;
		}
	}

	return true;
}
//多条件查询
function searchUserLogin() {
	if (judgeDate()) {

	} else {
		return false;
	}

	to_page(1);
}
function cleanForm() {
	var beginTime = $("#beginTime").val("");
	var endTime = $("#endTime").val("");
}
