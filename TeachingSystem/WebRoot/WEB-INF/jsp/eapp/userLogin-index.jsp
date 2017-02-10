<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>玩家活跃度</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css"
			rel="stylesheet" />
		<script src="../resources/js/common.js">
</script>
		<script src="../resources/js/eapp/userLogin.js">
</script>
	</head>
	<body>

		<div class="container-fluid">
			<div class="row-fluid data">
				<!-- 数据-->
				<div class="row" style="margin-bottom: 10px;">
					<form action="./toIndex" method="post"
						class="form-inline col-md-12">
						<input type="hidden" name="pageIndex" id="pageIndex"
							value="${pageInfo.pageIndex}">

						<div class="col-sm-3">
							<div class="input-group date form_date"
								data-date-format="yyyy-mm-dd" data-link-field="dtp_input2"
								data-link-format="yyyy-mm-dd">
								<input class="form-control" size="20" type="text"
									value="${pageInfo.beginTime}" readonly name="beginTime"
									id="beginTime" placeholder="请选择开始时间：">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span> </span>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="input-group date form_date"
								data-date-format="yyyy-mm-dd" data-link-field="dtp_input2"
								data-link-format="yyyy-mm-dd">
								<input class="form-control" size="20" type="text"
									value="${pageInfo.endTime}" readonly name="endTime"
									id="endTime" placeholder="请选择结束时间：">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span> </span>
							</div>
						</div>

						<div class="col-sm-3">
							<Button class="btn btn-default form-control" type="button"
								onclick="searchUserLogin();">
								<span class="glyphicon glyphicon-search"></span> 查询
							</Button>
							<Button class="btn btn-default form-control" type="button"
								onclick="cleanForm();">
								重置
							</Button>
						</div>
						
						<div class="col-sm-3">
							<label for="registNumber" class="col-sm-5 control-label"
								style="margin-top: 7px;">
								注册人数：
							</label>
							<div class="col-sm-2">
								<input type="text" class="form-control"
									id="registNumber" value="${registNumber}" disabled>
							</div>
						</div>
					</form>
				</div>
				<div class="row">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								玩家活跃度
							</h3>
						</div>
						<div class="panel-body">
							<div style="overflow: auto; height: 100%">
								<table class="table table-hover table-responsive"
									id="playerBehavior" data-show-columns="true" id="tb">
									<thead>
										<tr>

											<th>
												活跃日期
											</th>
											<th>
												登录人数(人)
											</th>
											<th>
												登录次数(次)
											</th>


										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pageInfo.list}" var="user">
											<tr>
												<td>
													${user.loadTime}
												</td>
												<td>
													${user.passportCount}
												</td>
												<td>
													${user.loginCount}
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
											<c:forEach begin="${pageInfo.pageNum<6?1:pageInfo.pageNum-5}"
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