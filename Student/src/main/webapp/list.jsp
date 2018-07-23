<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
<link type="css/stylesheet" href="/css/index_work.css" />
</head>
<body>
	<form action="" method="post">
		姓名<input type="text" name="name"> 生日<input type="text"
			name="birthday"><input type="text" name="birthday"> <input
			type="submit" value="查询">
	</form>
	<table>
		<tr>
		<td></td>
			<td>学号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>生日</td>
		</tr>
		<c:forEach items="${list }" var="s">
			<tr>
			<td><input type="checkbox"></td>
				<td>${s.id }</td>
				<td>${s.name }</td>
				<td>${s.sex }</td>
				<td>${s.birthday }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>