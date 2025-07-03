<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Danh sách người dùng</title>
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f5f5f5;
			padding: 20px;
		}
		.logout-btn {
	        position: absolute;
	        top: 20px;
	        right: 30px;
	        padding: 10px 20px;
	        background-color: #e74c3c;
	        color: white;
	        border: none;
	        border-radius: 5px;
	        text-decoration: none;
	        font-weight: bold;
	        transition: background 0.3s;
	    }
	    .logout-btn:hover {
	        background-color: #c0392b;
	    }
		h1 {
			text-align: center;
			color: #333;
		}
		table {
			width: 100%;
			border-collapse: collapse;
			background-color: #fff;
			box-shadow: 0 0 10px rgba(0,0,0,0.1);
		}
		th, td {
			padding: 12px 15px;
			text-align: left;
			border-bottom: 1px solid #ddd;
		}
		th {
			background-color: #007bff;
			color: #fff;
		}
		tr:hover {
			background-color: #f1f1f1;
		}
		.avatar-img {
			width: 40px;
			height: 40px;
			border-radius: 50%;
			object-fit: cover;
		}
	</style>
</head>
<body>
	<!-- Nút logout -->
    <a href="${pageContext.request.contextPath}/dang-xuat?action=logout" class="logout-btn">Đăng xuất</a>
	<h1>Danh sách người dùng</h1>

	<table>
		<tr>
			<th>ID</th>
			<th>Tên đăng nhập</th>
			<th>Họ tên</th>
			<th>Email</th>
			<th>Ngày sinh</th>
			<th>Giới tính</th>
			<th>Avatar</th>
			<th>Vai trò</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.fullname}</td>
				<td>${user.email}</td>
				<td><fmt:formatDate value="${user.dob}" pattern="dd/MM/yyyy" /></td>
				<td>${user.sex}</td>
				<td>
					<c:if test="${not empty user.avatar}">
						<img class="avatar-img" src="${user.avatar}" alt="Avatar">
					</c:if>
					<c:if test="${empty user.avatar}">
						<i>Không có</i>
					</c:if>
				</td>
				<td>
					<c:choose>
						<c:when test="${not empty user.role.role}">
							${user.role.role}
						</c:when>
						<c:otherwise>
							<i>Chưa phân quyền</i>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
