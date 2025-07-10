<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<style>
	* {
		box-sizing: border-box;
	}
	
	body {
		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
		background: linear-gradient(to right, #6dd5ed, #2193b0);
		margin: 0;
		padding: 0;
		height: 100vh;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.login-container {
		background-color: white;
		padding: 40px 30px;
		border-radius: 10px;
		box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
		width: 100%;
		max-width: 380px;
		text-align: center;
	}
	
	.login-container h2 {
		margin-bottom: 30px;
		color: #333;
		font-size: 24px;
	}
	
	input[type="text"], input[type="password"] {
		width: 100%;
		padding: 12px;
		margin-bottom: 20px;
		border: 1px solid #ccc;
		border-radius: 6px;
		font-size: 14px;
	}
	
	button {
		width: 100%;
		padding: 12px;
		background-color: #007bff;
		color: white;
		border: none;
		border-radius: 6px;
		font-size: 16px;
		font-weight: bold;
		cursor: pointer;
		transition: background-color 0.3s ease;
	}
	
	button:hover {
		background-color: #0056b3;
	}
	
	.error-message {
		color: #e74c3c;
		font-size: 14px;
		margin-top: 15px;
	}
	
	.alert-message {
	    position: fixed;
	    top: 20px;
	    right: 20px;
	    background-color: #f44336;
	    color: white;
	    padding: 12px 20px;
	    border-radius: 8px;
	    font-size: 14px;
	    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
	    z-index: 1000;
	    animation: slideDown 0.5s ease;
	}

	@keyframes slideDown {
	    from {
	        opacity: 0;
	        transform: translateY(-20%);
	    }
	    to {
	        opacity: 1;
	        transform: translateY(0);
	    }
	}
		
	@media ( max-width : 480px) {
		.login-container {
			padding: 30px 20px;
		}
	}
</style>
</head>
<body>
	<div class="login-container">
		<h2>Đăng Nhập Tài Khoản</h2>
		<form action="${pageContext.request.contextPath}/dang-nhap"
			method="post">
			<input type="hidden" name="action" value="login" /> <input
				type="text" name="username" placeholder="Tên đăng nhập" required />
			<input type="password" name="password" placeholder="Mật khẩu"
				required />
			<button type="submit">Đăng nhập</button>
		</form>

		<div class="error-message">
			<%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
		</div>
	</div>
	<%
	String message = request.getParameter("message");
	String alertMessage = "";
	if ("not_login".equals(message)) {
		alertMessage = "Vui lòng đăng nhập để tiếp tục.";
	} else if ("not_permission".equals(message)) {
		alertMessage = "Bạn không có quyền truy cập trang này.";
	} 
	%>

	<% if (!alertMessage.isEmpty()) {%>
		<div class="alert-message"><%=alertMessage%></div>
	<% } %>

	<script>
	    setTimeout(() => {
	        const alert = document.querySelector('.alert-message');
	        if (alert) alert.style.display = 'none';
	    }, 4000); // Tự ẩn sau 4 giây
	</script>
</body>
</html>
