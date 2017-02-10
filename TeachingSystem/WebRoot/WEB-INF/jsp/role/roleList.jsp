<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" 
	isELIgnored="false" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>	

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html";charset="utf-8" >    
    <title>角色管理</title>
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
	  					<label class="control-label" for="roleName">角色名:</label>
	  				</div>
	  				<div class="col-md-2">
	  					<select class="form-control selectpicker" id="userName">
	  						<option value="0">请选择</option>
	  					</select>
	  					<input type="text" class="form-control" id="roleName" name="roleName">
	  					
	  				</div>
	  				<div class="col-md-4">
	  					<input type="button" id="queryBtn" class="btn btn-primary btn-sm" value="查询">
	  					<input type="button" class="btn btn-primary btn-sm" value="添加">
	  					<a class="btn btn-primary btn-sm" href="${ctx}/roles/prepareAdd" >添加</a>
	  				</div>
	  				<div class="col-md-2">
	  				</div>
  				</form>
  			</div>
  		</div>
  	</div>
  	--%>
  	<div class="row">
  		<div class="col-md-2">
			<a class="btn btn-default" href="${ctx}/roles/prepareAdd" >
				<i class="glyphicon glyphicon-plus"></i>增加</a>
 		</div>
 		<div class="col-md-10"></div>
  	</div>
  	<br/>
  	<div class="row">
  		<div class="col-md-12">
  			<div class="panel panel-default">
  				<div class="panel-heading">
  					<h3 class="panel-title">角色信息列表</h3>
  				</div>
  				<div class="panel-body">
  					<div style="overflow:auto;height: 100%">
  						<table class="table table-bordered table-hover table-condensed" id="mainTable">
  							<thead>
  								<tr class="active">
  									<th style="width:40px;">
							       		<nobr><input type="checkbox" id="checkAll"/></nobr>
							       	</th>
  									<th>
  										<nobr>角色ID</nobr>
  									</th>
  									<th>
  										<nobr>角色名</nobr>
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
  							</tbody>
  						</table>
  					</div>
  				</div>
  			</div>
  		</div>
  	</div>
  	<script type="text/javascript" src="${ctx}/resources/js/purview/role.js"></script>
  </body>
</html>
