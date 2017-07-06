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

<script type="text/javascript">
</script>
</head>

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
	  		<td><a href="tomodifydishes.order?dishesId=${temp.dishesId}&dishesImg=${temp.dishesImg}&dishesTxt=${temp.dishesTxt}&recommend=${temp.recommend}">修改</a></td>	
			<td><a href="todeletedishes.order?dishesId=${temp.dishesId}">删除</a></td>	
			
			</tr>
		</c:forEach>
   </table>
</body>
</html>
