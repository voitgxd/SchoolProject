<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>没有贴标签的游戏</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css"
			rel="stylesheet" />
		<script src="../resources/js/common.js">
</script>
		<script src="../resources/js/eapp/tagManage.js">
</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./showAddApp" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
					<input type="hidden" name="tagId" id="tagId" value="${tagId}">
				</form>
			</div>
			<div class="row-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									没有贴<b>${pageInfo.tagName}</b>标签的游戏
								</h3>
							</div>
							<div class="panel-body">
								<div style="height: 100%;">
									<!-- 数据-->
									<table class="table" frame="void">
										<tr>
											<td style="text-align: left;">
												<button class="btn btn-default" type="button"
													onclick="addAppToTag();">
													<i class="glyphicon glyphicon-plus"></i>
													为以下游戏贴<b>${pageInfo.tagName}</b>标签
												</button>
											</td>
										</tr>
									</table>
									<div id="needAppsDiv">
										<table class="table table-hover" id="appsList">
											<thead>
												<tr>
													<th width="10%">
														<input type="checkbox" id="all" class="chooseAll" />
														全选
													</th>
													<th width="10%">
														游戏图片
													</th>
													<th width="10%">
														游戏名称
													</th>
													<th width="60%">
														游戏地址
													</th>
													<th width="10%">
														游戏状态
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
																	请选择
																</label> </span>
														</td>
														<td>
															<img alt="${app.appIcon}" src="${app.appIcon}"
																width="40px" height="40px">
														</td>
														<td>
															${app.appName}
														</td>
														<td>
															<a href="${app.appUrl}" target="_blank">${app.appUrl}</a>

														</td>
														<td>
															<c:if test="${app.appState==1}">
													待审核
													</c:if>
															<c:if test="${app.appState==2}">
													通过
													</c:if>
															<c:if test="${app.appState==3}">
													未通过
													</c:if>
															<c:if test="${app.appState==4}">
													无效
													</c:if>

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
												<td style="text-align: left;">
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
															<a href="#">共${pageInfo.totalSize}款游戏</a>
														</li>
													</ul>
													</nav>
												</td>
												<td style="text-align: right;">
													<button class="btn btn-primary" onclick="history.back();">
														返回
													</button>
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