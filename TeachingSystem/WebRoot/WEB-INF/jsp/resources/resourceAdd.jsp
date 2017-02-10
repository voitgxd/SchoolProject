<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" 
	isELIgnored="false" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>	

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html";charset="utf-8" >    
    <title>资源列表</title>
    <meta name="viewreport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
  </head>
  
  <body>	
  				<div class="modal-header">
  					<h4 class="modal-title" id="poupTitle">添加资源</h4>
  				</div>
  				<form action="${ctx}/resources/addResource" method="GET" id="resourceAddForm" class="form-horizontal">

	  				<div class="modal-body">     
	  					<div class="form-group">
	  					    <div class="col-md-3"></div>
	  						<div class="col-md-1">
	  							<label class="cotrol-label" for="resName">资源名称:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<input type="text" class="form-control" name="resName" id="resName">
	  						</div>
	  						<div class="col-md-3" id="nameMsg"><span style='color:gray'>必填</span></div>
	  					</div>
	  					<div class="form-group">
	  					    <div class="col-md-3"></div>
	  						<div class="col-md-1">
	  							<label class="cotrol-label" for="resPid">父模块:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<select class="form-control selectpicker" id="resPid" name="resPid" >
	  								<option value="0">请选择</option>
	  								<c:forEach items="${resultList}" var="resourceBean">
	  									<option value="${resourceBean.resId}" >${resourceBean.resName}
	  									</option>
	  								</c:forEach>
	  							</select>
	  						</div>
	  						<div class="col-md-3"><span style='color:gray'>不选择时默认保存在根目录下</span></div>
	  					</div>
	  					<div class="form-group">
	  					     <div class="col-md-3"></div>
	  						<div class="col-md-1">
	  							<label class="cotrol-label" for="resUrl">URL地址:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<input type="text" class="form-control" name="resUrl" id="resUrl">
	  						</div>
	  					</div>
	  					<div class="form-group">
	  					    <div class="col-md-3"></div>
	  						<div class="col-md-1">
	  							<label class="cotrol-label" for="resIcon">资源图标:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<input type="text" class="form-control" name="resIcon" id="resIcon">
	  						</div>
	  					</div>
	  					<div class="form-group">
	  					    <div class="col-md-3"></div>
	  						<div class="col-md-1">
	  							<label class="cotrol-label" for="resOrder">资源排序:</label>
	  						</div>
	  						<div class="col-md-4">
	  							<input type="text" class="form-control" name="resOrder" id="resOrder">
	  						</div>
	  						<div class="col-md-3"><span style='color:gray'>不填写默认为-1</span></div>
	  					</div>						
	  				</div>
	  				<div class="modal-footer" style="text-align:center;">
	  					<button type="submit" id="saveBtn" class="btn btn-primary">提交</button>	
	  					<button type="button" class="btn btn-default" onclick="javascript:history.back()">返回</button>	
	  				</div>
  				</form>
	<script type="text/javascript" src="${ctx}/resources/js/purview/resources.js"></script>
  </body>
</html>
