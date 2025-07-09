package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.IUserService;
import service.impl.UserService;

@WebServlet(urlPatterns = {"/user/info"})
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 2250082893491074306L;
	
	private IUserService userService;

	public UserController() {
		userService = new UserService(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/views/user/userInfo.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login");
		}
	}
}
