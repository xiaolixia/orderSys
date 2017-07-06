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
<link rel="stylesheet" href="css/dashboard.css">
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
function begin1() {
	txtAjaxRequest("getrtbord_dishes.order?messageTitle=rtbord1&time="
			+ Math.random(), "get", true, null, bordCallback1, null, null);
}

function bordCallback1(responseTxt, obj) {
	if (responseTxt != "Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut") {
		var msg = document.getElementById("msg1");

		msg.innerHTML = responseTxt;
		
	}
	txtAjaxRequest("getrtbord_dishes.order?messageTitle=rtbord1&time="
			+ Math.random(), "get", true, null, bordCallback1, null, null);
	
}
function begin() {
	begin1();
	txtAjaxRequest("getrtbord.order?messageTitle=rtbord&time="
			+ Math.random(), "get", true, null, bordCallback, null, null);
}

function bordCallback(responseTxt, obj) {
	if (responseTxt != "Org_EricYang_Platform_ErrorMsg:ServerPush_Wait_TimeOut") {
		var msg = document.getElementById("msg");

		msg.innerHTML = responseTxt;
		
	}
	txtAjaxRequest("getrtbord.order?messageTitle=rtbord&time="
			+ Math.random(), "get", true, null, bordCallback, null, null);
	
}

function myAdd(obj){
	obj.value++;
}
function myMin(obj){
	if(obj.value>0){
		obj.value--;
	}
}
function mytest(){
	var tableId=document.getElementById("setTableId").value;
	if(!isNaN(tableId)&&tableId!=""){
		submitOrder();
	}
	else{
		alert("请输入数字");
	}
}
function addCart(obj){
	var child=obj.childNodes;
	for(var i=0;i<child.length;i++){
		if(child[i].nodeName=="#text" && !/\s/.test(child.nodeValue)){}
		else{
			if((child[i].value=="+" || child[i].value=="-")){}
			else{
			
			//	window.location.href="/OrderSys/addcart.order?num="+child[i].value+"&dishesId="+child[i].name+"&tableId=-1"+"&time=" + Math.random()+"&ajax=0";
				txtAjaxRequest("/OrderSys/addcart.order?num="+child[i].value+"&dishesId="+child[i].name+"&tableId=-1"+"&time=" + Math.random()+
						"&ajax=0&waiterId="+document.getElementById("userId").value, "get", true, null,
						ajaxAddSuccess, null, null);
				break;
			}
		}
	}

}


function ajaxAddCart(obj){
	var child=obj.childNodes;
	for(var i=0;i<child.length;i++){
		if(child[i].nodeName=="#text" && !/\s/.test(child.nodeValue)){}
		else{
			if((child[i].value=="+" || child[i].value=="-")){}
			else{
			//	window.location.href="/OrderSys/addcart.order?num="+child[i].value+"&dishesId="+child[i].name+"&tableId=-1";
				txtAjaxRequest("/OrderSys/ajaxaddcart.order?num="+child[i].value+"&dishesId="+child[i].name+"&tableId=-1"+"&time=" + Math.random()+"&ajax=1", "get", true, null,
						ajaxAddSuccess, null, null);
				break;
			}
		}
	}

}

function ajaxAddSuccess(responseTxt, obj){
	alert(responseTxt);
}


function submitOrder(){
	var tableId=document.getElementById("setTableId").value;
	window.location.href="/OrderSys/addcart.order?num=0&dishesId=0&tableId="+tableId+"&time=" + Math.random()+"&ajax=0&waiterId="+document.getElementById("userId").value;

}

function ajaxSubmitOrder(){
	window.location.href="/OrderSys/addcart.order?num=0&dishesId=0&tableId=1"+"&time=" + Math.random()+"&ajax=1";

}

</script>
<style type="text/css">
#aa{
  	  padding: 2.5px 7px;
    border: solid 1px #d0d0d0;
    border-left: none;
    border-right: none;
    cursor: pointer;

}
.reduce{
	left:1px;
}
.plus{
	right:1px;
}
.shopping{
	width:150px;
	height:150px;
}
</style>
</head>

<body style="font-family: 微软雅黑" onload="begin()"><!-- onload="getUserList(1)" -->
	<!--  title  
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
-->

<!-- 老师的nav -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<img src="img/logo.png" class="navbar-brand" /> <span
				class="navbar-brand">${ORDER_SYS_NAME}</span>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><span class="navbar-brand">餐厅管理界面</span></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><img
						src="img/faces/${USER_INFO.faceimg }" width="24" height="24"
						class="img-circle" style="border:1px solid #FFF" />&nbsp;&nbsp;当前用户:【${USER_INFO.userAccount
						}】,身份为服务员 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="img/faces/${USER_INFO.faceimg }" width="128" height="128"
							class="img-circle"
							style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px" /></li>
						<li><a href="tomodifymyinfo.order?userId=${USER_INFO.userId }">修改我的密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="toonlinewaiters.order">查看当前在线的厨师 </a></li>
						<li><a href="toonlinekitchen.order">查看当前在线的点餐员</a></li>
						<input type = "hidden" value="${USER_INFO.userId}" id = "userId">

					</ul></li>
				<li><a href="logout.order">退出登录</a></li>
			</ul>


<!-- 
			<form class="navbar-form navbar-right" method="post"
				action="sendbord.order" target="formtarget">
				<input type="text" class="form-control" placeholder="公告"
					style="width:300px" name="bord"><input
					class="btn btn-default" type="submit" value="发送" />
				<iframe name="formtarget" width="0" height="0" style="display: none"></iframe>
			</form>
			 -->
			 <div class="navbar-form navbar-right" method="post"
				action="sendbord.order" target="formtarget">
				<input type="text" id = "setTableId" class="form-control" placeholder="桌号"
					style="width:300px" name="bord" value="" onblur=""></input>

			</div>
		</div>
	</div>
	</nav>




  	<!--left
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
	    			<li role="presentation"><a href="touseradmin.order?page=1"><img src="img/faces/default.jpg" class="img-circle" style="width:18px;"/>员工管理</a></li>
	    			<li role="presentation" class="active"><a href="#"><img src="img/faces/default.jpg" class="img-circle" style="width:18px;"/>菜品管理</a></li>
	    			<li role="presentation"><a href="#"><img src="img/faces/default.jpg" class="img-circle" style="width:18px;"/>查看经营数据</a></li>
	    		</ul>
	    	</div>
	    </div>
	</div>
-->

<!-- 老师的left -->
<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18; margin-bottom: 10px; margin-left: 10px"><i
						class="icon-user icon-large"></i>&nbsp;服务员点餐功能</li>
					<li class="active"><a href="towaitermain.order?page=1"><i
							class="icon-glass icon-large"></i>&nbsp; 服务点餐界面 <span
							class="sr-only">(current)</span></a></li>
					<li><a href="tooperatedata.order?page=1"><i
							class="icon-money icon-large"></i>&nbsp;餐桌结账</a></li>
			</div>





<!-- right 
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
						<h2 class="panel-title">菜品列表</h2>
					</div>




					<div class="panel-body" style="padding-bottom:0px">
						<div class="submit_div row">
						
							<a	href="toadddishes.order"  class = "btn btn-primary submit_button">
								添加菜品
							</a>
						</div>

						<div class="panel-body">
							<table style="text-align:center" border="0" rules="rows" width="100%"height="200px">
								<tr class="table_head">
									<td>菜品名</td>
									<td>价格</td>
									<td>菜品描述</td>
									<td>是否为特色菜</td>
									<td colspan="3">操作</td>
								</tr>
								<c:forEach items="${glist}" var="temp" varStatus="state">
									<tr>
										<td>${temp.dishesName}</td>
										<td>${temp.dishesPrice}</td>
										<td>${temp.dishesDiscript}</td>
										<td>${temp.recommend}</td>
										
										<td><a href="tomodifydishes1.order?dishesId=${temp.dishesId}">修改</a></td>
										<td><a
											href="todeletedishes.order?dishesId=${temp.dishesId}">删除</a></td>
										
										<td>
											<button type="button" class="btn btn-primary"data-toggle="modal"
											data-target=".bs-dishes${temp.dishesId}-modal-sm">查看</button>
												
												 
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
							
								  <ul class="pager">
								<li><a href="toquerydishes.order?page=1" id="firstpage">&larr;首页</a></li>
								<li><a href="toquerydishes.order?page=${page-1} " id="pre">上一页</a></li>
								<li><a href="toquerydishes.order?page=${page+1} " id="next">下一页</a></li>
								<li><a href="toquerydishes.order?page=-1" id="last">末页&rarr;</a></li>
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
-->

<!-- 老师的right -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>服务员</li>
					<li class="active">菜品管理界面</li>
				</ol>
				<div class="panel panel-danger">
									<div class="panel-heading">
										<i class="i"></i>&nbsp;最新公告消息
									</div>
									<div class="glyphicon glyphicon-envelope"
										style="color: #FFA500; font-size: 25px; margin: 25px 20px; display: inline-block;" id="msg"></div>
								</div>
				<div class="panel panel-danger">
									<div class="panel-heading">
										<i class="i"></i>&nbsp;最新菜品消息
									</div>
									<div class="glyphicon glyphicon-envelope"
										style="color: #FFA500; font-size: 25px; margin: 25px 20px; display: inline-block;" id="msg1"></div>
								</div>
				<div class="panel panel-danger">

					<div class="panel-heading">
						<h3 class="panel-title">菜品列表</h3>
					</div>
					
					
					
					<div class="panel-body">

						<div class="table-responsive">
						<table>
						<tr>
							<c:forEach items="${glist}" var="temp" varStatus="state">
								<td style="width:300px;"><center style="margin-bottom:20px;margin-top:20px;">
										<img src="img/${temp.dishesImg}" class="img-thumbnail shopping"
										data-toggle="modal" data-target="#${temp.dishesId}"
										style="border-radius: 15px; width: 180px; height: 180px; margin-left:0px" />
										<div>${temp.dishesName }</div>
										<div>
											<input type="button" class="btn btn-default"
												onclick="myMin(this.nextSibling.nextSibling)" value="-" /> <input class="btn btn-default"
												type="button" value="0" 
												disabled="disabled" name="${temp.dishesId }"> <input type="button" class="btn btn-default"
												onclick="myAdd(this.previousSibling.previousSibling)"
												value="+" />
										</div>
										<div>
											<input type="button" class="btn btn-danger" value="加入点餐车"
												onclick="addCart(this.parentNode.previousSibling.previousSibling)">
										</div>
									</center></td>
									
									
									<div class="modal fade" id="${temp.dishesId}" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel">
									<form method="post" action="modifydishes.order">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<h4 class="modal-title" id="myModalLabel">详细信息</h4>
												</div>
												<div class="modal-body"
													style="background-image: url('img/body-bg.png')">
													<div class="panel panel-danger" style="margin-top: 10px">
														<div class="panel-heading">
															<h3 class="panel-title">菜品详情</h3>
														</div>
														<div class="panel-body">
															<div style="text-align: center;">
																<img src="img/${temp.dishesImg}" id="dishesImg"
																	width="200px" height="200px" class="img-circle"
																	style="border: 1px solid #CCC; box-shadow: 0 0 10px rgba(100, 100, 100, 1);" />
															</div>
															<p>
															<h2 style="text-align: center;">
																菜品名称： <span id="dishesName">${temp.dishesName}</span>
															</h2>
															  <c:if test="${temp.recommend == 1}"> 
																<h3 style="text-align: center;">
																	<span class="icon-heart"
																		style="color: red; font-weight: bold;" id="recommend">推荐菜品</span>
																</h3>
														</c:if>  
														
														
															<hr />
															<p>
															<h3>菜品简介：</h3>
															<p>
																<span id="dishesDiscript">${temp.dishesDiscript}</span>
															</p>
															<p>
															<h3>菜品描述：</h3>
															<p>
																<span id="dishesTxt">${temp.dishesTxt}</span>
															</p> 
															<h3>
																菜品价格：<span id="dishesPrice">${temp.dishesPrice}</span>(元)
															</h3>
															<%-- <p><h3><span >${temp.recommend}</span></h3></p> --%>
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">关闭</button>
												</div>
											</div>
										</div>
									</form>
								</div>
							</c:forEach>
						</tr>
			
						<tr>
							<c:forEach items="${glist2}" var="temp" varStatus="state">
								<td style="width:300px;"><center>
										<img src="img/${temp.dishesImg}" class="img-thumbnail shopping"
										data-toggle="modal" data-target="#${temp.dishesId}"
										style="border-radius: 15px; width: 180px; height: 180px; margin-left:0px" />
										<div>${temp.dishesName }</div>
										<div>
											<input type="button" class="btn btn-default"
												onclick="myMin(this.nextSibling.nextSibling)" value="-" /> <input class="btn btn-default"
												type="button" value="0" 
												disabled="disabled" name="${temp.dishesId }"> <input type="button" class="btn btn-default"
												onclick="myAdd(this.previousSibling.previousSibling)"
												value="+" />
										</div>
										<div>
											<input type="button" class="btn btn-danger" value="加入点餐车"
												onclick="addCart(this.parentNode.previousSibling.previousSibling)">
										</div>
									</center></td>
							</c:forEach>
						</tr>
						
			
						
						</table>
							<nav>
							<ul class="pager">
								<li><a href="towaitermain.order?page=1" id="firstpage">&larr;首页</a></li>
								<li><a href="towaitermain.order?page=${page-1} " id="pre">上一页</a></li>
								<li><a href="towaitermain.order?page=${page+1} " id="next">下一页</a></li>
								<li><a href="towaitermain.order?page=-1" id="last">末页&rarr;</a></li>
							</ul>
							</nav>
							<div style="text-align:center;"><div class="btn btn-danger" onclick = "mytest()">提交订单</div></div>

						</div>

					</div>
				</div>

				<div
					style="height:1px;width: 100%;background: #CCC;margin-bottom: 10px"></div>
				<footer>
				<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
				</footer>

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
