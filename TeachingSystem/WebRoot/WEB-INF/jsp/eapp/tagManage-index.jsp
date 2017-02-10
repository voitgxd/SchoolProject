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
		<script src="../resources/js/eapp/tagManage.js">
</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<form action="./showTagInfo" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"
						value="${pageInfo.pageIndex}">
				</form>

			</div>
			<div class="row-fluid data">
				<!-- 数据-->
				<div class="row">
					<div class="col-md-12">
						<button type="button" class="btn btn-default"
							style="margin-bottom: 10px" id="addTag">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									标签管理
								</h3>
							</div>
							<div class="panel-body">
								<div style="overflow: auto; height: 100%">
									<table class="table table-hover table-responsive"
										id="tagManage" data-show-columns="true">
										<thead>
											<tr>
												<th width="25%">
													标签名称
												</th>
												<th width="30%">
													平台类型
												</th>
												<th width="35%">
													添加时间
												</th>

												<th width="10%">
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${tagInfo.list}" var="tag">
												<tr>
													<td>
														${tag.tagName}
													</td>
													<td>
														<c:choose>
															<c:when test="${tag.platformType==1}">8864平台</c:when>
															<c:when test="${tag.platformType==2}">h5平台</c:when>
														</c:choose>
													</td>
													<td>
														${tag.addTime}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-cog"
															title="修改" id="update" onclick="updateOne(${tag.tagId})"></i>
														</a>&nbsp;&nbsp;
														<a href="#"><i class="glyphicon glyphicon-trash"
															title="删除" id="delete" onclick="deleteOne(${tag.tagId})"></i>
														</a> &nbsp;&nbsp;
														<a href="#"><i class="glyphicon glyphicon-tags"
															title="给游戏贴标签" id="bookTags"
															onclick="toBookTags(${tag.tagId})"></i> </a>

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
					style="display: none; padding-top: 100px;">
					<div class="modal-dialog" style="width: 800px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="false">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									标签添加
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form">
									<div id="tagInfo">
										<input type="hidden" name="tagId" id="tagId">
										<div class="form-group">
											<label for="tagName" class="col-sm-3 control-label">
												标签名称：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="tagName"
													id="tagName" value="" onfocus="clean();">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="platformType" class="col-sm-3 control-label">
												平台类型：
											</label>
											<div class="col-sm-5">
												<select class="selectpicker show-tick form-control"
													data-style="btn-default" id="platformType"
													name="platformType" value="" onchange="clean();">

													<option value="1">
														8864平台
													</option>
													<option value="2">
														html5平台
													</option>
												</select>
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
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