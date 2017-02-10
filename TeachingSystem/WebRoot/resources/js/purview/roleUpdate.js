var manager=null;
$(function(){
	  var roleId=$("#roleId").val();
	  var roleNameGlob=$("#roleName").val();
	  var data1 = [];
	  $.post("../roles/roleResourceUpdate",
			{data:roleId},  
			function(data){
	  			var obj= $.parseJSON(data.data);
				  $.each(obj,function(i,itm){
					  	if(itm.flag==1){
						  	//表示选中的菜单
							data1.push({id:itm.nodeid,pid:itm.parentid,type:itm.type,text:itm.text,ischecked:true});
					  	}else{
					  		data1.push({id:itm.nodeid,pid:itm.parentid,type:itm.type,text:itm.text});
						  	}
					});	
		//菜单树			 
	  $("#maintree").ligerTree({  
	           data:data1, 
	           isExpand: true,
	           autoCheckboxEven:true,
	           idFieldName :'id',
	           parentIDFieldName :'pid',
		       //slide:false
	       });
	  manager=$("#maintree").ligerGetTreeManager();
	},'json');

	//角色名称校验
  $("#roleName").blur(function(){
	  var roleName=$("#roleName").val();
		var $parent = $(this).parent();
		$parent.find(".formtips").remove();
		 if( $(this).is('#roleName') ){
			 if(roleName==null||roleName==""){
				 var errorMsg = '角色名称不能为空!';
				 	$parent.append('<span class="formtips onError" style="color:red">'+errorMsg+'</span>');
					}else if(roleName!=roleNameGlob){
						$.ajax({
							Type:"Get",
							url:"../role/roleCheck",
							data:{roleName:roleName},
							contentType:"application/json",
							dataType:"json",
							success:function(msg){
								$parent.append('<span class="formtips onError" style="color:red">'+msg.msg+'</span>');
								}	
							});	
						}
		 		}
		});
	//提交数据
	$("#btnUpdate").click(function(){
		var roleName=$("#roleName").val();
		var $parent = $("#roleName").parent();
		
		$parent.find(".formtips").remove();
			if(roleName==null||roleName==""){
			 var errorMsg = '角色名称不能为空!';
			 	$parent.append('<span class="formtips onError" style="color:red">'+errorMsg+'</span>');
			 	return false;
		}
		var a = $(".l-checkbox-incomplete");	
		if(a.length>0){
			a.removeClass("l-checkbox-incomplete").addClass("l-checkbox-checked");
                
		}	
		var notes = manager.getChecked();
		var ids="";
		if(notes.length>0){
			for(var i=0;i<notes.length;i++){
				ids+=notes[i].data.id+",";
			}	
		}else{
			alert("角色需要选择关联的资源!");
			return false;	
			}
		$("#btnUpdate").attr("href","../roles/roleUpdate?roleId="+roleId+"&resId="+ids+"&roleName="+roleName+"&roleNameGlob="+roleNameGlob);
		});	
	});
//禁用回车事件
function save(){
	$("#roleName").keydown(function(e){
		var e=e||event;
		var keyNum=e.which||e.keyCode;
		return keyNum==13?false:true;
		});
	return false;
}