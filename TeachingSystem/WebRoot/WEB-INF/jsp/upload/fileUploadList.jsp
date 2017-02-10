<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" 
	isELIgnored="false" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html";charset="utf-8" >    
    <title>文件上传</title>
    <meta name="viewreport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
    
</head>
<body>
<form action="" >
	<input type="hidden" id="sessionId" value="${pageContext.session.id}" /> 
	<table style="width: 90%;">
		<tr>
			<td style="width: 100px;">
				<input type="file" name="uploadify" id="uploadify" />
			</td>
			<td align="left">
			<a href="javascript:$('#uploadify').uploadify('upload', '*')">开始上传</a>|
			<a href="javascript:$('#uploadify').uploadify('cancel')">取消上传</a>
			<%--<span id="result" style="font-size: 13px;color: red"></span>--%>
			</td>
		</tr>
	</table>
	<div id="fileQueue" ></div>
	<%--<div id="fileQueue" style="width: 400px;height: 300px; border: 2px solid green;"></div>--%>
	</form>
	<br/>
  	<div class="row">
  		<div class="col-md-12">
  			<div class="panel panel-default">
  				<div class="panel-heading">
  					<div class="row">
  						<div class="col-md-3">
  							<h3 class="panel-title">文件信息列表</h3>
  						</div>
  						<div class="col-md-8"></div>
  						<div class="col-md-1">
							<a class="btn btn-default btn-xs" id="btnExport" href="#" title="导出">
 							<i class="fa fa-arrow-down fa-lg"></i></a>
					</div>
  					</div>
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
  										<nobr>图标</nobr>
  									</th>
  									<th style="width:560px;">
  										<nobr>文件地址</nobr>
  									</th>
  									<%--<th>
  										<nobr>创建时间</nobr>
  									</th>--%>
  									<%--<th>
  										<nobr>操作</nobr>
  									</th>--%>
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
	
	
	
	<script type="text/javascript" src="${ctx}/resources/js/upload/fileUploadList.js"></script>
</body>
</html>