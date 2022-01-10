<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	//int no =  Integer.parseInt(request.getParameter("no"));
	String no = request.getParameter("no");
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<br>
		<form action="/guestbook2/gbc" method="get">	
			비밀번호 <input type="password" name="password" value="">
			<input type="hidden" name="action" value="delete">
			<input type="text" name="no" value="<%= no %>">
			<button type="submit">확인</button>
		</form>
		<br>
		<a href="./gbc?action=addList">메인으로 돌아가기</a>
	</body>
</html>