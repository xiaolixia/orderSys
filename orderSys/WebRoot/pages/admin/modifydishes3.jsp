<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="modifydishes.order">
	<input type="hidden" value="${DISHES_INFO.dishesId }"name="dishesId" />
	<input type="hidden" value="${DISHES_INFO.recommend }"name="recommend" />
	<input type="hidden" value="${DISHES_INFO.dishesImg }"name="dishesImg" />
	<input type="hidden" value="${DISHES_INFO.dishesTxt }"name="dishesTxt" />
	菜品名称：<input type="text" value="${DISHES_INFO.dishesName }" name="dishesName"><br>
	菜品简介：<input type="text" value=${DISHES_INFO.dishesDiscript } name="dishesDiscript"><br>
	菜品价格：<input type="text" name="dishesPrice" value="${DISHES_INFO.dishesPrice }"><br>
	<input type="submit" value="修改" />
</form>
</body>
</html>