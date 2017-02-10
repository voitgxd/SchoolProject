<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>游戏管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css" rel="stylesheet" />
		<script src="../resources/js/common.js"></script>
		<script src="../resources/js/eapp/apps.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./toIndex" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
					<input type="hidden" name="queryGameName" id="queryGameName"
						value="">
				</form>
			</div>
			<div class="row-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									游戏列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="height: 100%;" >
									<!-- 数据-->
									<table class="table" frame="void">
										<tr>
											<td style="text-align: left;width:400px;">
												<form class="form-inline">
												  	<div class="form-group">
												    	<label for="appNameForSearch">游戏名称：</label>
												    	<input type="text" class="form-control" id="appNameForSearch" placeholder="支持模糊查询">
												  	</div>
													<button type="button" class="btn btn-default" onclick="searchApp();">
														<i class="glyphicon glyphicon-search"></i>  搜索
												  	</button>
											  	</form>
											</td>
											<td style="text-align: center;">
												<button class="btn btn-default" type="button" id="app_add">
													<i class="glyphicon glyphicon-plus"></i> 新增
												</button>
											</td>
											<td style="text-align: center;">
												<button type="button" class="btn btn-default" data-toggle="modal"
													data-target="#myModal4">
													<i class="glyphicon glyphicon-import"></i> 批量导入
												</button>
											</td>
											<td style="text-align: center;">
												<button type="button" class="btn btn-default" onclick="downloadExcell();">
													<i class="glyphicon glyphicon-download-alt"></i> 下载模板
												</button>
											</td>
										</tr>
									</table>
									<table class="table table-hover" id="appsList">
										<thead>
											<tr>
												<th>
													应用名称
												</th>
												<th>
													应用图标
												</th>
												<th>
													应用地址
												</th>
												<th>
													应用描述
												</th>
												<th>
													提交时间
												</th>
												<th>
													审核时间
												</th>
												<th>
													应用状态
												</th>
												<th>
													开发者
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageInfo.list}" var="app">
												<tr>
													<td>
														<input type="hidden" value="${app.appId}" />
														${app.appName}
													</td>
													<td>
														<a href="javascript:toUploadIcon(${app.appId});"><img src="${app.appIcon}" width="40" height="40" /></a>
													</td>
													<td>
														<a href="${app.appUrl}" target="_blank">${app.appUrl}</a>
													</td>
													<td>
														${app.appDesc}
													</td>
													<td>
														${app.submitTime}
													</td>
													<td>
														${app.addTime}
													</td>
													<td>
														<c:choose>
															<c:when test="${app.appState==1}">待审核</c:when>
															<c:when test="${app.appState==2}">通过</c:when>
															<c:when test="${app.appState==3}">未通过</c:when>
															<c:when test="${app.appState==4}">无效</c:when>
															<c:otherwise></c:otherwise>
														</c:choose>
													</td>
													<td>
														${app.developerId}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-cog" title="修改" id="update"></i></a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<!--分页-->
									<nav>
									<ul class="pagination">
										<!-- 首页 -->
										<li>
											<a href="javascript:to_page(1);">首页</a>
										</li>
										<c:choose>
											<c:when test="${pageInfo.pageIndex==1}">
												<li>
													<span href="#">&larr;</span>
												</li>
											</c:when>
											<c:otherwise>
												<li>
													<a href="javascript:to_page(${pageInfo.pageIndex-1});">&larr;</a>
												</li>
											</c:otherwise>
										</c:choose>


										<c:choose>
											<c:when test="${pageInfo.pageIndex<3}">
												<c:forEach begin="1"
													end="${pageInfo.pageNum>5?5:pageInfo.pageNum}" var="p">
													<c:choose>
														<c:when test="${pageInfo.pageIndex==p}">
															<li class="active">
																<span href="javascript:to_page(${p});">${p}</span>
															</li>
														</c:when>
														<c:otherwise>
															<li>
																<a href="javascript:to_page(${p});">${p}</a>
															</li>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:when test="${pageInfo.pageIndex<pageInfo.pageNum-2}">
												<c:forEach begin="${pageInfo.pageIndex-2}"
													end="${pageInfo.pageIndex+2}" var="p">
													<c:choose>
														<c:when test="${pageInfo.pageIndex==p}">
															<li class="active">
																<span href="javascript:to_page(${p});">${p}</span>
															</li>
														</c:when>
														<c:otherwise>
															<li>
																<a href="javascript:to_page(${p});">${p}</a>
															</li>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach
													begin="${pageInfo.pageNum<6?1:pageInfo.pageNum-5}"
													end="${pageInfo.pageNum}" var="p">
													<c:choose>
														<c:when test="${pageInfo.pageIndex==p}">
															<li class="active">
																<span href="javascript:to_page(${p});">${p}</span>
															</li>
														</c:when>
														<c:otherwise>
															<li>
																<a href="javascript:to_page(${p});">${p}</a>
															</li>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:otherwise>
										</c:choose>

										<c:choose>
											<c:when test="${pageInfo.pageIndex==pageInfo.pageNum}">
												<li>
													<span href="#">&rarr;</span>
												</li>
											</c:when>
											<c:otherwise>
												<li class="next">
													<a href="javascript:to_page(${pageInfo.pageIndex+1});">&rarr;</a>
												</li>
											</c:otherwise>
										</c:choose>
										<li>
											<a>${pageInfo.pageIndex}/${pageInfo.pageNum}</a>
										</li>
										<!-- 末页 -->
										<li>
											<a href="javascript:to_page(${pageInfo.pageNum});">末页</a>
										</li>
										<li>
											<a>共${pageInfo.totalSize}款游戏</a>
										</li>
									</ul>
									</nav>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<!-- 模态框-->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					style="display: none;">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="false">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel"></h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" id="formApp">
									<input type="hidden" name="flag" id="flag">
									<input type="hidden" name="appId" id="appId">
									<div class="form-group">
										<label for="appName" class="col-sm-2 control-label">
											应用名称：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="appName"
												id="appName" value="" onfocus="clean();">
										</div>
										<span class="col-sm-2 check-span"></span>
									</div>
									
									<div class="form-group">
										<label for="file_info" class="col-sm-2 control-label" style="padding-left: 0px;">
									 		应用图标：
									 	</label>
									 	<div class="col-sm-5">
									 		<input type="text" class="form-control" name="file_info" readonly="readonly" placeholder="选择应用图标(.gif/.jpg/.png/.swf)">
									 	</div>
									 	<div class="col-sm-2" style="text-align: center;">
									 		<button type="button" class="btn btn-primary my-btn btn-input">
									 			<i class="fa fa-upload" style="line-height: 2;"></i> 选择图标
									 		</button>
											<input id="appIconId" type="file" class="upload" name="data" onchange="chooseFile(this,'formApp');">
									 	</div>
									 	<span class="col-sm-2 check-span check-spe-span2"></span>
									</div>
									<div class="form-group">
										<label for="appUrl" class="col-sm-2 control-label">
											应用地址：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="appUrl"
												id="appUrl" value="" onfocus="clean();">
										</div>
										<span class="col-sm-2 check-span"></span>
									</div>
									<div class="form-group">
										<label for="platformType" class="col-sm-2 control-label">
											平台类型：
										</label>
										<div class="col-sm-7">
											<select class="selectpicker form-control" id="platformType" name="platformType"
											data-live-search="true" onfocus="clean();" value="">
												<option value="1">H5平台</option>
												<option value="2">8864平台</option>
											</select>
										</div>
										<span class="col-sm-2 check-span"></span>
									</div>
									<div class="form-group">
										<label for="appType" class="col-sm-2 control-label">
											应用类型：
										</label>
										<div class="col-sm-7">
											<select class="selectpicker form-control" id="appType" name="appType"
											data-live-search="true" onfocus="clean();" value="">
												<option value="1">H5</option>
												<option value="2">手游</option>
											</select>
										</div>
										<span class="col-sm-2 check-span"></span>
									</div>
									<div class="form-group">
										<label for="appSummary" class="col-sm-2 control-label">
											应用简介：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="appSummary"
												id="appSummary" value="" onfocus="clean();">
										</div>
										<span class="col-sm-2 check-span"></span>
									</div>
									<div class="form-group">
										<label for="appDesc" class="col-sm-2 control-label">
											应用描述：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="appDesc"
												id="appDesc" value="" onfocus="clean();">
										</div>
										<span class="col-sm-2 check-span"></span>
									</div>
									<div class="form-group">
										<label for="developerId" class="col-sm-2 control-label">
											开发者：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="developerId"
												id="developerId" value="" onfocus="clean();">
										</div>
										<span class="col-sm-2 check-span"></span>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									关闭
								</button>
								<button type="button" class="btn btn-primary" id="save">
									保存
								</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				
				<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel2" aria-hidden="true"
							style="padding-top: 200px">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">
									上传游戏
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" id="appForm">
									<div class="form-group">
										<label for="address" class="col-sm-2 control-label">
											上传地址：
										</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="address" id="address"
												placeholder="应用上传地址，例如:gedou/h5">
										</div>
										<span class="col-sm-4 check-span"></span>
									</div>
									<div id="changeFormDiv"></div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<button name="submit" type="button"
												class="btn btn-primary my-btn2" onclick="uploadApp();">
												上传
											</button>
										</div>
										<div class="col-sm-4">
											<button name="cancle" type="button"
												class="btn btn-primary my-btn2" data-dismiss="modal">
												取消
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
							</div>
						</div>
					</div>
				</div>
				<!-- 上传游戏模态框完毕 -->
				<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel3" aria-hidden="true"
							style="padding-top: 200px">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">
									修改应用图标
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" id="iconForm">
									<input type="hidden" id="iconAppId">
									<div class="form-group">
										<label for="file_info" class="col-sm-2 control-label" style="padding-left: 0px;">
									 		应用图标：
									 	</label>
									 	<div class="col-sm-4">
									 		<input type="text" class="form-control" name="file_info" readonly="readonly" placeholder="选择应用图标(.gif/.jpg/.png/.swf)">
									 	</div>
									 	<div class="col-sm-2" style="text-align: center;">
									 		<button type="button" class="btn btn-primary my-btn btn-input">
									 			<i class="fa fa-upload" style="line-height: 2;"></i> 选择图标
									 		</button>
											<input id="appIcon" type="file" class="upload" name="data" onchange="chooseFile(this,'iconForm');">
									 	</div>
									 	<span class="col-sm-4 check-span check-spe-span2"></span>
									</div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<button name="submit" type="button"
												class="btn btn-primary my-btn2" onclick="uploadIcon();">
												上传
											</button>
										</div>
										<div class="col-sm-4">
											<button name="cancle" type="button"
												class="btn btn-primary my-btn2" data-dismiss="modal">
												取消
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
							</div>
						</div>
					</div>
				</div>
				<!-- 上传游戏图标模态框结束 -->
				<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel4" aria-hidden="true"
							style="padding-top: 200px">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">
									导入批量添加游戏Excell文件
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" id="ExcellForm">
									<div class="form-group">
										<label for="file_info" class="col-sm-2 control-label" style="padding-left: 0px;">
									 		Excell文件：
									 	</label>
									 	<div class="col-sm-4">
									 		<input type="text" class="form-control" name="file_info" readonly="readonly" placeholder="选择文件(.xls)">
									 	</div>
									 	<div class="col-sm-2" style="text-align: center;">
									 		<button type="button" class="btn btn-primary my-btn btn-input">
									 			<i class="fa fa-upload" style="line-height: 2;"></i> 选择文件
									 		</button>
											<input id="appExcell" type="file" class="upload" name="data" onchange="chooseFile(this,'excellForm');">
									 	</div>
									 	<span class="col-sm-4 check-span check-spe-span2"></span>
									</div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<button name="submit" type="button"
												class="btn btn-primary my-btn2" onclick="uploadExcell();">
												上传
											</button>
										</div>
										<div class="col-sm-4">
											<button name="cancle" type="button"
												class="btn btn-primary my-btn2" data-dismiss="modal">
												取消
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
							</div>
						</div>
					</div>
				</div>
				<!-- 上传导入游戏模板完毕 -->
			</div>
		</div>
	</body>
</html>