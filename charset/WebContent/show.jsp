<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<a href="<c:url value='/AServlet?username=张三'/>">点击这里</a>
<form action="<c:url value='/AServlet'/>" method="post">
用户名：<input type="text" name="username" value="李四"/>
<input type="submit" value="提交"/>
</form>
</body>
</html>