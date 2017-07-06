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
function myAdd(obj){
	obj.value++;
}
function myMin(obj){
	obj.value--;
}
var add=document.getElementById("next");
var del=document.getElementById("prove");
  /*add.onclick=function(){
	  addn();
  }
  del.onclick=function(){
	  deln();
  }*/
  function addn(){
	  var val=document.getElementById("aa").innerHTML;
	  
	  val++;
	  document.getElementById("aa").innerHTML=val;
  }
  function deln(){
 var val=document.getElementById("aa").innerHTML;
	  
	  val--;
	  document.getElementById("aa").innerHTML=val;
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

<body>
	<!--  title  -->
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<img src="logo.png" class="navbar-brand" /> 
		<span class="navbar-brand">中软国际-餐厅到店点餐系统</span>
		<ul class="nav navbar-nav navbar-right">
			<li><span class="navbar-brand">餐厅管理界面</span></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"	data-toggle="dropdown" >
				<img src="default.jpg" width="24" height="24"
					class="img-circle" style="border:1px solid #FFF" />
				&nbsp;&nbsp;当前用户:【admin】,身份为管理员 <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<input type="image" src="default.jpg" class="img-circle" data-toggle="modal" data-target=".bs-example-modal-sm" style="width:100px;margin-left:40px"/>		
					
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
	  <div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-credit-card icon-large"></i>&nbsp;运营功能</li>
					<li><a href="toadminmain.order"><i
							class="icon-money icon-large"></i>&nbsp; 顾客结账界面 <span
							class="sr-only">(current)</span></a></li>


				</ul>
				<ul class="nav nav-sidebar">
					<li class="nav-header"
						style="font-size: 18;margin-bottom: 10px;margin-left: 10px"><i
						class="icon-cog icon-large"></i>&nbsp;餐厅管理</li>
					<li><a href="touseradmin.order"><i
							class="icon-group icon-large"></i>&nbsp;员工管理</a></li>
					<li  class="active"><a href="todishesadmin.order"><i
							class="icon-coffee icon-large"></i>&nbsp;菜品管理</a></li>
					<li><a href="tooperatedata.order"><i
							class="icon-bar-chart icon-large"></i>&nbsp;查看经营数据 </a></li>

				</ul>
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
						<h2 class="panel-title">最新公告信息</h2>
					</div>
			<table style="text-align:left" border="0" rules="rows" width="100%"height="50px">
				<tr>
				  <td>
				  	<div>
					<button type="button" class="btn btn-default btn-sm">
    				<span class="glyphicon glyphicon-envelope"></span>
					</button>
					</div>
				  </td>
				</tr>
			</table>
				</div>
				
				<div class="panel panel-danger yuangong">

					<div class="panel-heading">
						<h2 class="panel-title">菜品清单</h2>
					</div>
			

			<table style="text-align:left" border="0" rules="rows" width="100%"height="200px">			
			<tr>
			
			
			
			
			
			
			
			<div>
            	<td><center><img src="2.jpg" alt="" class="shopping"/><div>Canon</div>
           		<div>
					<input type="button" onclick="myMin(this.nextSibling.nextSibling)"  value="-"/>
					<input type="text" value="0" style="width:22px;" disabled="disabled">
					<input type="button" onclick="myAdd(this.previousSibling.previousSibling)" value="+"/>
				</div>
           		<div><input type="button" class="btn btn-danger" value="加入点餐车" style="background-color:red"></div>
           		</center>
           		</td>
           		
           		<td><img src="2.jpg" alt=""/><div>Canon</div>
           		<div style="center">
           			<input type="button" class="reduce" id="prove" value="-" onclick="deln()">
           			<panel id="aa" >1</panel>

           			<input type="button" class="plus" id="next" value="+" onclick="addn()">
           		</div>
           		<div><input type="button" class="btn btn-danger" value="加入点餐车" style="background-color:red"></div>
           		</td>
           		
           		<td><img src="2.jpg" alt=""/><div>Canon</div>
           		<div style="center">
           			<input type="button" class="reduce" id="prove" value="-" onclick="deln()">
           			<panel id="aa" >1</panel>

           			<input type="button" class="plus" id="next" value="+" onclick="addn()">
           		</div>
           		<div><input type="button" class="btn btn-danger" value="加入点餐车" style="background-color:red"></div>
           		</td>
           		
           		<td><img src="2.jpg" alt=""/><div>Canon</div>
           		<div style="center">
           			<input type="button" class="reduce" id="prove" value="-" onclick="deln()">
           			<panel id="aa" >1</panel>

           			<input type="button" class="plus" id="next" value="+" onclick="addn()">
           		</div>
           		<div><input type="button" class="btn btn-danger" value="加入点餐车" style="background-color:red"></div>
           		</td>
           	</div>
        	</tr>
        	
        	
        	<tr>
			<div>
            	<td><img src="2.jpg" alt=""/><div>Canon</div>
           		<div style="center">
           			<input type="button" class="reduce" id="prove" value="-" onclick="deln()">
           			<panel id="aa" >1</panel>

           			<input type="button" class="plus" id="next" value="+" onclick="addn()">
           		</div>
           		<div><input type="button" class="btn btn-danger" value="加入点餐车" style="background-color:red"></div>
           		</td>
           	</div>
        	</tr>
			</table>
							<center>
							<nav class="fanye">
								<ul class="pagination">
								    <li>
								      <a href="#" aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								      </a>
								    </li>
								    <li><a href="#">1</a></li>
								    <li><a href="#">2</a></li>
								    <li><a href="#">3</a></li>
								    <li><a href="#">4</a></li>
								    <li><a href="#">5</a></li>
								    <li>
								      <a href="#" aria-label="Next">
								        <span aria-hidden="true">&raquo;</span>
								      </a>
								    </li>
								  </ul>
								<div>
									<input type="button" class="btn btn-danger" style="weight=500px" value="确认订单">
								</div>
							</nav>
							</center>
						</div>
					</div>
		
				</div>

			中国中软




<!-- Small modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">Small modal</button>





			</div>



		</div>

	</div>
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
