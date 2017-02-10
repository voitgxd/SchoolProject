<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>游戏操作</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css" rel="stylesheet" />
		<script src="../resources/js/common.js"></script>
		<script src="../resources/js/eapp/apps.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./displayFunc" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
					<input type="hidden" name="appStateFlag" id="appStateFlag"
						value="${appStateFlag}"/>
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
									<form class="form-inline" role="form">
										<div class="form-group">
											<label for="appState" class="col-sm-4 control-label" style="margin-top: 10px;">
												应用状态：
											</label>
											<div class="col-sm-7">
												<select class="selectpicker" id="appState"
													data-live-search="true" value="${appStateFlag}">
													<option value="1">待审核</option>
													<option value="2">通过</option>
													<option value="3">未通过</option>
												</select>
											</div>
										</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn btn-default" type="button" onclick="toCheckChoosedApps(true);">
											<i class="glyphicon glyphicon-hourglass"></i> 批量通过
										</button>&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn btn-default" type="button" onclick="toCheckChoosedApps(false);">
											<i class="glyphicon glyphicon-hourglass"></i> 批量未通过
										</button>
									</form>
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
												<th>
													应用描述
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
														<span class="checkbox"><label>
																<input type="checkbox" name="check_app" />
																<input type="hidden" value="${app.appId}" />
																选择
															</label>
														</span>
													</td>
													<td>
														<input type="hidden" value="${app.appId}" />
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
													<td>
														${app.appDesc}
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
														<a href="#"><i class="glyphicon glyphicon-ban-circle" title="下架" id="delete"></i></a>
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
			</div>
		</div>
	</body>
</html>