var manager=null;
$(function(){
	  var data1 = [];
	  $.post("../roles/getRoleResources",function(data){
	  var obj= $.parseJSON(data.data);
	  $.each(obj,function(i,itm){
			data1.push({id:itm.nodeid,pid:itm.parentid,type:itm.type,text:itm.text});
		 });
	  console.info("data1="+data1.id+"="+data1.text);	 
	  $("#maintree").ligerTree({  
	           data:data1, 
	           isExpand: false,
	           //autoCheckboxEven:false,
	           idFieldName :'id',
	           parentIDFieldName :'pid',
		       slide:true
	       });
	  manager=$("#maintree").ligerGetTreeManager();
	},'json');


$("#roleName").blur(function(){
  var roleName=$("#roleName").val();
	var $parent = $(this).parent();
	$parent.find(".formtips").remove();
	 if( $(this).is('#roleName') ){
		 if(roleName==null||$.trim(roleName)==""){
			 var errorMsg = '角色名称不能为空!';
			 	$parent.append('<span class="formtips onError" style="color:red">'+errorMsg+'</span>');
				}else{
					$.ajax({
						Type:"GET",
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

	//角色名称校验
	$("#btnSave").click(function(){
		var roleName=$("#roleName").val();
		var $parent = $("#roleName").parent();
		$parent.find(".formtips").remove();
			if(roleName==null||$.trim(roleName)==""){
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
		$("#btnSave").attr("href","../role/addRole?resId="+ids+"&roleName="+roleName);
		});	
	});

function save(){
	$("#roleName").keydown(function(e){
		var e=e||event;
		var keyNum=e.which||e.keyCode;
		return keyNum==13?false:true;
		});
	return false;
}