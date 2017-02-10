<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
		<title>礼包信息模块</title>
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css"
			rel="stylesheet" />
		<script type="text/javascript" src="../resources/js/site/giftPackage.js">
</script>
		<script type="text/javascript" src="../resources/js/common.js">
</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									礼包信息列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="overflow: auto; height: 100%">
									<!-- 数据-->
									<table id="basicTable" class="table table-hover">
										<button class="btn btn-default" type="button"
											id="add_giftPackage">
											<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
										</button>
										<thead class="">
											<tr>
												<th>
													礼包ID
												</th>
												<th>
													游戏ID
												</th>
												<th>
													礼包类型
												</th>
												<th>
													操作系统
												</th>
												<th>
													礼包标题
												</th>
												<th>
													礼包描述
												</th>
												<th>
													开始时间
												</th>
												<th>
													结束时间
												</th>
												<th>
													使用方法
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${gpfList}" var="gpf">
												<tr>
													<td>
														${gpf.packageId}
													</td>
													<td>
														${gpf.gameName}
													</td>
													<td>
														<c:if test="${gpf.packageType==1}">
											激活码
											</c:if>
														<c:if test="${gpf.packageType==2}">
											特权礼包
											</c:if>
														<c:if test="${gpf.packageType==3}">
											新服礼包
											</c:if>
														<c:if test="${gpf.packageType==4}">
											公会礼包
											</c:if>

													</td>
													<td>
														<c:if test="${gpf.osType==1}">
											IOS
											</c:if>
														<c:if test="${gpf.osType==2}">
											Android
											</c:if>
														<c:if test="${gpf.osType==3}">
											WP
											</c:if>
														<c:if test="${gpf.osType==4}">
											IOS越狱
											</c:if>
													</td>
													<td>
														${gpf.packageTitle}
													</td>
													<td>
														${gpf.packageDescribe}
													</td>
													<td>
														${gpf.beginTime}
													</td>
													<td>
														${gpf.endTime}
													</td>
													<td>
														${gpf.useMethod}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-cog"
															onclick="updateOne(${gpf.packageId});" title="修改"></i> </a>&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="#"><i class="glyphicon glyphicon-trash"
															onclick="deleteOne(${gpf.packageId});" title="删除"></i> </a>

													</td>
												</tr>
											</c:forEach>

										</tbody>
									</table>

								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 模态框-->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					style="display: none;">
					<div class="modal-dialog" style="width: 900px;">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="false">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									添加礼包信息
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form">
									<div id="pacDeatilDiv">
										<input type="hidden" name="packageId" id="packageId">

										<div class="form-group">
											<label for="gameId" class="col-sm-3 control-label">
												游戏ID：
											</label>
											<div class="col-sm-5">
												<select class="selectpicker form-control" id="gameId"
													name="gameId" data-live-search="true" onchange="clean();"
													value="">
												</select>
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>

										<div class="form-group">
											<label for="packageType" class="col-sm-3 control-label">
												礼包类型 ：
											</label>
											<div class="col-sm-5">
												<select class="selectpicker show-tick"
													data-style="btn-default" id="packageType"
													name="packageType" value="" onchange="clean();">
													<option value="1">
														激活码
													</option>
													<option value="2">
														特权礼包
													</option>
													<option value="3">
														新服礼包
													</option>
													<option value="4">
														公会礼包
													</option>
												</select>
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="packageTitle" class="col-sm-3 control-label">
												礼包标题：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="packageTitle"
													id="packageTitle" value="" onfocus="clean();">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="packageDescribe" class="col-sm-3 control-label">
												礼包描述：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control"
													name="packageDescribe" id="packageDescribe" value=""
													onfocus="clean();">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="beginTime" class="col-sm-3 control-label">
												开始时间：
											</label>
											<div class="col-sm-5">
												<div class="input-group date form_datetime"
													data-date="2015-01-15 05:25"
													data-date-format="yyyy-mm-dd hh:ii"
													data-link-field="dtp_input1">
													<input class="form-control" size="16" type="text"
														name="beginTime" id="beginTime" value="" readonly
														onfocus="clean();">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-th"></span> </span>
												</div>
											</div>
											<span class="col-sm-4 pam-desc tip"></span>
										</div>
										<div class="form-group">
											<label for="endTime" class="col-sm-3 control-label">
												结束时间：
											</label>
											<div class="col-sm-5">
												<div class="input-group date form_datetime"
													data-date="2015-01-15 05:25"
													data-date-format="yyyy-mm-dd hh:ii"
													data-link-field="dtp_input1">
													<input class="form-control" size="16" type="text"
														name="endTime" id="endTime" value="" readonly
														onfocus="clean();">
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-th"></span> </span>
												</div>
											</div>
											<span class="col-sm-4 pam-desc tip"></span>
										</div>
										<div class="form-group">
											<label for="useMethod" class="col-sm-3 control-label">
												使用方法 ：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="useMethod"
													id="useMethod" value="" onfocus="clean();">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<span class="form-control tip"
												style="text-align: center; color: red; border: 0px;"
												id="messageInfo"></span>
										</div>

									</div>

								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">
									关闭
								</button>
								<button type="button" class="btn btn-primary btn-save">
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
