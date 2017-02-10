<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" 
	isELIgnored="false" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>	

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html";charset="utf-8" >    
    <title>修改角色</title>
    <meta name="viewreport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
  </head>
  <body>
		<div class="row">
			<div class="col-md-3">	
			</div>
			<div class="col-md-8">
			<form class="form-horizontal" role="form"  method="GET" onSubmit="return save();">
			<div class="form-group">
	        <label class="col-md-2 control-label" for="roleName">角色名称</label>	
	         <div class="col-md-6">
	         	<input type="hidden" id="roleId" name="roleId" value="${roleId}"/>
	         	<input type="text" class="form-control"  id="roleName" name="roleName" value="${rolePOJO.roleName}">        
	         </div>
	         </div>
	         <div class="form-group">
			 <label class="col-md-2 control-label">功能列表</label>
			
			 <div class="col-md-6">
	         	 <ul id="maintree"></ul>
	         </div>
	         </div>
	         
	         <div class="form-group">
				<div class="col-md-offset-2 col-md-4">
					<a id="btnUpdate" class="btn btn-primary" href="">保存</a>
					<a class="btn btn-default" href="javascript:history.back()">返回</a>
				</div>				
			</div>
			<div class="col-md-1">	
			</div>
		</form>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/resources/js/purview/roleUpdate.js"></script>	
	</body>
</html>