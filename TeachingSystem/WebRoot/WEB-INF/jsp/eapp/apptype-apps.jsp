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
				<form action="./toSetApp" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
					<input type="hidden" name="typeId" id="typeId" value="${typeId}">
					<input type="hidden" name="typeName" id="typeName" value="${typeName}"/>
					<input type="hidden" id="pageNum" value="${pageInfo.pageNum}" />
				</form>
			</div>
			<div class="row-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<b>已</b>添加到<b>${typeName}</b>的游戏列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="height: 100%;" >
									<!-- 数据-->
									<table class="table" frame="void">
										<tr>
											<td style="text-align: left;">
												<button class="btn btn-default" type="button" onclick="showAddApp(${typeId},'${typeName}');">
													<i class="glyphicon glyphicon-plus"></i> 添加游戏
												</button>
											</td>
										</tr>
									</table>
									<table class="table table-hover" id="appsList">
										<thead>
											<tr>
												<th>置顶状态</th>
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
													开发者信息
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
														<input type="hidden" value="${app.appId}"/>
														<c:choose>
															<c:when test="${app.stickState==1}">
																<a href="javascript:cancelTopState(${typeId},${app.appId});"><i class="glyphicon glyphicon-heart" title="置顶"></i></a>
															</c:when>
															<c:otherwise>
																<a href="javascript:toChooseExpireTime(${app.appId});"><i class="glyphicon glyphicon-heart-empty" title="未置顶"></i></a>
															</c:otherwise>
														</c:choose>
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
													<td>
														${app.developerId}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-trash" title="删除" id="remove"></i></a>
														<a href="#"><i class="glyphicon glyphicon-arrow-up"
															title="上升" id="up"></i> </a>
														<a href="#"><i class="glyphicon glyphicon-arrow-down"
															title="下降" id="down"></i> </a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<table class="table" frame="void">
										<tr>
											<td style="text-align:left;">
											
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
									</td>
											<td style="text-align:right;">
												<button class="btn btn-primary" onclick="history.back();">返回</button>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<!-- 置顶模态框 -->
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
								<h4 class="modal-title">
									选择置顶的过期时间
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form" id="timeForm">
									<input type="hidden" id="appId" />
									<div class="form-group">
										<label for="expireTime" class="col-sm-2 control-label">
											过期时间：
										</label>
										<div class="col-sm-6">
											<div class="input-group date form_datetime"
												data-date="2015-05-01 05:25"
												data-date-format="yyyy-mm-dd hh:ii"
												data-link-field="dtp_input1">
												<input class="form-control" size="16" type="text"
													name="expireTime" id="expireTime" value="" readonly
													placeholder="请选择过期时间" onfocus="clean();">
												<span class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span> </span>
											</div>
										</div>
										<span class="col-sm-4 check-span"></span>
									</div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<button name="submit" type="button"
												class="btn btn-primary my-btn2" onclick="setAppToTop();">
												确定
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