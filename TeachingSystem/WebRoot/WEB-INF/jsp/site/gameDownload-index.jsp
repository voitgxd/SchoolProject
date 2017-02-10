<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>网站点击下载统计</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp" %>
		<link type="text/css" href="../resources/css/common.css" rel="stylesheet" />
		<script src="../resources/js/common.js"></script>
		<script src="../resources/js/site/download.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./toIndex" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
					<input type="hidden" name="queryGameType" id="queryGameType" value="${pageInfo.queryGameType}">
				</form>
				<div class="col-md-12">
					<span class="col-sm-12">设备类型：
						<select class="selectpicker" id="gameTypeQuery" name="gameTypeQuery" data-live-search="true"
							 value="${pageInfo.queryGameType}">
							<option value="-1">全部</option>
							<option value="1">Android</option>
							<option value="2">IOS</option>
							<option value="3">越狱</option>
							<option value="4">WP</option>
							<option value="5">GooglePlay</option>
							<option value="0">未知</option>
						</select>
					</span>
				</div>
			</div>
			<div class="row-fluid data">
				<!-- 数据-->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									网站点击下载统计列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="overflow: auto; height: 100%">
									<table class="table table-hover table-responsive" id="downloadRecords" data-show-columns="true">
										<thead>
											<tr>
												<th>
													记录ID
												</th>
												<th>
													查询类型
												</th>
												<th>
													设备类型
												</th>
												<th>
													下载时间
												</th>
												<th>
													下载地址
												</th>
												<th>
													下载资源
												</th>
												<th>
													下载IP
												</th>
												<th>
													代理用户
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageInfo.list}" var="record">
												<tr>
													<td>
														${record.logId}
													</td>
													<td>
														${record.queryType}
													</td>
													<td>
														<c:choose>
															<c:when test="${record.gameType==1}">Android</c:when>
															<c:when test="${record.gameType==2}">IOS</c:when>
															<c:when test="${record.gameType==3}">越狱</c:when>
															<c:when test="${record.gameType==4}">WP</c:when>
															<c:when test="${record.gameType==5}">GooglePlay</c:when>
															<c:otherwise>未知</c:otherwise>
														</c:choose>
													</td>
													<td>
														${record.downloadTime}
													</td>
													<td>
														${record.downloadUrl}
													</td>
													<td>
														${record.downloadSource}
													</td>
													<td>
														${record.downloadIp}
													</td>
													<td>
														${record.userAgent}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-cog" title="修改" id="update"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="#"><i class="glyphicon glyphicon-trash" title="删除" id="delete"></i></a>
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
								<form class="form-horizontal" role="form">
									<input type="hidden" name="logId" id="logId">
									<div class="form-group">
										<label for="queryType" class="col-sm-2 control-label">
											查询类型：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="queryType"
												id="queryType" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>

									<div class="form-group">
										<label for="gameType" class="col-sm-2 control-label">
											设备类型：
										</label>
										<div class="col-sm-7">
											<select class="selectpicker form-control" id="gameType" name="gameType"
											data-live-search="true" onfocus="clean();" value="">
												<option value="1">Android</option>
												<option value="2">IOS</option>
												<option value="3">越狱</option>
												<option value="4">WP</option>
												<option value="5">GooglePlay</option>
												<option value="0">未知</option>
											</select>
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="downloadTime" class="col-sm-2 control-label">
											下载时间：
										</label>
										<div class="col-sm-7">
											<div class="input-group date form_datetime"
												data-date="2015-01-15 05:25"
												data-date-format="yyyy-mm-dd hh:ii"
												data-link-field="dtp_input1">
												<input class="form-control" size="16" type="text" name="downloadTime" id="downloadTime" value=""
													readonly>
												<span class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span> </span>
											</div>
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="downloadUrl" class="col-sm-2 control-label">
											下载地址：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="downloadUrl"
												id="downloadUrl" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="downloadSource" class="col-sm-2 control-label">
											下载资源：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="downloadSource"
												id="downloadSource" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="downloadIp" class="col-sm-2 control-label">
											下载IP：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="downloadIp"
												id="downloadIp" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="userAgent" class="col-sm-2 control-label">
											代理用户：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="userAgent"
												id="userAgent" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
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
			</div>
		</div>
	</body>
</html>