package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Constant;
import model.User;
import service.IUserService;
import service.impl.UserService;

@WebServlet(urlPatterns = {"/dang-nhap", "/dang-xuat"})
public class AuthController extends HttpServlet{
	private static final long serialVersionUID = -4015599647677928668L;
	
	private IUserService userService;

	public AuthController() {
		userService = new UserService(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("login")) {
				RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
				rd.forward(request, response);
			} else if (action.equals("logout")) {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login");
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			User user = userService.getByUserNameAndPassword(username, password);
			
			HttpSession session = request.getSession();
		    session.setAttribute("user", user);

			if (user != null ) {
				if(user.getRole().getRole().equals(Constant.ROLE_USER)) {
					response.sendRedirect(request.getContextPath() + "/user/info");
				} else if (user.getRole().getRole().equals(Constant.ROLE_ADMIN)){
					response.sendRedirect(request.getContextPath()+"/admin/user");
				}
			}
			else {
				request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
				RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
				rd.forward(request, response);
			}
		}
	}
}
