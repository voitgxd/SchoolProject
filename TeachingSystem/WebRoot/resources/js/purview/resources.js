var manager=null;
$().ready(function(){
	  //获取所有资源
	  var data1 = [];
	  $.post("../roles/getRoleResources",function(data){
	  var obj= $.parseJSON(data.data);
	  $.each(obj,function(i,itm){
			data1.push({id:itm.nodeid,pid:itm.parentid,type:itm.type,text:itm.text});
		 });
	  console.info("data1="+data1.id+"="+data1.text);	 
	  $("#maintree").ligerTree({  
	           data:data1, 
	           isExpand: true,
	           idFieldName :'id',
	           parentIDFieldName :'pid',
	           checkbox: false,
	       });	  
	  manager=$("#maintree").ligerGetTreeManager();
	},'json');  
	  
	  
	//增加节点
    $("#saveBtn").click(function(){
    	var resName = $("#resName").val();
    	var resOrder = $("#resOrder").val();
    	if(resName==""){
    	   $(".form-group #nameMsg").html("<span style='color:red'>请输入资源名称</span>");
    	   return false;
    	}
    	if(resOrder == ""){
    		$("#resOrder").val(-1);
    	}
    });	
});



//删除节点
function removeTreeItem()
{
    var node = manager.getSelected();
    if (node){ 	
    	$.ligerDialog.confirm('你确定删除\"'+node.data.text+'\"吗？', function (result)
        {
           if(result){
        	   manager.remove(node.target);
               window.location.href="../resources/deleteResource?resId="+node.data.id;
           }
           else 
               return;
        });
    }  
    else
    	 $.ligerDialog.error('请选择节点！');
}




