<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>标签管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css"
			rel="stylesheet" />
		<script src="../resources/js/common.js">
</script>
		<script src="../resources/js/eapp/playerBehavior.js">
</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./toIndex" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
				</form>

			</div>
			<div class="row-fluid data">
				<!-- 数据-->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									玩家游戏行为
								</h3>
							</div>
							<div class="panel-body">
								<div style="overflow: auto; height: 100%">
									<table class="table table-hover table-responsive"
										id="playerBehavior" data-show-columns="true">
										<thead>
											<tr>

												<th>
													玩家id
												</th>
												<th>
													总评论
												</th>
												<th>
													总积分
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageInfo.list}" var="player">
												<tr>
													<td>
														${player.passportId}
													</td>
													<td>
														<c:if test="${player.countNum!=null}">${player.countNum}条</c:if>
														<c:if test="${player.countNum==null}">0条</c:if>

													</td>
													<td>
														<c:if test="${player.score!=null}">
													${player.score}分
													</c:if>
														<c:if test="${player.score==null}">
													0分
													</c:if>

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
									</ul>
									</nav>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>