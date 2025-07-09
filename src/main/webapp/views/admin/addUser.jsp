<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm người dùng</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: #f0f2f5;
	padding: 40px;
}

.form-container {
	max-width: 550px;
	margin: auto;
	background: #fff;
	padding: 30px 35px;
	border-radius: 12px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	margin-bottom: 25px;
	color: #333;
}

.form-group {
	display: flex;
	flex-direction: column;
	margin-bottom: 18px;
}

label {
	margin-bottom: 6px;
	font-weight: 600;
	color: #333;
}

input, select {
	padding: 10px 12px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 15px;
}

input:focus, select:focus {
	border-color: #007bff;
	outline: none;
}

.btn {
	margin-top: 10px;
	width: 100%;
	padding: 12px;
	background-color: #007bff;
	color: white;
	font-size: 16px;
	font-weight: bold;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: background-color 0.2s;
}

.btn:hover {
	background-color: #0056b3;
}

.back-link {
	display: block;
	margin-top: 20px;
	text-align: center;
	color: #007bff;
	text-decoration: none;
	font-size: 14px;
}

.back-link:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="form-container">
		<h2>Thêm người dùng</h2>
		<form action="${pageContext.request.contextPath}/admin/user"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="action" value="add" />

			<div class="form-group">
				<label for="username">Tài khoản:</label> <input type="text"
					name="username" id="username" required />
			</div>

			<div class="form-group">
				<label for="password">Mật khẩu:</label> <input type="password"
					name="password" id="password" required />
			</div>

			<div class="form-group">
				<label for="fullname">Họ tên:</label> <input type="text"
					name="fullname" id="fullname" required />
			</div>

			<div class="form-group">
				<label for="email">Email:</label> <input type="email" name="email"
					id="email" required />
			</div>

			<div class="form-group">
				<label for="dob">Ngày sinh:</label> <input type="date" name="dob"
					id="dob" required />
			</div>

			<div class="form-group">
				<label for="sex">Giới tính:</label> <select name="sex" id="sex">
					<option value="MALE">Nam</option>
					<option value="FEMALE">Nữ</option>
				</select>
			</div>

			<div class="form-group">
				<label>Avatar:</label> <input type="file" name="avatarFile"
					accept="image/*" />
			</div>

			<div class="form-group">
				<label for="role">Vai trò:</label> <select name="role" id="role">
					<option value="USER">USER</option>
					<option value="ADMIN">ADMIN</option>
				</select>
			</div>

			<button type="submit" class="btn">Thêm mới</button>
		</form>
		<a href="${pageContext.request.contextPath}/admin/user?action=list"
			class="back-link">← Quay lại danh sách</a>
	</div>
</body>
</html>
