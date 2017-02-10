<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>推荐游戏</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css" rel="stylesheet" />
		<script src="../resources/js/common.js"></script>
		<script src="../resources/js/eapp/recommend.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./toIndex" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
					<input type="hidden" name="recommendType" id="recommendType"
						value="${recommendType}"/>
					<input type="hidden" id="pageNum" value="${pageInfo.pageNum}"/>
				</form>
			</div>
			<div class="row-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									游戏推荐列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="height: 100%;" >
									<form class="form-inline" role="form">
										<div class="form-group">
											<label for="recommendType" class="col-sm-4 control-label" style="margin-top: 10px;">
												推荐类型：
											</label>
											<div class="col-sm-7">
												<select class="selectpicker" id="recommendTypeSearch"
													data-live-search="true" value="${recommendType}">
													<option value="1">精品推荐</option>
													<option value="2">新品推荐</option>
													<option value="3">热门推荐</option>
												</select>
											</div>
										</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn btn-default" type="button"
											onclick="toRecommendNeedApp(${recommendType});">
											<i class="glyphicon glyphicon-plus"></i> 推荐游戏</b>
										</button>
									</form><br/>
									<table class="table table-hover" id="recommendList">
										<thead>
											<tr>
												<th>
													应用名称
												</th>
												<th>
													应用图标
												</th>
												<th>
													推荐原因
												</th>
												<th>
													生效时间
												</th>
												<th>
													过期时间
												</th>
												<th>
													应用状态
												</th>
												<th>
													推荐人
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageInfo.list}" var="recommend">
												<tr>
													<td>
														<input type="hidden" value="${recommend.appId}"/>
														${recommend.appName}
													</td>
													<td>
														<img src="${recommend.appIcon}" width="40" height="40" />
													</td>
													<td>
														${recommend.recommendReason}
													</td>
													<td>
														${recommend.effectTime}
													</td>
													<td>
														${recommend.expireTime}
													</td>
													<td>
														<c:choose>
															<c:when test="${recommend.timeFlag==1}">
																已生效
															</c:when>
															<c:otherwise>
																未生效
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														${recommend.userName}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-remove-circle" title="取消推荐" id="cancelRecommend"></i></a>
														<a href="#"><i class="glyphicon glyphicon-arrow-up"
															title="上升" id="up"></i></a>
														<a href="#"><i class="glyphicon glyphicon-arrow-down"
															title="下降" id="down"></i></a>
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
											<a>共${pageInfo.totalSize}个游戏</a>
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