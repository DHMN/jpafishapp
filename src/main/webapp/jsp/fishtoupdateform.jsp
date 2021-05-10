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
<form action='../updatefish' method='post'>
<input type='text' name='id' value='${requestScope.fish.id }'>
<input type='text' name='breed' value='${requestScope.fish.breed }'>
<input type='text' name='weight' value='${requestScope.fish.weight }'>
<input type='submit' name='ok' value='OK'>
</form>
</body>
</html>