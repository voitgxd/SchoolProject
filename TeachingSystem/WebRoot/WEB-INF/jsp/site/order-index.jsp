<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>游戏排行</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css"
			rel="stylesheet" />
		<script src="../resources/js/common.js">
</script>
		<script src="../resources/js/site/order.js">
</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid search">

			</div>
			<div class="row-fluid data">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									游戏排行信息列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="height: 100%;" >
									<!-- 数据-->
									<button class="btn btn-default" type="button" id="order_add">
										<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
									</button>
									<table class="table table-hover" id="gamesList">
										<thead>
											<tr>
												<th>
													游戏ID
												</th>
												<th>
													游戏名称
												</th>
												<th>
													查询类型
												</th>
												<th>
													排行ID
												</th>
												<th>
													排行得分
												</th>
												<th>
													排行状态
												</th>
												<th>
													初始得分
												</th>
												<th>
													游戏状态
												</th>
												<th>
													统计时间
												</th>
												<th>
													图片地址
												</th>
												<th>
													Inner名
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${games.list}" var="game">
												<tr>
													<td>
														${game.gameId}
													</td>
													<td>
														${game.gameName}
													</td>
													<td>
														${game.queryType}
													</td>
													<td>
														${game.orderId}
													</td>
													<td>
														${game.gameScore}
													</td>
													<td>
														<c:choose>
															<c:when test="${game.orderState==0}">持平<i class="glyphicon glyphicon-minus" title="持平"></i></c:when>
															<c:when test="${game.orderState==1}">上升<i class="glyphicon glyphicon-chevron-up" title="上升"></i></c:when>
															<c:otherwise>下降<i class="glyphicon glyphicon-chevron-down" title="下降"></i></c:otherwise>
														</c:choose>
													</td>
													<td>
														${game.initScore}
													</td>
													<td>
														<c:choose>
															<c:when test="${game.gameState==0}"><i class="glyphicon glyphicon-ok" title="正常"></i></c:when>
															<c:otherwise><i class="glyphicon glyphicon-remove" title="失效"></i></c:otherwise>
														</c:choose>
													</td>
													<td>
														${game.statTime}
													</td>
													<td>
														${game.pictureUrl}
													</td>
													<td>
														${game.innerName}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-cog"
															title="修改" id="update"></i> </a>
														<a href="#"><i class="glyphicon glyphicon-trash"
															title="删除" id="delete"></i> </a>
														<c:if test="${game.orderId!=games.minOrderId}"><a href="#"><i class="glyphicon glyphicon-arrow-up"
															title="上升" id="up"></i> </a></c:if>
														<c:if test="${game.orderId!=games.maxOrderId}"><a href="#"><i class="glyphicon glyphicon-arrow-down"
															title="下降" id="down"></i> </a></c:if>
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
			</div>
			<div class="row-fluid foot">
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
									<input type="hidden" name="flag" id="flag">
									<input type="hidden" name="gameId" id="gameId">
									<div class="form-group">
										<label for="gameName" class="col-sm-2 control-label">
											游戏名称：
										</label>
										<div class="col-sm-7">
											<select class="selectpicker form-control" id="gameName" name="gameName"
											data-live-search="true" onfocus="clean();" value=""></select>
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>

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
										<label for="orderId" class="col-sm-2 control-label">
											排行ID：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="orderId"
												id="orderId" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="gameScore" class="col-sm-2 control-label">
											排行得分：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="gameScore"
												id="gameScore" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="orderState" class="col-sm-2 control-label">
											排行状态 ：
										</label>
										<div class="col-sm-7">
											<select class="selectpicker show-tick form-control"
												data-style="btn-default" id="orderState" name="orderState"
												value="" onchange="clean();">
												<option value="0">
													持平
												</option>
												<option value="1">
													上升
												</option>
												<option value="-1">
													下降
												</option>
											</select>
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="initScore" class="col-sm-2 control-label">
											初始得分：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="initScore"
												id="initScore" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="gameState" class="col-sm-2 control-label">
											游戏状态：
										</label>
										<div class="col-sm-7">
											<select class="selectpicker show-tick form-control"
												data-style="btn-default" name="gameState" id="gameState"
												value="" onchange="clean();">
												<option value="0">
													正常
												</option>
												<option value="-1">
													失效
												</option>
											</select>
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="statTime" class="col-sm-2 control-label">
											统计时间：
										</label>
										<div class="col-sm-7">
											<div class="input-group date form_datetime"
												data-date="2015-01-15 05:25"
												data-date-format="yyyy-mm-dd hh:ii"
												data-link-field="dtp_input1">
												<input class="form-control" size="16" type="text"
													name="statTime" id="statTime" value="" readonly
													placeholder="请选择统计时间" onfocus="clean();">
												<span class="input-group-addon"><span
													class="glyphicon glyphicon-th"></span> </span>
											</div>
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="pictureUrl" class="col-sm-2 control-label">
											图片地址：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="pictureUrl"
												id="pictureUrl" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="innerName" class="col-sm-2 control-label">
											Inner名：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="innerName"
												id="innerName" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="gameUrl" class="col-sm-2 control-label">
											链接地址：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="gameUrl"
												id="gameUrl" value="" onfocus="clean();">
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