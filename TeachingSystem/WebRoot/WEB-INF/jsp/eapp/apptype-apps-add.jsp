<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>类型游戏</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css" rel="stylesheet" />
		<script src="../resources/js/common.js"></script>
		<script src="../resources/js/eapp/apptype.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./toAddTypeApp" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
					<input type="hidden" name="typeId" id="typeId" value="${typeId}">
					<input type="hidden" id="typeName" value="${typeName}">
				</form>
			</div>
			<div class="row-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<b>未</b>添加到<b>${typeName}</b>的游戏列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="height: 100%;" >
									<!-- 数据-->
									<table class="table" frame="void">
										<tr>
											<td style="text-align: left;">
												<form class="form-inline">
												  <div class="form-group">
												    <label for="appNameForSearch">游戏名称：</label>
												    <input type="text" class="form-control" id="appNameForSearch" placeholder="支持模糊查询"
												    	value="">
												  </div>
												  <button type="button" class="btn btn-default" onclick="searchApp();">
													<i class="glyphicon glyphicon-search"></i>  搜索
												  </button>
												</form>
											</td>
											<td style="text-align: right;">
												<button class="btn btn-default" type="button" onclick="addAppToType();">
													<i class="glyphicon glyphicon-plus"></i> 添加到<b>${typeName}</b>
											  </button>	
											</td>
										</tr>
									</table>
									<div id="needAppsDiv">
									<table class="table table-hover" id="appsList">
										<thead>
											<tr>
												<th>
													<span class="checkbox">
														<label>
															<input type="checkbox" onclick="selectApps(this);" />
															全选
														</label> 
													</span>
												</th>
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
													平台类型
												</th>
												<th>
													应用类型
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageInfo.list}" var="app">
												<tr>
													<td>
														<span class="checkbox"><label>
																<input type="checkbox" name="check_app" />
																<input type="hidden" value="${app.appId}" />
																选择
															</label>
														</span>
													</td>
													<td>
														${app.appName}
													</td>
													<td>
														<img src="${app.appIcon}" width="40" height="40" />
													</td>
													<td>
														<a href="${app.appUrl}" target="_blank">${app.appUrl}</a>
													</td>
													<td>
														<c:choose>
															<c:when test="${app.platformType==2}">8864平台</c:when>
															<c:otherwise>H5平台</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${app.appType==2}">手游</c:when>
															<c:otherwise>H5</c:otherwise>
														</c:choose>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
									<!-- 分页节点 -->
									<div id="pageDiv">
									<table class="table" frame="void">
										<tr>
											<td style="text-align:left;">
										<!--分页-->
										<nav>
										<ul class="pagination">
											<!-- 首页 -->
											<li>
												<a href="javascript:to_page2(1);">首页</a>
											</li>
											<c:choose>
												<c:when test="${pageInfo.pageIndex==1}">
													<li>
														<span href="#">&larr;</span>
													</li>
												</c:when>
												<c:otherwise>
													<li>
														<a href="javascript:to_page2(${pageInfo.pageIndex-1});">&larr;</a>
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
																	<span href="javascript:to_page2(${p});">${p}</span>
																</li>
															</c:when>
															<c:otherwise>
																<li>
																	<a href="javascript:to_page2(${p});">${p}</a>
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
																	<span href="javascript:to_page2(${p});">${p}</span>
																</li>
															</c:when>
															<c:otherwise>
																<li>
																	<a href="javascript:to_page2(${p});">${p}</a>
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
																	<span href="javascript:to_page2(${p});">${p}</span>
																</li>
															</c:when>
															<c:otherwise>
																<li>
																	<a href="javascript:to_page2(${p});">${p}</a>
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
														<a href="javascript:to_page2(${pageInfo.pageIndex+1});">&rarr;</a>
													</li>
												</c:otherwise>
											</c:choose>
											<li>
												<a>${pageInfo.pageIndex}/${pageInfo.pageNum}</a>
											</li>
											<!-- 末页 -->
											<li>
												<a href="javascript:to_page2(${pageInfo.pageNum});">末页</a>
											</li>
											<li>
												<a>共${pageInfo.totalSize}款游戏</a>
											</li>
										</ul>
										</nav>
										</td>
											<td style="text-align:right;">
												<button class="btn btn-primary" onclick="history.back();">返回</button>
											</td>
										</tr>
									</table>
									</div>
									<!-- 分页结束 -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				
			</div>
		</div>
	</body>
</html>