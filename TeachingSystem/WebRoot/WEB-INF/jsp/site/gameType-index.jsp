<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>游戏类型</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/WEB-INF/jsp/common/common.jsp"%>
		<link type="text/css" href="../resources/css/common.css"
			rel="stylesheet" />
		<script src="../resources/js/common.js">
</script>
		<script src="../resources/js/site/gameType.js">
</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid search">

			</div>
			<div class="row-fluid data">
				<!-- 数据-->
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									游戏类型信息列表
								</h3>
							</div>
							<div class="panel-body">
								<div style="overflow: auto; height: 100%">
									<button class="btn btn-default" type="button" id="type_add"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
									<table class="table table-hover" id="gameTypes">
										<thead>
											<tr>
												<th>
													游戏类型ID
												</th>
												<th>
													游戏类型名称
												</th>
												<th>
													游戏类型描述
												</th>
												<th>
													游戏数量
												</th>
												<th>
													类型排序
												</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${types}" var="type">
												<tr>
													<td>
														${type.gameTypeId}
													</td>
													<td>
														${type.gameTypeName}
													</td>
													<td>
														${type.typeDescribe}
													</td>
													<td>
														${type.gameNumbers}
													</td>
													<td>
														${type.typeOrder}
													</td>
													<td>
														<a href="#"><i class="glyphicon glyphicon-cog" title="修改" id="update"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="#"><i class="glyphicon glyphicon-trash" title="删除" id="delete"></i></a>
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
									<input type="hidden" name="gameTypeId" id="gameTypeId">
									<div class="form-group">
										<label for="gameTypeName" class="col-sm-2 control-label">
											游戏类型名称：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="gameTypeName"
												id="gameTypeName" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>

									<div class="form-group">
										<label for="typeDescribe" class="col-sm-2 control-label">
											游戏类型描述：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="typeDescribe"
												id="typeDescribe" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>

									<div class="form-group">
										<label for="gameNumbers" class="col-sm-2 control-label">
											游戏数量：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="gameNumbers"
												id="gameNumbers" value="" onfocus="clean();">
										</div>
										<span class="col-sm-3 pam-desc tip"></span>
									</div>
									<div class="form-group">
										<label for="typeOrder" class="col-sm-2 control-label">
											类型排序：
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" name="typeOrder"
												id="typeOrder" value="" onfocus="clean();">
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