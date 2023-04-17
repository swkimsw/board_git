<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message List</title>
<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
</head>
<body>

	<table border=1 align=center>
		<tr>
			<th colspan=3>Message List</th>
		</tr>

		<tr>
			<th>ID</th>
			<th>Writer</th>
			<th>Message</th>
		</tr>

		<c:forEach var="i" items="${list}">

			<tr>
				<td>${list.id}</td>
				<td>${list.writer}</td>
				<td>${list.messages}</td>
			</tr>

		</c:forEach>
		
		<tr>
			<td colspan=3 align=center><a href="/index.jsp"><button>Back</button></a></td>
		</tr>

	</table>




</body>
</html>