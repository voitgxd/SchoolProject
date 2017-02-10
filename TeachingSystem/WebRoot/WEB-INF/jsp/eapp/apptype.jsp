<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>游戏类型</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css" rel="stylesheet" />
		<script src="../resources/js/common.js"></script>
		<script src="../resources/js/eapp/apptype.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./toIndex" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
					<input type="hidden" id="pageNum" value="${pageInfo.pageNum}"/>
				</form>
			</div>
			<div class="row-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									游戏类型列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="height: 100%;" >
									<!-- 数据-->
									<button class="btn btn-default" type="button" id="appTypeAdd">
										<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
									</button>
									<table class="table table-hover" id="appTypeList">
										<thead>
											<tr>
												<th>
													类型名称
												</th>
												<th>
													类型图标
												</th>
												<th>
													平台类型
												</th>
												<th>
													添加时间
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageInfo.list}" var="appType">
												<tr>
													<td>
														<input type="hidden" value="${appType.typeId}" />
														${appType.typeName}
													</td>
													<td>
														<a href="javascript:toUploadIcon(${appType.typeId});"><img src="${appType.typePic}" width="40" height="40" /></a>
													</td>
													<td>
														<c:choose>
															<c:when test="${appType.platformType==2}">8864平台</c:when>
															<c:otherwise>H5平台</c:otherwise>
														</c:choose>
													</td>
													<td>
														${appType.addTime}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-cog" title="修改" id="update"></i></a>&nbsp;&nbsp;
														<a href="#"><i class="glyphicon glyphicon-trash" title="删除" id="delete"></i></a>&nbsp;&nbsp;
														<a href="#"><i class="glyphicon glyphicon-th-list" title="配置游戏" id="setApp"></i></a>&nbsp;&nbsp;
														<a href="javascript:typeUp(${appType.typeId});"><i class="glyphicon glyphicon-arrow-up"
															title="上升"></i></a>&nbsp;&nbsp;
														<a href="javascript:typeDown(${appType.typeId});"><i class="glyphicon glyphicon-arrow-down"
															title="下降"></i></a>
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
											<a>共${pageInfo.totalSize}种分类</a>
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
							style="padding-top: 200px">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">
									新增游戏类型
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" id="typeForm">
									<input type="hidden" id="flag" name="flag">
									<input type="hidden" id="typeId" name="typeId">
									<div class="form-group">
										<label for="typeName" class="col-sm-2 control-label">
											类型名称：
										</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="typeName" id="typeName"
												placeholder="动作、战略 、 RPG">
										</div>
										<span class="col-sm-4 check-span"></span>
									</div>
									<div class="form-group">
										<label for="file_info" class="col-sm-2 control-label" style="padding-left: 0px;">
									 		类型图标：
									 	</label>
									 	<div class="col-sm-4">
									 		<input type="text" class="form-control" name="file_info" readonly="readonly" placeholder="选择应用图标(.gif/.jpg/.png/.swf)">
									 	</div>
									 	<div class="col-sm-2" style="text-align: center;">
									 		<button type="button" class="btn btn-primary my-btn btn-input">
									 			<i class="fa fa-upload" style="line-height: 2;"></i> 选择图标
									 		</button>
											<input id="typeIcon" type="file" class="upload" name="data" onchange="chooseFile(this,'typeForm');">
									 	</div>
									 	<span class="col-sm-4 check-span check-spe-span2"></span>
									</div>
									<div class="form-group">
										<label for="platformType" class="col-sm-2 control-label">
											平台类型：
										</label>
										<div class="col-sm-6">
											<select class="selectpicker form-control" id="platformType" name="platformType"
											data-live-search="true" onfocus="clean();" value="">
												<option value="1">H5平台</option>
												<option value="2">8864平台</option>
											</select>
										</div>
										<span class="col-sm-4 check-span"></span>
									</div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<button name="submit" type="button"
												class="btn btn-primary my-btn2" onclick="addAppType();">
												提交
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
				<!-- 游戏类型模态框完毕 -->
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
									修改类型图标
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" id="picForm">
									<input type="hidden" id="picTypeId">
									<div class="form-group">
										<label for="file_info" class="col-sm-2 control-label" style="padding-left: 0px;">
									 		类型图标：
									 	</label>
									 	<div class="col-sm-4">
									 		<input type="text" class="form-control" name="file_info" readonly="readonly" placeholder="选择应用图标(.gif/.jpg/.png/.swf)">
									 	</div>
									 	<div class="col-sm-2" style="text-align: center;">
									 		<button type="button" class="btn btn-primary my-btn btn-input">
									 			<i class="fa fa-upload" style="line-height: 2;"></i> 选择图标
									 		</button>
											<input id="picData" type="file" class="upload" name="data" onchange="chooseFile(this,'picForm');">
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
				<!-- 上传游戏类型图标模态框结束 -->
			</div>
		</div>
	</body>
</html>