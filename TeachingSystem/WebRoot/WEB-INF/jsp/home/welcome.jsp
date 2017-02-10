<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html";charset="utf-8" >    
	    <title>welcome</title>
	    <meta name="viewreport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
		
		<script type="text/javascript">      
	        	$(function () {
					$("#weekLoginNum").highcharts({
						chart: {
				            animation: {
				                duration: 1500,
				                easing: 'easeOutBounce'
				            }
				        },
				        credits:{
							enabled:false	
					        },
				        exporting: {
				            filename: 'custom-file-name'
				        },
				        title:{
							text:'系统周访问量统计'
					        },
				        xAxis:{
							categories:[
								<c:forEach items="${weekLoginNumList}" var="weekLoginNum">
									'${weekLoginNum.lastLoginTime}',
								</c:forEach>
							],	
							tickmarkPlacement:'on'
							},
						yAxis:{
							title:{
								text:'千人'
								},
								plotLines:[{
									value: 0,
					                width: 1,
					                color: '#808080'
								}]
							},
						tooltip:{
								valueSuffix: '(人)'
							},
						legend:{
							layout:'vertical',
							align:'right',
							verticalAlign:'middle',
							borderWidth:0
							},
						series:[{
							name:'访问量',
							data:[
								<c:forEach items="${weekLoginNumList}" var="weekLoginNum">
									${weekLoginNum.loginCount},
								</c:forEach>
							]
						}]
					});


					$("#monthLoginNum").highcharts({
						chart: {
				            animation: {
				                duration: 1500,
				                easing: 'easeOutBounce'
				            }
				        },
				        credits:{
							enabled:false	
					        },
				        exporting: {
				            filename: 'custom-file-name'
				        },
				        title:{
							text:'系统月访问量统计'
					        },
				        xAxis:{
							categories:[
								<c:forEach items="${monthLoginNumList}" var="monthLoginNum">
									'${monthLoginNum.lastLoginTime}',
								</c:forEach>
							],	
							tickmarkPlacement:'on'
							},
						yAxis:{
							title:{
								text:'千人'
								},
								plotLines:[{
									value: 0,
					                width: 1,
					                color: '#808080'
								}]
							},
						tooltip:{
								valueSuffix: '(人)'
							},
						legend:{
							layout:'vertical',
							align:'right',
							verticalAlign:'middle',
							borderWidth:0
							},
						series:[{
							name:'访问量',
							data:[
								<c:forEach items="${monthLoginNumList}" var="monthLoginNum">
									${monthLoginNum.loginCount},
								</c:forEach>
							]
						}]
					});
				});		        
		</script> 
	</head>
	<body>		
		<div class="panel panel-default">
			<div class="panel-heading">
				周访问量				
			</div>	
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12" id="weekLoginNum">
					</div>
				</div>
			</div>
		</div>
				
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="row">						
						<div class="col-md-12">
							月访问量
						</div>
					</div>
			</div>	
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-12" id="monthLoginNum">
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>