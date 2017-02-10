<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>	
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Login page</title>
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
    <link rel="stylesheet" href="./resources/css/home/login.css">
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> 
			</div>
		</div>
	</div>

	<div class="container">
		<div id="login-wraper">
			<form  id="loginForm" class="form login-form" action="${ctx}/login" method="POST">
				<legend>
					欢迎登陆 <span class="blue">在线教学平台</span>
				</legend>
				<div>
					<label class="control-label" for="passportName">
				 		<i class="fa fa-user fa-1x" title="用户名"></i>
					</label>
					<input class="form-control" type="text" id="passportName" name="passportName" placeholder="账号"> 
					<label class="control-label" for="password">
						<i class="fa fa-key fa-1x" title="密码"></i>
					</label>
					<input class="form-control" type="password" id="password" name="password" placeholder="密码">
				</br>
					<span id="tipMsg" class="formtips onError" style="color:red">${msg}</span>
				</div>
				<div class="footer">
					<label class="checkbox inline"> <input type="checkbox"
						id="inlineCheckbox1" value="option1"> 自动登录
					</label>
					<button type="submit" class="btn btn-success" id="loginBtn">登录</button>
				</div>

			</form>
		</div>

	</div>
	<footer class="white navbar-fixed-bottom">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-4">
					Copyright © 2017-${year} voitgxd.com, All Rights Reserved					
			</div>
			<div class="col-md-3">				
				<!--[if lt IE 9]>					
					为了更好的用户体验，建议使用Chrome,Firefox,IE9以上浏览器					
				<![endif]-->
			</div>
			<div class="col-md-3 text-right">
				  <a href="http://voitgxd.com" target="_blank">voitgxd.com</a>
			</div>
		<div class="col-md-1"></div>
		</div>
	</footer>	
	<%--<footer class="white navbar-fixed-bottom"> 忘记密码? <a
		href="/register.html"
		class="btn btn-black">找回</a> </footer>
	--%>
	<%--<script src="./resources/plugin/jquery/jquery-1.11.2.js"></script>--%>
	<%--<script src="./resources/plugin/bootstrap/js/bootstrap.js"></script>--%>
	<script src="./resources/plugin/backstretch/backstretch.min.js"></script>
	<script src="./resources/js/login.js"></script>
</body>
</html>