<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" 
	isELIgnored="false" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>	

<!DOCTYPE HTML>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html";charset="utf-8" >    
    <title>资源列表</title>
    <meta name="viewreport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
    <script src="${ctx}/resources/plugin/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
  </head>
  
  <body>
    <div class="row">
            <div class="col-md-3">
                <a class="btn btn-default" id="addResource" href="${ctx}/resources/addResPage">
				<i class="glyphicon glyphicon-plus"></i>添加节点</a>
				<a class="btn btn-default" href="javascript:removeTreeItem()" >
				<i class="glyphicon glyphicon-trash"></i>删除节点</a>
			<div class="col-md-10"><br/></div>	
            </div>
			<div class="col-md-12">
				<div class="form-group">
					<div class="panel panel-default">
  						<div class="panel-heading">
  						<h3 class="panel-title">功能列表</h3>
  						</div>	
					 	<div class="col-md-12"><br/>
	  				    </div>
	  		 			<div class="panel-body">
			 				<div class="col-md-12">
			  	  				 <div class="col-md-5"></div>
	         						 <ul id="maintree" ></ul>
	         					</div>
	        				 </div>
	         			</div> 	 
	        	 </div>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctx}/resources/js/purview/resources.js"></script>
  </body>
</html>
