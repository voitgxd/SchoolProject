<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" 
	isELIgnored="false" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>	

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html";charset="utf-8" >    
    <title>用户管理</title>
    <meta name="viewreport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
  </head>
  
  <body>
  	<%--<div class="panel panel-default">
  		<div class="panel-body">
  			<div class="row">
  				<form action="" role="form">
	  				<div class="col-md-2">
	  				</div>
	  				<div class="col-md-2">
	  					<label class="control-label" for="userName">用户名:</label>
	  				</div>
	  				<div class="col-md-2">
	  					<input type="text" class="form-control" id="userName" name="userName">
	  				</div>
	  				<div class="col-md-4">
	  					<input type="button" id="queryBtn" class="btn btn-primary btn-sm" value="查询">
	  					<input type="button" class="btn btn-primary btn-sm"
	  						data-toggle="modal" data-target="#userAddModal" value="添加">
	  				</div>
	  				<div class="col-md-2">
	  				</div>
  				</form>
  			</div>
  		</div>
  	</div>--%>
  	<div class="row">
  		<div class="col-md-2">
			<button class="btn btn-default" data-toggle="modal" data-target="#userAddModal">
				<i class="glyphicon glyphicon-plus"></i>增加</button>
 		</div>
 		<div class="col-md-10"></div>
  	</div>
  	<br/>
  	<div class="row">
  		<div class="col-md-12">
  			<div class="panel panel-default">
  				<div class="panel-heading">
  					<h3 class="panel-title">用户信息列表</h3>
  				</div>
  				<div class="panel-body">
  					<div style="overflow:auto;height: 100%;margin:0px">
  						<table class="table table-striped table-bordered" style="margin:0px" id="mainTable">
  							<thead>
  								<tr class="active">
  									<th style="width:40px;">
							       		<nobr><input type="checkbox" id="checkAll"/></nobr>
							       	</th>
  									<th>
  										<nobr>用户ID</nobr>
  									</th>
  									<th>
  										<nobr>用户名</nobr>
  									</th>
  									<th>
  										<nobr>真实姓名</nobr>
  									</th>
  									<th>
  										<nobr>角色</nobr>
  									</th>
  									<th>
  										<nobr>创建时间</nobr>
  									</th>
  									<th>
  										<nobr>操作</nobr>
  									</th>
  								</tr>
  							</thead>
  							<tbody>
  								<tr></tr>
  							</tbody>
  							<tfoot>
  							</tfoot>
  						</table>
  					</div>
  				</div>
  			</div>
  		</div>
  	</div>
  	<div class="modal fade" id="userAddModal" role="dialog" 
  		aria-labelledby="poupTitle" aria-hidden="true">
  		<div class="modal-dialog">
  			<div class="modal-content">
  				<div class="modal-header">
  					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  					<h4 class="modal-title" id="poupTitle">添加用户</h4>
  				</div>
  				<form action="${ctx }/users/userAdd" method="POST" id="useAddForm" class="form-horizontal" role="form">
	  				<div class="modal-body">
	  					<div class="form-group">
	  						<div class="col-md-3">
	  						</div>
	  						<div class="col-md-2">
	  							<label class="cotrol-label" for="userName">用户名:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<input type="text" class="form-control" name="userName" id="userName">
	  						</div>
	  						<div class="col-md-3"></div>
	  					</div>
	  					<div class="form-group">
	  						<div class="col-md-3">
	  						</div>
	  						<div class="col-md-2">
	  							<label class="cotrol-label" for="realName">真实名:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<input type="text" class="form-control" name="realName" id="realName">
	  						</div>
	  						<div class="col-md-3"></div>
	  					</div>
	  					<div class="form-group">
	  						<div class="col-md-3">
	  						</div>
	  						<div class="col-md-2">
	  							<label class="cotrol-label" for="roleName">角色名:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<select class="form-control selectpicker" id="roleName" name="roleId">
	  								<option value="0">请选择</option>
	  								<c:forEach items="${session_role_list}" var="roleBean">
	  									<option value="${roleBean.roleId}">${roleBean.roleName}</option>
	  								</c:forEach>
	  							</select>
	  						</div>
	  						<div class="col-md-3"></div>
	  					</div>
	  				</div>
	  				
	  				<div class="modal-footer" style="text-align:center;">
	  					<button type="submit" id="saveBtn" class="btn btn-primary">提交</button>	
	  					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>	
	  				</div>
  				</form>
  			</div>
  		</div>
  	</div>
  	
  	<div class="modal fade" id="userUpdateModal" role="dialog" 
  		aria-labelledby="poupTitle" aria-hidden="true">
  		<div class="modal-dialog">
  			<div class="modal-content">
  				<div class="modal-header">
  					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
  					<h4 class="modal-title" id="poupTitle">修改用户</h4>
  				</div>
  				<form action="${ctx }/users/updateUser" method="GET" id="useUpdateForm" class="form-horizontal" role="form">
	  				<div class="modal-body">
	  					<div class="form-group">
	  						<div class="col-md-3">
	  						</div>
	  						<div class="col-md-2">
	  							<label class="cotrol-label" for="userName">用户名:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<input type="hidden" id="userUId" name="userId" value="">
	  							<input type="text" class="form-control" name="userName" id="userUName" value="" readonly="readonly">
	  						</div>
	  						<div class="col-md-3"></div>
	  					</div>
	  					<div class="form-group">
	  						<div class="col-md-3">
	  						</div>
	  						<div class="col-md-2">
	  							<label class="cotrol-label" for="realName">真实名:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<input type="text" class="form-control" name="realName" id="realUName" value="">
	  						</div>
	  						<div class="col-md-3"></div>
	  					</div>
	  					<div class="form-group">
	  						<div class="col-md-3">
	  						</div>
	  						<div class="col-md-2">
	  							<label class="cotrol-label" for="roleName">角色名:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<select class="form-control selectpicker" id="roleUName" name="roleId" data-value="">
	  								<option value="0">请选择</option>
	  								<c:forEach items="${session_role_list}" var="roleBean">
	  									<option value="${roleBean.roleId}">${roleBean.roleName}</option>
	  								</c:forEach>
	  							</select>
	  						</div>
	  						<div class="col-md-3"></div>
	  					</div>
	  				</div>
	  				
	  				<div class="modal-footer" style="text-align:center;">
	  					<button type="submit" id="saveBtn" class="btn btn-primary">提交</button>	
	  					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>	
	  				</div>
  				</form>
  			</div>
  		</div>
  	</div>
  	
  	<script type="text/javascript" src="${ctx}/resources/js/purview/user.js"></script>
  </body>
</html>
