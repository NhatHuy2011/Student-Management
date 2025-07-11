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

.pagination {
	margin-top: 20px;
	text-align: center;
}

.pagination a {
	display: inline-block;
	margin: 0 5px;
	padding: 8px 12px;
	background-color: #007bff;
	color: white;
	border-radius: 4px;
	text-decoration: none;
}

.pagination a.disabled {
	background-color: #ccc;
	pointer-events: none;
}

.pagination a.active {
	background-color: #0056b3;
	font-weight: bold;
}
.header {
	position: relative;
	height: 40px;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-bottom: 20px;
}

.page-title {
	margin: 0;
	font-size: 24px;
	color: #333;
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
}
</style>
</head>
<body>
	<div class="header">
		<h1 class="page-title">Danh sách người dùng</h1>
		<a href="${pageContext.request.contextPath}/admin/user?action=add"
			class="add-btn">+ Thêm người dùng</a> <a
			href="${pageContext.request.contextPath}/dang-xuat?action=logout"
			class="logout-btn">Đăng xuất</a>
	</div>
	
	<!-- Form Sắp Xếp -->
	<div style="text-align: center; margin: 20px;">
		<form method="get"
			action="${pageContext.request.contextPath}/admin/user"
			style="display: inline-block;">
			<input type="hidden" name="action" value="list" /> <input
				type="hidden" name="page" value="${page.pageNumber}" /> <input
				type="hidden" name="limit" value="${page.pageSize}" /> <label
				for="sortName"><b>Sắp xếp theo:</b></label> <select name="sortName"
				id="sortName">
				<option value="id" ${sortName == 'id' ? 'selected' : ''}>ID</option>
				<option value="username" ${sortName == 'username' ? 'selected' : ''}>Tài
					khoản</option>
				<option value="fullname" ${sortName == 'fullname' ? 'selected' : ''}>Họ
					tên</option>
				<option value="email" ${sortName == 'email' ? 'selected' : ''}>Email</option>
				<option value="dob" ${sortName == 'dob' ? 'selected' : ''}>Ngày
					sinh</option>
				<option value="role" ${sortName == 'role' ? 'selected' : ''}>Vai
					trò</option>
			</select> <label for="sortBy"><b>Thứ tự:</b></label> <select name="sortBy"
				id="sortBy">
				<option value="asc" ${sortBy == 'asc' ? 'selected' : ''}>Tăng
					dần</option>
				<option value="desc" ${sortBy == 'desc' ? 'selected' : ''}>Giảm
					dần</option>
			</select>

			<button type="submit"
				style="padding: 6px 12px; background-color: #28a745; color: white; border: none; border-radius: 4px; margin-left: 10px;">Sắp
				xếp</button>
		</form>
	</div>

	<div style="text-align: center; margin-bottom: 20px;">
		<div
			style="display: inline-block; padding: 10px 20px; background-color: #3498db; color: white; font-weight: bold; border-radius: 8px;">
			Tổng số người dùng: ${page.totalElements}</div>
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
			<c:forEach var="user" items="${page.content}">
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

	<!-- Pagination -->
	<div class="pagination">
		<c:choose>
			<c:when test="${page.pageNumber > 1}">
				<a
					href="?action=list&page=${page.pageNumber - 1}&limit=${page.pageSize}&sortName=${sortName}&sortBy=${sortBy}">&laquo;
					Trước</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">&laquo; Trước</a>
			</c:otherwise>
		</c:choose>

		<c:forEach var="i" begin="1" end="${page.totalPages}">
			<c:choose>
				<c:when test="${i == page.pageNumber}">
					<a class="active">${i}</a>
				</c:when>
				<c:otherwise>
					<a
						href="?action=list&page=${i}&limit=${page.pageSize}&sortName=${sortName}&sortBy=${sortBy}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${page.pageNumber < page.totalPages}">
				<a
					href="?action=list&page=${page.pageNumber + 1}&limit=${page.pageSize}&sortName=${sortName}&sortBy=${sortBy}">Sau
					&raquo;</a>
			</c:when>
			<c:otherwise>
				<a class="disabled">Sau &raquo;</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
