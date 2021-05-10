<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action='../addfish' method='post'>
<input type='text' name='breed' value=''>
<input type='text' name='weight' value=''>
<input type='submit' name='ok' value='OK'>
</form>
<ol>
<c:forEach var="fish" items="${requestScope.fishlist }">
	<li>${fish} <a href='../deletefish?id=${fish.id}'>Delete</a> <a href='../readtoupdatefish?id=${fish.id}'>Update</a>
</c:forEach>
</ol>
</body>
</html>