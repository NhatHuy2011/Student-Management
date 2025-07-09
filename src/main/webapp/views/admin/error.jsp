<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lỗi</title>
</head>
<body>
    <h2 style="color: red;">Đã xảy ra lỗi!</h2>
    <p><strong>Chi tiết:</strong> ${errorMessage}</p>
    <a href="${pageContext.request.contextPath}/admin/user?action=list">Quay lại danh sách người dùng</a>
</body>
</html>
