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

<title>${ORDER_SYS_NAME}-餐厅管理员</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/dashboard.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	function changeOrder(obj,id) {
		if (confirm("管理员确认买单？")) {
			xmlAjaxRequest("deletedishes.order?orderId="+ id + "&time="
					+ Math.random(), "get", true, null, changeCallback, obj, null);

		}
	}

	function changeCallback(responseTxt, obj) {

		alert("确认买单！");
		obj.parentNode.parentNode.parentNode
				.removeChild(obj.parentNode.parentNode);
		location.reload(false);
	}
	
	function cancelOrder(obj,id) {
		if (confirm("管理员确认免单？")) {
			xmlAjaxRequest("cancelOrder.order?orderId="+ id + "&time="
					+ Math.random(), "get", true, null, changeCallback, obj, null);

		}
	}

	function cancelCallback(responseTxt, obj) {

		alert("确认免单！");
		obj.parentNode.parentNode.parentNode
				.removeChild(obj.parentNode.parentNode);
		location.reload(false);
	}


	function getDishesList(page) {

		xmlAjaxRequest("getdishesbypage.order?page=" + page + "&time="
				+ Math.random(), "get", true, null, showList, null, null);
	}

	function showList(responseXml, obj) {

		var maxPage = responseXml.getElementsByTagName("maxPage");
		maxPage = maxPage[0].childNodes;
		//alert(maxPage[0].nodeValue);
		var link = document.getElementById("last");
		link.href = "javascript:getDishesList(" + maxPage[0].nodeValue + ")";

		var page = responseXml.getElementsByTagName("page");
		page = page[0].childNodes;
		link = document.getElementById("next");
		link.href = "javascript:getDishesList("
				+ (parseInt(page[0].nodeValue) + 1) + ")";
		link = document.getElementById("pre");
		link.href = "javascript:getDishesList("
				+ (parseInt(page[0].nodeValue) - 1) + ")";
		var table = document.getElementById("orderTable");
		table.innerHTML = "";
		var disheses = responseXml.getElementsByTagName("dishes");
		for (var i = 0; i < disheses.length; i++) {
			var dishes = disheses[i];
			var attrs = dishes.childNodes;
			var dishesId;
			var dishesName;
			var dishesDiscript;
			var dishesImg;
			var dishesTxt;
			var recommend;
			var dishesPrice;
			for (var j = 0; j < attrs.length; j++) {
				var attr = attrs[j];
				if (attr.nodeName == "dishesId") {
					dishesId = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesName") {
					dishesName = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesDiscript") {
					dishesDiscript = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesImg") {
					dishesImg = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesTxt") {
					dishesTxt = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "recommend") {
					recommend = attr.childNodes[0].nodeValue;
				}
				if (attr.nodeName == "dishesPrice") {
					dishesPrice = attr.childNodes[0].nodeValue;
				}
			}

			var newLine = "<tr><td>" + dishesId + "</td><td>" + dishesName
					+ "</td><td>" + dishesDiscript + "<td>" + dishesPrice
					+ "</td>";
			var recommendString = recommend == "0" ? "非特色菜品" : "特色菜品";
			newLine += "<td>" + recommendString + "</td>";

			newLine += "<td>";
			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-cogs icon-large' title='编辑菜品信息' onclick='window.location=\"tomodifydishes.order?dishesId="
					+ dishesId + "\"'></i>&nbsp;&nbsp;";

			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-sitemap icon-large' title='查看菜品详情' onclick=detail(\""
					+ dishesName
					+ "\",\""
					+ dishesDiscript
					+ "\",\""
					+ dishesTxt
					+ "\","
					+ dishesPrice
					+ ","
					+ recommend
					+ ",\""
					+ dishesImg + "\")></i>&nbsp;&nbsp;";

			newLine += "<i style='cursor: pointer; font-size: 14;'";
			newLine += "onmouseover='this.style.color=\"orange\"'";
			newLine += "onmouseout='this.style.color=\"black\"'";
			newLine += "class='icon-remove-sign icon-large' title='删除该菜品' onclick='deleteDishes("
					+ dishesId + ",\"" + dishesName + "\",this)'></i>";

			newLine += "</td></tr>";

			table.innerHTML += newLine;

		}

	}
</script>
<script type="text/javascript">
var add=document.getElementById("next");
var del=document.getElementById("prove");
  function addn(){
	  var val=document.getElementById("aa").innerHTML;
	  
	  val++;
	  document.getElementById("aa").innerHTML=val;
  }
  function deln(){
 var val=document.getElementById("aa").innerHTML;
	  if(val!=0){
		  val--;
		  document.getElementById("aa").innerHTML=val;
	  }
	  else{
	  document.getElementById("aa").innerHTML=0;
	  }
  }	
</script>
<script type="text/javascript">
	var add=document.getElementById("next");
	var del=document.getElementById("prove");
		function addn1(){
			var val=document.getElementById("bb").innerHTML;
			val++;
			document.getElementById("bb").innerHTML=val;
		}
		function deln1(){
			var val=document.getElementById("bb").innerHTML;
			if(val!=0){
				val--;
				document.getElementById("bb").innerHTML=val;
			}
			else{
				document.getElementById("bb").innerHTML=0;
			}
		}
</script>
<style type="text/css">
#aa {
	padding: 2.5px 7px;
	border: solid 1px #d0d0d0;
	border-left: none;
	border-right: none;
	cursor: pointer;
}

#bb {
	padding: 2.5px 7px;
	border: solid 1px #d0d0d0;
	border-left: none;
	border-right: none;
	cursor: pointer;
}

.reduce {
	left: 1px;
}

.plus {
	right: 1px;
}
</style>
</head>
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
						}】,身份为管理员 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center;padding-top: 15px"><img
							src="img/faces/${USER_INFO.faceimg }" width="128" height="128"
							class="img-circle"
							style="border:1px solid #CCC;box-shadow:0 0 10px rgba(100, 100, 100, 1);margin-bottom: 20px" /></li>
						<li><a href="tomodifymyinfo.order?userId=${USER_INFO.userId}">修改我的密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="toonlinewaiters.order">查看当前在线的厨师 </a></li>
						<li><a href="toonlinekitchen.order">查看当前在线的点餐员</a></li>


					</ul></li>
				<li><a href="logout.order">退出登录</a></li>
			</ul>



			<form class="navbar-form navbar-right" method="post"
				action="sendbord.order" target="formtarget">
				<input type="text" class="form-control" placeholder="公告"
					style="width:300px" name="bord"><input
					class="btn btn-default" type="submit" value="发送" />
				<iframe name="formtarget" width="0" height="0" style="display: none"></iframe>
			</form>
		</div>
	</div>
	</nav>

<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-credit-card icon-large"></i>&nbsp;运营功能</li>
					<li ><a href="toadmincharge.order?page=1"><i
							class="icon-money icon-large"></i>&nbsp; 顾客结账界面 <span
							class="sr-only">(current)</span></a></li>


				</ul>
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-cog icon-large"></i>&nbsp;餐厅管理</li>
					<li  ><a href="touseradmin.order?page=1"><i
							class="icon-group icon-large"></i>&nbsp;员工管理</a></li>
					<li class="active"><a href="toadminmain.order?page=1"><i
							class="icon-coffee icon-large"></i>&nbsp;菜品管理</a></li>
					<li><a href="tooperatedata.order"><i
							class="icon-bar-chart icon-large"></i>&nbsp;查看经营数据 </a></li>

				</ul>
			</div>




			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>管理员</li>
					<li class="active">结账界面</li>
				</ol>




				<div class="container">
					<div class="row" style="padding-top: 0px">
					<div class="panel panel-danger" style="width:89%;">
									<div class="panel-heading">推荐菜品
									</div>
									<div class="panel-body">
							<c:forEach items="${glist1}" var="temp" varStatus="state">
								<div class="col-md-3" style="height: 320px; width: 250px;">
									<img src="img/${temp.dishesImg}" class="img-thumbnail"
										data-toggle="modal" data-target="#${temp.dishesId}"
										style="border-radius: 15px; width: 180px; height: 180px; margin-left: 30px" />
									<h4 align="center">${temp.dishesName}</h4>
									<div class="col-md-offset-2" style="width: 150px; height: 20px">
										<p align="center"
											style="max-width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; margin: auto; color: #999; font-size: 10px">${temp.dishesDiscript}</p>
									</div>
								</div>
								
								
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
							</div>
					</div>
				</div>
				</div>
			</div>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="container">
					<div class="row" style="padding-top: 0px">
						<div style="width: 89%; display: inline-block;">
							<div>
								<div class="panel panel-danger">
									<div class="panel-heading">
										<i class=""></i>&nbsp;待结账餐桌信息
									</div>

									<div class="panel-body">

										<div class="table-responsive">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>桌号</th>
														<th>开餐时间</th>
														<th>结账时间</th>
														<th>总价</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody id="orderTable">
													<tr class="table_head">
														<c:forEach items="${glist}" var="temp" varStatus="state">
															<tr>
																<td>${temp.tableId}</td>
																<td>${temp.orderBeginDate}</td>
																<td>${temp.orderEndDate}</td>
																<td>${temp.money}</td>
								<!-- 								<td style="width:55px;"><a  data-target="#${temp.orderId}" data-toggle="modal" data-target=".bs-example-modal-sm"><i style="font-size:19px;color:black;" class="icon-credit-card"></i></a></td>
								 -->							
															
															

																<td style="width:55px;"><a 	
																	data-toggle="modal" data-target="#target${temp.orderId}"
																	><i style="font-size:19px;color:black;" class="icon-credit-card"></i></a>


																





																<div class="modal fade" id="target${temp.orderId}" tabindex="-1"
																	role="dialog" aria-labelledby="myModalLabel">
																	<form method="post" action="modifydishes.order">
																		<div class="modal-dialog" role="document">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close"
																						data-dismiss="modal" aria-label="Close">
																						<span aria-hidden="true">&times;</span>
																					</button>
																					<h4 class="modal-title" id="myModalLabel">详细信息</h4>
																				</div>

																				<div class="modal-body"
																					style="background-color: #F2F1F0;">
																					<div class="panel panel-danger"
																						style="margin-top: 10px">
																						<div style="width: 100%; margin: auto;">
																							<div class="row-fluid">
																								<div class="span6"
																									style="width: 30%; float: left; height: 320px;">
																									<div
																										style="background-color: #66A; padding: 15px; margin: 1px; height: 320px; box-shadow: 0 0 20px rgba(0, 100, 200, 1); color: white;">
																										<h2
																											style="color: white; font-weight: bold; font-family: '微软雅黑'">桌号1</h2>
																										<p>1</p>
																										<div class="glyphicon glyphicon-alert"
																											style="color: white; font-size: 25px;"></div>

																										<p
																											style="text-align: center; font-size: 64; margin-top: 40px">
																											<i class="icon-warning-sign icon-large"></i>
																										</p>
																									</div>
																								</div>
																								<div class="span6"
																									style="width: 70%; float: left;height:50%;">
																									<div class="row-fluid">
																										<div class="span6"
																											style="width: 50%; height: 50%; float: left">
																											<div
																												style="background-color: #CCC; padding: 15px; margin: 1px; box-shadow: 0 0 20px rgba(0, 100, 200, 1);">
																												<h2>开始时间</h2>
																												<p>12.22</p>
																												<p
																													style="text-align: center; font-size: 32; margin-top: 10px">
																													<i class=" icon-tags icon-large"></i>
																												</p>
																											</div>

																										</div>
																										<div class="span6"
																											style="width: 50%; height: 50%; float: left">

																											<div
																												style="background-color: #CF0; padding: 15px; margin: 1px; box-shadow: 0 0 20px rgba(0, 100, 200, 1);">
																												<h2>结餐时间</h2>
																												<p>12.22</p>
																												<p
																													style="text-align: center; font-size: 32; margin-top: 10px">
																													<i class=" icon-random icon-large"></i>
																												</p>
																											</div>
																										</div>
																									</div>
																									<div class="row-fluid">
																										<div class="span6"
																											style="width: 50%; float: left">

																											<div
																												style="background-color: #FA0; height: 153px; padding: 15px; margin: 1px; box-shadow: 0 0 20px rgba(0, 100, 200, 1);">
																												<h2>点餐服务员</h2>
																												<p>赖vv</p>
																											</div>
																										</div>
																										<div class="span6"
																											style="width: 50%; height: 50%; float: left">

																											<div
																												style="background-color: #DDAADD; padding: 15px; margin: 1px; height: 153px; box-shadow: 0 0 20px rgba(0, 100, 200, 1);">
																												<h2>总价</h2>
																												<p>免单</p>
																												<p
																													style="text-align: center; font-size: 32; margin-top: 10px">
																													<i class="icon-circle-arrow-right icon-large"></i>
																												</p>
																											</div>
																										</div>
																									</div>
																								</div>
																							</div>

																						</div>
																					</div>
																					<div class="panel panel-danger"
																						style="margin-top: 335px">
																						<div class="panel-heading">
																							<h3 class="panel-title">订单详情</h3>
																						</div>
																						<div class="panel-body">
																							<p>该桌的订单详情如下：</p>
																							<table class="table table-striped">
																								<thead>
																									<tr>
																										<th>菜品</th>
																										<th>单价</th>
																										<th>数量</th>
																									</tr>
																								</thead>
																								<tbody id="orderTable">
																									
																										<c:forEach items="${disheslist}" var="dishestemp" >
																	
																									 	<c:choose>
																							 		<c:when test="${temp.orderId == dishestemp.orderReference }">
																						 			<tr class="table_head">
																						 				<td>${dishestemp.dishesName }</td>
																						 				<td>${dishestemp.dishesPrice }</td>
																						 				<td>${dishestemp.num }</td>
																							 		</tr>
																							 		</c:when>
																									 	</c:choose>
																	
																								</c:forEach>	
												
																									
																								</tbody>
																							</table>
																							
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




																<td style="width:55px;"><div onclick="changeOrder(this,${temp.orderId})"><i style="font-size:19px;" class="icon-remove-sign"></i></div></td>
																<td style="width:55px;"><div onclick="cancelOrder(this,${temp.orderId})"><i style="font-size:15px;" class="icon-info-sign icon-large" data-toggle="modal" data-target="#xxf${temp.orderId}"></i></div></td>


															</td>



															</tr>
																
															
															
														</c:forEach>
												</tbody>
											</table>

											<nav>
											<ul class="pager">
												<li><a href="toadmincharge.order?page=1" id="firstpage">&larr;首页</a></li>
												<li><a href="toadmincharge.order?page=${page-1} " id="pre">上一页</a></li>
												<li><a href="toadmincharge.order?page=${page+1} " id="next">下一页</a></li>
												<li><a href="toadmincharge.order?page=-1" id="last">末页&rarr;</a></li>
											</ul>
											</nav>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>



					<div
						style="height: 1px; width: 98%; background: #CCC; margin-bottom: 10px">
					</div>
					<footer>
					<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
					</footer>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
