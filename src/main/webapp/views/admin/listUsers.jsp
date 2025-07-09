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

.logout-btn, .add-btn {
	position: absolute;
	top: 20px;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	color: white;
	font-weight: bold;
	text-decoration: none;
}

.logout-btn {
	right: 30px;
	background-color: #e74c3c;
}

.add-btn {
	right: 150px;
	background-color: #3498db;
}

.logout-btn:hover {
	background-color: #c0392b;
}

.add-btn:hover {
	background-color: #2980b9;
}

h1 {
	text-align: center;
	color: #333;
}

table {
	width: 100%;
	border-collapse: collapse;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 12px;
	border-bottom: 1px solid #ddd;
	text-align: left;
}

th {
	background-color: #007bff;
	color: #fff;
}

.avatar-img {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	object-fit: cover;
}

.action-btn {
	margin-right: 8px;
	padding: 6px 10px;
	border-radius: 4px;
	font-size: 14px;
	text-decoration: none;
	color: white;
}

.edit-btn {
	background-color: #f39c12;
}

.edit-btn:hover {
	background-color: #e67e22;
}

.delete-btn {
	background-color: #e74c3c;
}

.delete-btn:hover {
	background-color: #c0392b;
}
</style>
</head>
<body>
	<a href="${pageContext.request.contextPath}/dang-xuat?action=logout"
		class="logout-btn">Đăng xuất</a>
	<a href="${pageContext.request.contextPath}/admin/user?action=add"
		class="add-btn">+ Thêm người dùng</a>

	<h1>Danh sách người dùng</h1>
	<div style="text-align: center; margin-bottom: 20px;">
		<div
			style="display: inline-block; padding: 10px 20px; background-color: #3498db; color: white; font-weight: bold; border-radius: 8px;">
			Tổng số người dùng: ${total}</div>
	</div>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Tài khoản</th>
				<th>Họ tên</th>
				<th>Email</th>
				<th>Ngày sinh</th>
				<th>Giới tính</th>
				<th>Avatar</th>
				<th>Vai trò</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>${user.fullname}</td>
					<td>${user.email}</td>
					<td><fmt:formatDate value="${user.dob}" pattern="dd/MM/yyyy" /></td>
					<td>${user.sex}</td>
					<td><c:choose>
							<c:when test="${not empty user.avatar}">
								<img class="avatar-img" src="${user.avatar}" />
							</c:when>
							<c:otherwise>
								<i>Không có</i>
							</c:otherwise>
						</c:choose></td>
					<td>${user.role.role}</td>
					<td><a class="action-btn edit-btn"
						href="${pageContext.request.contextPath}/admin/user?action=edit&id=${user.id}">Sửa</a>
						<a class="action-btn delete-btn"
						href="${pageContext.request.contextPath}/admin/user?action=delete&id=${user.id}"
						onclick="return confirm('Xác nhận xoá?');">Xoá</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
