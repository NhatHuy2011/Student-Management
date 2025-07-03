<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin người dùng</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: #ecf0f1;
        margin: 0;
        padding: 0;
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
    .container {
        max-width: 750px;
        margin: 80px auto;
        background: #fff;
        padding: 40px;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
        position: relative;
    }
    .avatar-container {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .avatar {
        width: 130px;
        height: 130px;
        border-radius: 50%;
        object-fit: cover;
        border: 4px solid #3498db;
        margin-bottom: 10px;
    }
    h2 {
        margin-bottom: 25px;
        color: #2c3e50;
        text-align: center;
    }
    .info-table {
        width: 100%;
        border-collapse: collapse;
    }
    .info-table td {
        padding: 12px 10px;
        vertical-align: top;
        font-size: 16px;
    }
    .info-table .label {
        font-weight: bold;
        width: 160px;
        color: #34495e;
        background-color: #f9f9f9;
    }
    .info-table .value {
        color: #2c3e50;
    }
</style>
</head>
<body>

    <!-- Nút logout -->
    <a href="${pageContext.request.contextPath}/dang-xuat?action=logout" class="logout-btn">Đăng xuất</a>

    <div class="container">
        <div class="avatar-container">
            <c:choose>
                <c:when test="${not empty user.avatar}">
                    <img src="${user.avatar}" alt="Avatar" class="avatar" />
                </c:when>
                <c:otherwise>
                    <img src="https://via.placeholder.com/130x130.png?text=No+Image" alt="No Avatar" class="avatar" />
                </c:otherwise>
            </c:choose>
            <h2>${user.fullname}</h2>
        </div>

        <table class="info-table">
            <tr>
                <td class="label">Tên đăng nhập:</td>
                <td class="value">${user.username}</td>
            </tr>
            <tr>
                <td class="label">Email:</td>
                <td class="value">${user.email}</td>
            </tr>
            <tr>
                <td class="label">Ngày sinh:</td>
                <td class="value"><fmt:formatDate value="${user.dob}" pattern="dd/MM/yyyy" /></td>
            </tr>
            <tr>
                <td class="label">Giới tính:</td>
                <td class="value">${user.sex}</td>
            </tr>
            <tr>
                <td class="label">Vai trò:</td>
                <td class="value">${user.role.role}</td>
            </tr>
        </table>
    </div>
</body>
</html>
