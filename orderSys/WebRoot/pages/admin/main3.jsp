<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>餐厅管理员</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
	<!--  title  -->
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<img src="img/logo.png" class="navbar-brand" /> 
		<span class="navbar-brand">中软国际-餐厅到店点餐系统</span>
		<ul class="nav navbar-nav navbar-right">
			<li><span class="navbar-brand">餐厅管理界面</span></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"	data-toggle="dropdown" >
				<img src="img/faces/default.jpg" width="24" height="24"
					class="img-circle" style="border:1px solid #FFF" />
				&nbsp;&nbsp;当前用户:【admin】,身份为管理员 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<input type="image" src="img/faces/default.jpg" class="img-circle" data-toggle="modal" data-target=".bs-example-modal-sm" style="width:100px;margin-left:40px"/>		
					
					<li><a href="#">修改我的密码</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">查看当前在线的厨师</a></li>
					<li><a href="#">查看当前在线的点餐员</a></li>
				</ul>
			</li>
			<li><a href="logout.order">退出登录</a></li>
		</ul>
		<form class="navbar-form navbar-right" method="post" action="sendbord.order" target="formtarget">
			<input type="text" class="form-control" placeholder="公告" style="width:300px" name="bord">
			<input class="btn btn-default" type="submit" value="发送" />
			<iframe name="formtarget" width="0" height="0" style="display: none"></iframe>
		</form>
	</div>
  	</nav>


  	<!--left-->
  	<div class="daohang" style="width:300px;height:500px;background-color:lightblue;float:left;margin-top:-20px;">
  		<div>
			<div class="container-fluid">
	    		<div class="row"></div>
	    		<div class="col-md-2"></div>
	    		<ul class="nav nav-pills ">
	    			<li role="presentation" ><a href="#"><img src="img/faces/default.jpg" class="img-circle" style="width:18px;"/>员工</a></li>
	    			<li role="presentation"><a href="#"><img src="img/faces/default.jpg" class="img-circle" style="width:18px;"/>服务员</a></li>
	    		</ul>
	    	</div>


			<ul class="nav nav-pills">
				<li role="presentation"><a href="#">员工管理</a></li>
				<li role="presentation" class="active"><a href="#">菜品管理</a></li>
	 			<li role="presentation"><a href="#">查看经营数据</a></li>	
			</ul>
			<div class="container-fluid">
	    		<div class="row"></div>
	    		<div class="col-md-3"></div>
	    		<ul class="nav nav-pills nav-stacked">
	    			<li role="presentation" class="active"><a href="#"><img src="img/faces/default.jpg" class="img-circle" style="width:18px;"/>员工管理</a></li>
	    			<li role="presentation"><a href="#"><img src="img/faces/default.jpg" class="img-circle" style="width:18px;"/>菜品管理</a></li>
	    			<li role="presentation"><a href="#"><img src="img/faces/default.jpg" class="img-circle" style="width:18px;"/>查看经营数据</a></li>
	    		</ul>
	    	</div>
	    </div>
	</div>




<div class="container-fluid">
		<div class="row">
			
			<div class="col-md-9" style="background-color:white;margin-top:-20px;">

			<br>
				<div style="background-color:lightgrey;height:35px;font-size:20px;">
					<a href="#" class="panel-title">首页</a>/

					<a href="#" class="panel-title">管理员</a>/
					
					<a href="#" class="panel-title">员工页面管理</a>
					
				</div>
				<br>
				


				<div class="panel panel-danger yuangong">

					<div class="panel-heading">
						<h2 class="panel-title">员工列表</h2>
					</div>




					<div class="panel-body" style="padding-bottom:0px">
						<div class="submit_div row">
						
							<button type="submit" class = "btn btn-primary submit_button">添加员工</button>
						
						</div>

						<div class="panel-body">
							<table style="text-align:center" border="0" rules="rows" width="100%"height="200px">
								<tr class="table_head">
									<td>菜品名</td>
									<td>价格</td>
									<td>菜品描述</td>
									<td>修改</td>
								</tr>
								<c:forEach items="${glist}" var="temp" varStatus="state">
									<tr>
										<td>${temp.dishesName}</td>
										<td>${temp.dishesPrice}</td>
										<td>${temp.dishesDiscript}</td>
										<td><a
											href="tomodifydishes.order?dishesId=${temp.dishesId}&dishesImg=${temp.dishesImg}&dishesTxt=${temp.dishesTxt}&recommend=${temp.recommend}">修改</a></td>
										<td><a
											href="todeletedishes.order?dishesId=${temp.dishesId}">删除</a></td>
										<td>
											<button type="button" class="btn btn-primary"data-toggle="modal"
											data-target=".bs-dishes${temp.dishesId}-modal-sm">查看</button>
												
												 <!-- 查看菜的信息 -->
											<div class="modal fade bs-dishes${temp.dishesId}-modal-sm" tabindex="-1"
												role="dialog" aria-labelledby="mySmallModalLabel">
												<div class="modal-dialog modal-sm">
													<div class="modal-content">
													
														<div class="panel panel-success" style="margin: 15px 0px;">
															<div class="panel-heading">
																<h2 class="panel-title">详细信息</h2>
															</div>
															<div class="panel-body" style="padding: 20px 0px;">
																<div style="width: 200px; margin: auto;">
																	
																	<div class="panel-heading">
																		<img src="${temp.dishesImg}" style="border: grey 5px solid; width: 100px;"></img>
																		<h2 class="panel-title" style="font-size: 16px;">菜号：${temp.dishesId}</h2>
																		<h2 class="panel-title" style="font-size: 16px;">菜名： ${temp.dishesName}</h2>
																		<h2 class="panel-title" style="font-size: 16px;">价格：${temp.dishesPrice}</h2>
																		<h2 class="panel-title" style="font-size: 16px;">信息： ${temp.dishesTxt}</h2>
																		<h2 class="panel-title" style="font-size: 16px;">描述：${temp.dishesDiscript}</h2>

																	</div>
																</div>

															</div>
														</div>
													</div>
												</div>
											</div>


										</td>

									</tr>
								</c:forEach>
								
							</table>
							<center>
							<nav class="fanye">
								<ul class="pagination">
								   <li><a href="toquerydishes.order?page=1">首页</a></li>
								   
								    <li><a href="toquerydishes.order?page=${page-1}">上一页</a></li>
								    
								    <li><a href="toquerydishes.order?page=${page+1}">下一页</a></li>
								    
								  </ul>

							</nav>
							</center>
						</div>
					</div>
		
				</div>

			中国中软










			</div>



		</div>

	</div>
	<!-- Small modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Small modal</button>



	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  		<div class="modal-dialog modal-sm">
    		<div class="modal-content" style="width:370px;background-color:#ccc;">
    			<div class="panel-heading" style="width:100%;height:35px;background-color:white;">
    				<h2 class="panel-title">详细信息</h2>
    			</div>
    			
    			
    			<div class="panel panel-danger" style="margin:15px 20px;">
    				<div class="panel-heading">
						<h2 class="panel-title">员工列表</h2>
					</div>
					<div class="panel-body" style="padding:20px 50px;">
						<div style="width:200px;margin:auto;">
							<img src="img/faces/default.jpg" class="img-circle" style="border:grey 5px solid;width:100px;margin-left:40px;">
							<div class="panel-heading">
								<h2 class="panel-title" style="font-size:20px;">员工账号：admin</h2>
								<h2 class="panel-title" style="font-size:16px;">员工角色：餐厅管理员</h2>
							</div>
						</div>
						
					</div>
    			</div>
    			
    			
    			<div class="panel-heading" style="width:100%;margin-top:20px;margin-bottom:0px;height:40px;background-color:white;">
    				<input class="btn btn-danger" style="width:45px;height:28px;float:right;" type="button" value="关闭">
    			</div>
    		
    		</div>
  		</div>
	</div>
	
	

</body>
<!-- 
<body>
管理员主界面
<hr>
	<table border="1">
	  	<tr  style="background-color:yellowgreen">
	  		<td>菜品名</td>
	  		<td>价格</td>
	  		<td>菜品描述</td>
	  		<td>修改</td>
	  	<tr>
		<c:forEach items="${glist}" var="temp" varStatus="state">
		  	<tr>
	  		<td>${temp.dishesName}</td>
	  		<td>${temp.dishesPrice}</td>	
	  		<td>${temp.dishesDiscript}</td>	
	  		<td><a href="tomodifydishes.order?dishesId=1">修改</a></td>	
			</tr>
		</c:forEach>
   </table>
</body>

 -->
</html>
