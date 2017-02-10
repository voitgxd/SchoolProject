<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
		<title>游戏信息模块</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->
		<link type="text/css" href="../resources/css/common.css"
			rel="stylesheet" />
		<script type="text/javascript" src="../resources/js/site/gameInfo.js">
</script>
		<script type="text/javascript" src="../resources/js/site/common.js">
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
									游戏信息列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="overflow: auto; height: 100%">
									<button class="btn btn-default" type="button" id="add_gameInfo">
										<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
									</button>
									<!-- 数据-->
									<table id="basicTable" class="table table-hover">
										<thead class="">
											<tr>
												<th>
													游戏ID
												</th>
												<th>
													游戏名称
												</th>
												<th>
													游戏类型
												</th>
												<th>
													操作系统
												</th>
												<th>
													运营状态
												</th>
												<th>
													游戏排名
												</th>
												<th>
													游戏大小
												</th>
												<th>
													星级评价
												</th>
												<th>
													图片地址
												</th>
												<th>
													下载地址
												</th>
												<th>
													论坛地址
												</th>
												<th>
													游戏描述
												</th>
												<th>
													操作
												</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${gameList}" var="game">
												<tr>
													<td>
														${game.gameId}
													</td>
													<td>
														${game.gameName}
													</td>
													<td>
														${game.gameTypeName}
													</td>
													<td>
														<c:if test="${game.osType==1}">
											IOS
											</c:if>
														<c:if test="${game.osType==2}">
											Android
											</c:if>
														<c:if test="${game.osType==3}">
											WP
											</c:if>
														<c:if test="${game.osType==4}">
											IOS越狱
											</c:if>

													</td>
													<td>
														<c:if test="${game.gameState==1}">
											公测
											</c:if>
														<c:if test="${game.gameState==2}">
											封测
											</c:if>
														<c:if test="${game.gameState==3}">
											内侧
											</c:if>
													</td>
													<td>
														${game.rankingOrder}
													</td>
													<td>
														${game.dataPacket}
													</td>
													<td>
														${game.gameEvaluate}
													</td>
													<td>
														${game.pictureUrl}
													</td>
													<td>
														${game.downloadUrl}
													</td>
													<td>
														${game.forumUrl}
													</td>
													<td>
														${game.gameDescribe}
													</td>
													<td>

														<a href="#"><i class="glyphicon glyphicon-cog"
															onclick="updateOne(${game.gameId});" title="修改"></i> </a>&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="#"><i class="glyphicon glyphicon-trash"
															onclick="deleteOne(${game.gameId});" title="删除"></i> </a>
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

				<!-- datatable -->


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
									添加游戏信息
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" role="form">
									<div id="pacDeatilDiv">
										<input type="hidden" name="gameId" id="gameId">
										<div class="form-group">
											<label for="gameName" class="col-sm-3 control-label">
												游戏名称：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="gameName"
													id="gameName" value="" onfocus="clean();">

											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="gameId" class="col-sm-3 control-label">
												游戏类型：
											</label>
											<div class="col-sm-5">
												<select class="selectpicker form-control" id="gameType"
													name="gameType" data-live-search="true" value=""
													onchange="clean();">
												</select>
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="osType" class="col-sm-3 control-label">
												操作系统 ：
											</label>
											<div class="col-sm-5">
												<select class="selectpicker show-tick"
													data-style="btn-default" id="osType" name="osType" value=""
													onchange="clean();">

													<option value="1">
														IOS
													</option>
													<option value="2">
														Android
													</option>
													<option value="3">
														WP
													</option>
													<option value="4">
														IOS越狱
													</option>
												</select>
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="gameState" class="col-sm-3 control-label">
												运营状态 ：
											</label>
											<div class="col-sm-5">
												<select class="selectpicker show-tick"
													data-style="btn-default" id="gameState" name="gameState"
													value="" onfocus="clean();">

													<option value="1">
														公测
													</option>
													<option value="2">
														封测
													</option>
													<option value="3">
														内侧
													</option>
												</select>
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<!-- <div class="form-group">
											<label for="rankingOrder" class="col-sm-3 control-label">
												游戏排名：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="rankingOrder"
													id="rankingOrder" value="" onfocus="clean();">
											</div>
											 	
										<span class="col-sm-4 pam-desc tip"> </span>
									</div>-->
										<div class="form-group">
											<label for="dataPacket" class="col-sm-3 control-label">
												游戏包大小：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="dataPacket"
													id="dataPacket" value="">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="gameEvaluate" class="col-sm-3 control-label">
												星级评价：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="gameEvaluate"
													id="gameEvaluate" value="">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="pictureUrl" class="col-sm-3 control-label">
												图片地址：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="pictureUrl"
													id="pictureUrl" value="">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="downloadUrl" class="col-sm-3 control-label">
												游戏下载地址：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="downloadUrl"
													id="downloadUrl" value="">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="forumUrl" class="col-sm-3 control-label">
												论坛地址：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="forumUrl"
													id="forumUrl" value="">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="officialUrl" class="col-sm-3 control-label">
												官方网址：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="officialUrl"
													id="officialUrl" value="">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>
										<div class="form-group">
											<label for="gameDescribe" class="col-sm-3 control-label">
												游戏描述：
											</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" name="gameDescribe"
													id="gameDescribe" value="">
											</div>
											<span class="col-sm-4 pam-desc tip"> </span>
										</div>

										<div class="form-group">
											<span class="form-control"
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
