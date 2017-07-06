<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%response.setCharacterEncoding("utf-8");request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>修改菜品</title>
<meta charset=utf-8>
</head>
<body>
	<form action="modifyInfo.order?dishesId=${chosendish.dishesId}&dishesImg=${chosendish.dishesImg}&dishesTxt=${chosendish.dishesTxt}&recommend=${chosendish.recommend}" method="post">
	<table>
		
			<tr><td>序号：${chosendish.dishesId}</td></tr>
			<tr><td>菜名：<input type="text" name = "dishName" value=${chosendish.dishesName }></td></tr>
			<tr><td>价格：<input type="text" name = "dishPrice" value=${chosendish.dishesPrice }></td></tr>
			<tr><td>描述：<input type="text" name = "dishDiscription" value=${chosendish.dishesDiscript }></td></tr>
			<tr><td><input type="submit" value="修改"></input></tr></td>
		
	</table>
	</form>
</body>
</html>