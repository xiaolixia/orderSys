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
	
	
function payMoney(id,obj) {
		if (confirm("确认买单？")) {
			xmlAjaxRequest("payMoney.order?orderId=" + id + "&time="
					+ Math.random(), "get", true, null, payCallback,
					obj, null);
		}
}

function payCallback(responseTxt, obj) {

	alert("确认买单！");
	obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
	location.reload(false);
}
	
function begin() {
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
</head>

<body style="font-family: 微软雅黑" onload="begin()"><!-- getDishesList(1) -->
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
					aria-expanded="false"><img src="img/dishes/1.jpg" width="24"
						height="24" class="img-circle" style="border: 1px solid #FFF" />&nbsp;&nbsp;当前用户:【${USER_INFO.userAccount
						}】,身份为服务员
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center; padding-top: 15px"><img
							src="img/faces/${USER_INFO.faceimg }" width="128" height="128"
							class="img-circle"
							style="border: 1px solid #CCC; box-shadow: 0 0 10px rgba(100, 100, 100, 1); margin-bottom: 20px;" /></li>
						<li><a href="modifymyinfo.order">修改我的密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="toonlinewaiters.order">查看当前菜单 </a></li>
						<li><a href="toonlinekitchen.order">查看已烹饪好的菜单</a></li>
					</ul></li>
				<li><a href="logout.order">退出登录</a></li>
			</ul>



			<!-- <form class="navbar-form navbar-right" method="post"
				action="sendbord.order" target="formtarget">
				<input type="text" class="form-control" placeholder="公告"
					style="width: 300px" name="bord"><input
					class="btn btn-default" type="submit" value="发送" />
				<iframe name="formtarget" width="0" height="0" style="display: none"></iframe>
			</form> -->
		</div>
	</div>
	</nav>


	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18; margin-bottom: 10px; margin-left: 10px"><i
						class="glyphicon glyphicon-user"></i>&nbsp;服务员点餐功能</li>
					<li><a href="towaitermain.order?page=1"><i
							class="glyphicon glyphicon-glass"></i>&nbsp; 服务员点餐界面 <span
							class="sr-only">(current)</span></a></li>
					<li><a href="tooperatedata.order?page=1"><i
							class="glyphicon glyphicon-credit-card"></i>&nbsp; 餐桌结账 <span
							class="sr-only">(current)</span></a></li>
				</ul>
			</div>



			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<ol class="breadcrumb">
					<li><a href="/OrderSys/">首页</a></li>
					<li>服务员</li>
					<li class="active">买单界面</li>
				</ol>




				<div class="container">
					<div class="row" style="padding-top: 0px">
						<div style="width: 98%; display: inline-block;">
							<div>
								<div class="panel panel-danger">
									<div class="panel-heading">
										<i class="i"></i>&nbsp;最新公告消息
									</div>
									<div class="glyphicon glyphicon-envelope" style="color:#FFA500;font-size:25px;margin:25px 20px;display:inline-block;" id="msg"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row" style="padding-top: 0px">
						<div style="width: 98%; display: inline-block;">
							<div>
								<div class="panel panel-danger">
									<div class="panel-heading">
										<i class=""></i>&nbsp;顾客点餐列表
									</div>
									
									<div class="panel-body">

										<div class="table-responsive">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>桌号</th>
														<th>服务员</th>
														<th>开单时间</th>
														<th>操作</th>
													</tr>
													
													<c:forEach items="${glist}" var="temp" varStatus="state">
		  												<tr>
	  													<td>${temp.tableId}</td>
	  													<td>${temp.userAccount}</td>	
	  													<td>${temp.orderBeginDate}</td>
	  													<td><div class="btn btn-danger btn-default" onclick="payMoney('${temp.orderId}',this)">买单</div></td> 	
														</tr>
													</c:forEach>
												</thead>
												<tbody id="orderTable"></tbody>
											</table>

											<nav>
											<ul class="pager">
												<li><a href="tooperatedata.order?page=1 ">&larr;首页</a></li>
												<li><a href="tooperatedata.order?page=${page-1}">上一页</a></li>
												<li><a href="tooperatedata.order?page=${page+1}">下一页</a></li>
												<li><a href="tooperatedata.order?page=-1">末页&rarr;</a></li>
											</ul>
											</nav>

										</div>
									</div>
								</div>
							</div>
					</div>
				</div>
				
				<div style="height: 1px; width: 98%; background: #CCC; margin-bottom: 10px">
				</div>
				<footer>
				<p>&copy; ${ORDER_SYS_NAME}-中软国际ETC 2015</p>
				</footer>

			</div>
		</div>
	</div>








	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
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
								<img src="img/faces/default.jpg" id="dishesImg" width="200px"
									height="200px" class="img-circle"
									style="border: 1px solid #CCC; box-shadow: 0 0 10px rgba(100, 100, 100, 1);" />
							</div>
							<p>
							<h2 style="text-align: center;">
								菜品名称： <span id="dishesName"></span>
							</h2>
							<h3 style="text-align: center;">
								<span style="color: red; font-weight: bold;" id="recommend"></span>
							</h3>

							<hr />

							<p>
							<h3>菜品简介：</h3>
							<p>
								<span id="dishesDiscript"></span>
							</p>

							<p>
							<h3>菜品描述：</h3>

							<p>
								<span id="dishesTxt"></span>
							</p>
							<h3>
								菜品价格：<span id="dishesPrice"> </span> (元)
							</h3>



						</div>



					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
