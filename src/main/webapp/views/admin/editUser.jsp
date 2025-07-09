<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa người dùng</title>
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
            padding: 35px 40px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }
        .avatar-preview {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        .avatar-preview img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid #ccc;
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
            border-color: #ffc107;
            outline: none;
        }
        .btn {
            margin-top: 10px;
            width: 100%;
            padding: 12px;
            background-color: #ffc107;
            color: white;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.2s;
        }
        .btn:hover {
            background-color: #e0a800;
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
    <h2>Chỉnh sửa người dùng</h2>

    <c:if test="${not empty user.avatar}">
        <div class="avatar-preview">
            <img src="${user.avatar}" alt="Avatar của người dùng" />
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/admin/user" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="edit" />
        <input type="hidden" name="id" value="${user.id}" />

        <!-- Nếu không upload ảnh mới, giữ nguyên avatar cũ -->
        <input type="hidden" name="avatar" value="${user.avatar}" />
        <input type="hidden" name="publicId" value="${user.publicId}" />

        <div class="form-group">
            <label for="username">Tài khoản:</label>
            <input type="text" name="username" id="username" value="${user.username}" required />
        </div>

        <div class="form-group">
            <label for="password">Mật khẩu:</label>
            <input type="password" name="password" id="password" value="${user.password}" required />
        </div>

        <div class="form-group">
            <label for="fullname">Họ tên:</label>
            <input type="text" name="fullname" id="fullname" value="${user.fullname}" required />
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" value="${user.email}" required />
        </div>

        <div class="form-group">
            <label for="dob">Ngày sinh:</label>
            <input type="date" name="dob" id="dob"
                   value="<fmt:formatDate value='${user.dob}' pattern='yyyy-MM-dd' />" required />
        </div>

        <div class="form-group">
            <label for="sex">Giới tính:</label>
            <select name="sex" id="sex">
                <option value="MALE" <c:if test="${user.sex == 'MALE'}">selected</c:if>>Nam</option>
                <option value="FEMALE" <c:if test="${user.sex == 'FEMALE'}">selected</c:if>>Nữ</option>
            </select>
        </div>

        <div class="form-group">
            <label for="avatarFile">Ảnh đại diện mới (nếu có):</label>
            <input type="file" name="avatarFile" id="avatarFile" accept="image/*" />
        </div>

        <div class="form-group">
            <label for="role">Vai trò:</label>
            <select name="role" id="role">
                <option value="USER" <c:if test="${user.role.role == 'USER'}">selected</c:if>>USER</option>
                <option value="ADMIN" <c:if test="${user.role.role == 'ADMIN'}">selected</c:if>>ADMIN</option>
            </select>
        </div>

        <button type="submit" class="btn">Cập nhật</button>
    </form>
    <a href="${pageContext.request.contextPath}/admin/user?action=list" class="back-link">← Quay lại danh sách</a>
</div>
</body>
</html>
