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
				<td>${i.id}</td>
				<td>${i.writer}</td>
				<td>${i.message}</td>
			</tr>

		</c:forEach>

		<form action="/update.message">
			<tr align="center">
				<td colspan=3>
					<input type="text" placeholder="수정할 대상 ID"name="modId"><br> 
					<input type="text" placeholder="이름..." name="modWriter"><br> 
					<input type="text" placeholder="메세지..." name="modMessages"><br>
					<button>수정</button>
				</td>
			</tr>
		</form>

		<tr>
			<td colspan=3 align="center">
				<input type="text" placeholder="삭제할 아이디..." name="delId" id="delId">
				<button id="delete">삭제</button>
			</td>
		</tr>



		<tr>
			<td colspan=3 align=center><a href="/index.jsp"><button>Back</button></a></td>
		</tr>

	</table>

	<script>
		$("#delete").on("click", function() {
			let delId = $("#delId").val();
			location.href = "/delete.message?delId=" + delId;
		});
	</script>





</body>
</html>