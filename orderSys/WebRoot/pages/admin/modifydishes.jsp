<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/dashboard.css">


<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="bootstrap/js/jquery-1.11.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
function begin() {

	var input = document.getElementById("inputfile");
	if (input.value != "") {
		var preview = document.getElementById("face");
		preview.src = "img/loading.gif";
		imgform.submit();
		request();
	}

}

function viewImg(file) {
	var preview = document
			.getElementById('face');
	if (file.files && file.files[0]) {
		//火狐下
		// preview.style.display = "block";
		//preview.style.width = "300px";
		//preview.style.height = "120px";
		preview.src = window.URL.createObjectURL(file.files[0]);
		//preview.src = window.URL.createObjectURL(preview.value);
	} else {
		//ie下，使用滤镜
		file.select();
		var imgSrc = document.selection
				.createRange().text;
		var localImagId = document
				.getElementById("localImag");
		//必须设置初始大小 
		//localImagId.style.width = "250px";
		// localImagId.style.height = "200px";
		try {
			localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			locem("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		} catch (e) {
			alert("您上传的图片格式不正确，请重新选择!");
			return false;
		}
		// preview.style.display = 'none';
		document.selection.empty();
	}
	return true;
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

<body style="font-family: 微软雅黑" onload="getDishesList(1)">
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
						class="img-circle" style="border: 1px solid #FFF" />&nbsp;&nbsp;当前用户:【${USER_INFO.userAccount
						}】,身份为管理员
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li style="text-align: center; padding-top: 15px"><img
							src="img/faces/${USER_INFO.faceimg }" width="128" height="128"
							class="img-circle"
							style="border: 1px solid #CCC; box-shadow: 0 0 10px rgba(100, 100, 100, 1); margin-bottom: 20px" /></li>
						<li><a href="modifymyinfo.order">修改我的密码</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="toonlinewaiters.order">查看当前在线的厨师 </a></li>
						<li><a href="toonlinekitchen.order">查看当前在线的点餐员</a></li>


					</ul></li>
				<li><a href="logout.order">退出登录</a></li>
			</ul>



			<form class="navbar-form navbar-right" method="post"
				action="sendbord.order" target="formtarget">
				<input type="text" class="form-control" placeholder="公告"
					style="width: 300px" name="bord"><input
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
						style="font-size: 18; margin-bottom: 10px; margin-left: 10px"><i
						class="icon-credit-card icon-large"></i>&nbsp;运营功能</li>
					<li><a href="toadminmain.order"><i
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
					<li class="active">菜品管理界面</li>
				</ol>




				<div class="container">
					<div class="row" style="padding-top: 0px">
						<div style="width: 50%; display: inline-block;">
							<div>

								<h2>请填写菜品信息：</h2>
								<p>请在下面的表单中填写新增加的菜品名称、价格等描述信息</p>


								<div class="panel panel-danger">
									<div class="panel-heading">
										<i class="icon-warning-sign"></i>&nbsp;注意事项
									</div>
									<div class="panel-body">请正确填写菜品描述信息，菜品名称不要超过20个字符，菜品简介不要超过200个字符，菜品详细描述不要超过400个字符，菜品价格请输入正确的数字格式</div>
								</div>

								<p>
								<form class="form-horizontal" role="form"
									style="margin-top: 20px" method="post"
									action="modifydishes.order">
									<input type="hidden" name="dishesId" id="dishesId"
										value="${DISHES_INFO.dishesId}" />
									<div class="form-group">
										<label for="firstname" class="col-sm-2 control-label">名称：</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="firstname"
												name="dishesName" placeholder="请输入菜品名称"
												value="${DISHES_INFO.dishesName}">
										</div>


									</div>

									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">简介:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="userOldPass"
												name="dishesDiscript" placeholder="请输入少于100字的简介"
												onblur="checkoldpass()">
										</div>
										<div style="color: red; float: right;" id="usererror"></div>
									</div>

									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">说明:</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="userOldPass"
												name="dishesTxt" onblur="checkoldpass()">
									
								</textarea>
										</div>
										<div style="color: red; float: right;" id="usererror"></div>
									</div>

									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">价格:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="userOldPass"
												name="dishesPrice" placeholder="请输入菜品价格"
												onblur="checkoldpass()">
										</div>
										<div style="color: red; float: right;" id="usererror"></div>
									</div class="form-group">
									<label for="lastname" class="col-sm-2 control-label">推荐菜品


									</label>
									<div class="col-sm-10">
										<input type="checkbox" id="userOldPass" name="recommend"
											onblur="checkoldpass()" value="">
									</div>
									<div></div>


									<input type="hidden" value="${DISHES_INFO.dishesImg}"
										id="faceimgname" name="dishesImg" />

									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<input type="submit" class="btn btn-danger"
												 id="addbtu" value="添加菜品" />
										</div>
									</div>
								<!-- </form> -->
							</div>

						</div>

						<div
							style="width: 5%; display: inline-block; padding-top: 0px; margin-top: 0px; vertical-align: top; padding-left: 20px">
							<div
								style="background-color: #CCC; width: 1px; height: 580px; margin-top: 0px"></div>
						</div>


						<div
							style="width: 40%; display: inline-block; vertical-align: top;">
							<h2>设置图片：</h2>
							<p style="margin-bottom: 10px">菜品图片上传成功后将在下面直接预览</p>
							<div style="text-align: center;" id="localImag">
								<img src="img/faces/${DISHES_INFO.dishesImg}" id="face"
									width="200px" height="200px" class="img-circle"
									style="border: 1px solid #CCC; box-shadow: 0 0 10px rgba(100, 100, 100, 1);" name="dishesImg" />
								<p style="margin-top: 15px">当前菜品图像预览</p>
								<p style="margin-top: 15px">
									为菜品指定新的图片，请选择图片文件后，点击<span
										style="color: red; font-weight: bold;">上传图片</span>按钮<br />
								</p>
								<div>

									<!-- <form class="form-inline" role="form"
										enctype="multipart/form-data" action="upimg.order"
										name="imgform" target="submitform" method="post">
 -->
										<div class="form-group">
											<label class="sr-only" for="inputfile">文件输入</label> <input
												type="file" id="inputfile" name="dishesImg" onchange="viewImg(this)">
										</div>

										<input type="button" class="btn btn-danger" value="上传图片"
											onclick="begin()" />
									</form>
								</div>


							</div>
						</div>
					</div>
				</div>










				<div
					style="height: 1px; width: 100%; background: #CCC; margin-bottom: 10px"></div>
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
								<img src="img/faces/${DISHES_INFO.dishesImg}" id="dishesImg" width="200px"
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
