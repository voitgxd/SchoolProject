<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>在线教学平台</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description"
			content="Responsive HTML template for Your company">
		<meta name="author" content="8864">

		<link rel="stylesheet" href="resources/plugin/bootstrap/css/bootstrap.css" type="text/css"></link>
		<link rel="stylesheet" href="resources/css/home/pace.css">
		<link rel="stylesheet" href="resources/css/home/home.css">
		<link rel="stylesheet" href="resources/plugin/font-awesome/css/font-awesome.css">
		
		
	</head>
	<body class="pace-done">
		<div class="pace  pace-inactive">
			<div class="pace-progress" style="width: 100%;"
				data-progress-text="100%" data-progress="99">
				<div class="pace-progress-inner"></div>
			</div>
			<div class="pace-activity"></div>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp"></jsp:include>
		<section>
		<div class="mainwrapper">
			<div class="leftpanel">
				<div class="media profile-left">
					<a class="pull-left profile-thumb"> <img
							alt="" src="${sessionScope.session_user.iconUrl}"
							class="img-circle"> </a>
					<div class="media-body">
						<h4 class="media-heading">
							<b>${sessionScope.session_user.realName}</b>
						</h4>
						<small class="text-muted">${sessionScope.session_user.userName}</small>
					</div>
				</div>
				<!-- media -->
				<h5 class="leftpanel-title">
					<%--模块名称(如官网后台)--%><%--
					${product.productName}
				--%>	
	    	    </h5>
				<ul class="nav nav-pills nav-stacked">
					<admin:menuResourceTag
						roleResourceBeanList="${role_resources}">
					</admin:menuResourceTag>
				</ul>
			</div>
			<div class="mainpanel">
				<div class="pageheader">
					<div class="media">
						<div class="pageicon pull-left">
							<i class="fa fa-home"></i>
						</div>
						<div class="media-body" id="breadcrumb">
						    <input id="productName" type="hidden" value="${product.resName}"/>
			                <div id="breadcrumbsub" ></div> 
						    <%--<strong>您好,<a href="#">${sessionScope.session_user.userName }</a></strong>
						--%>
						</div>
					</div>
				</div>
				<div class="contentpanel">
					<div class="row">
						<div class="col-md-12">
							<iframe id="mainFrame" name="mainFrame" scrolling="no"

								style="width: 100%; height:880px; border: 0; overflow: auto;"

								src="${ctx}/home/welcome"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
	<script src="resources/js/jquery/jquery-1.11.2.js"></script>
	<script src="resources/plugin/bootstrap/js/bootstrap.js"></script>
	<script src="resources/js/home/home.js"></script>
	<script src="resources/js/home/pace.min.js"></script>	
	</body>
</html>