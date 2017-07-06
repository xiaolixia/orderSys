<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<%
//加载驱动程序
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//建立连接
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ordersys","root","root");
Statement stmt=conn.createStatement();
try{
	ResultSet rs;
	rs=stmt.executeQuery("SELECT *FROM dishesinfo where recommend='1'");
%>
<table border=3>
<tr bgcolor=orange><b>
<td>dishesId</td><td>dishesName</td><td>dishesDscript</td>

<td>dishesImg</td><td>dishesTxt</td><td>dishesPrice</td>
</b>
</tr>
<%
while (rs.next()){
%>
<tr>
<td><%=rs.getString("dishesId")%></td>
<td><%=rs.getString("dishesName") %></td>
<td><%=rs.getString("dishesDscript")%></td>
<td><%=rs.getString("dishesImg")%></td>
<td><%=rs.getString("dishesTxt") %></td>
<td><%=rs.getString("dishesPrice") %></td>
</tr>
<% 
}
rs.close();
}
catch(Exception e){
out.println(e.getMessage());
}
stmt.close();
conn.close();
%>
</table>
</center>
</body>
</html>