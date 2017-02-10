<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>	
<!-- 头部 -->
<header>
<div class="headerwrapper">
	<div class="header-left">
		<!-- logo -->
		<a class="logo" href="http://huobaoyx.com"> 
			<img alt="" height="36px" src="resources/images/huobaologo_title.png">
		</a>
		<div class="pull-right">
			<a class="menu-collapse" href="#"> <i class="fa fa-bars"></i> </a>
		</div>
	</div>
	<!-- header-left -->
	<!-- 产品导航 -->
 	<span id="product">
 	
 	</span>	
 	<div class="header-right">
		<div class="pull-right">
			
			<!-- btn-group -->
			<!-- 消息 btn-group btn-group-list btn-group-messages-->
			<%--<div class=" btn-group btn-group-list">
				<button data-toggle="dropdown"
					class="btn btn-default dropdown-toggle" type="button">
					<i class="fa fa-envelope-o"></i>
					<span class="badge">2</span>
				</button>
				<div class="dropdown-menu pull-right">
					<a class="link-right" href="#"><i class="fa fa-plus"></i>
					</a>
					<h5>
						系统列表
					</h5>
					
					<div class="dropdown-footer text-center">
						<a class="link" href="#"></a>
					</div>
				</div>
				<!-- dropdown-menu -->
			</div>--%>
			<!-- btn-group -->
			<!-- 个人信息/操作 -->
			<div class="btn-group btn-group-option">
				<button data-toggle="dropdown"
					class="btn btn-default dropdown-toggle" type="button">
					<img src="${sessionScope.session_user.iconUrl}" alt="" class="img-circle avatar-img">
					<span class="user-name" style="color:white">${sessionScope.session_user.realName }</span>
					<i class="fa fa-caret-down"></i>
				</button>
				<ul role="menu" class="dropdown-menu pull-right">
					<li class="divider"></li>
					<li>
						<a href="./home/exit"><i class="glyphicon glyphicon-log-out"></i> 注销</a>
					</li>
				</ul>
			</div>
			<!-- btn-group -->
		</div>
		<!-- pull-right -->
	</div>
	<!-- header-right -->
</div>
<!-- headerwrapper -->
</header>