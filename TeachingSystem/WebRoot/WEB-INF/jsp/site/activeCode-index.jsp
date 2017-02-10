<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>激活码信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css"
			rel="stylesheet" />
		<script src="../resources/js/common.js">
</script>
		<script src="../resources/js/site/activeCode.js">
</script>

	</head>
	<body>
		<div class="container-fluid">
			<form action="./showActiveCode" method="post">
				<input type="hidden" name="pageIndex" id="pageIndex"
					value="${pageInfo.pageIndex}" />
			</form>
			<div class="row-fluid data">
				<div class="row">
					<div class="col-md-12">
						<button type="button" class="btn btn-default" data-toggle="modal"
							data-target="#myModal" style="margin-bottom: 10px">
							<i class="glyphicon glyphicon-import"></i> Loading File
						</button>
						<button type="button" class="btn btn-default" id="download"
							style="margin-bottom: 10px">
							<i class="glyphicon glyphicon-download-alt"></i> Download
						</button>

						<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true"
							style="padding-top: 200px">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title">
											导入激活码
										</h4>
									</div>
									<div class="modal-body">
										<form action="./insertActiveCode" method="post"
											enctype="multipart/form-data" class="form-inline"
											id="activeForm">
											<div class="form-group">
												<label for="file_info" class="col-sm-3 control-label"
													style="padding-top: 10px;">
													导入激活码：
												</label>
												<div class="col-sm-5">
													<input type="text" class="form-control" name="file_info"
														readonly="readonly" placeholder="选择xls激活码文件">
												</div>
												<div class="col-sm-4" style="text-align: right;">
													<button type="button" class="btn btn-info my-btn btn-input">
														<i class="fa fa-upload" style="line-height: 2;"></i>
														选择激活码文件
													</button>
													<input id="activeCode" type="file" class="upload"
														name="file" onchange="chooseFile(this,'activeForm');">
												</div>
												<span class="col-sm-4 check-span check-spe-span2"></span>
											</div>
									</div>
									<div class="modal-footer">
										<button type="submit" class="btn btn-default" id="insert">
											导入
										</button>

										<button type="button" class="btn btn-default"
											data-dismiss="modal">
											取消
										</button>

									</div>
									</form>

								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->



						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									激活码信息
								</h3>
							</div>
							<div class="panel-body">
								<div style="overflow: auto; height: 100%">

									<!-- 数据-->
									<table class="table table-hover">
										<thead>
											<tr>
												<th width="25%;">
													激活码ID
												</th>
												<th>
													礼包ID
												</th>
												<th>
													激活码状态
												</th>
												<th>
													领取时间
												</th>
												<th>
													领取账号
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${pageInfo.list}" var="actCode">
												<tr>
													<td>
														${actCode.codeId}
													</td>
													<td>
														${actCode.packageTitle}
													</td>
													<td>
														<c:if test="${actCode.codeStat==1}">
															<i class="glyphicon glyphicon-ok-circle"></i>
														</c:if>
														<c:if test="${actCode.codeStat==2}">
															<i class="glyphicon glyphicon-remove-circle"></i>
														</c:if>

													</td>
													<td>
														<c:choose>
															<c:when test="${actCode.getTime==null}">
																<i class="fa fa-minus"></i>
															</c:when>
															<c:otherwise>${actCode.getTime}</c:otherwise>
														</c:choose>
													</td>
													<td>
														<c:choose>
															<c:when test="${actCode.getTime==null}">
																<i class="fa fa-minus"></i>
															</c:when>
															<c:otherwise>${actCode.userName}</c:otherwise>
														</c:choose>
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-trash"
															onclick="deleteOne('${actCode.codeId}');" title="删除"></i> </a>

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
				<!-- 数据-->

			</div>
		</div>
	</body>
</html>