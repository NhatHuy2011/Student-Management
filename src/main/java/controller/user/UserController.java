package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import utils.SessionUtil;

@WebServlet(urlPatterns = {"/user/info"})
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 2250082893491074306L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) SessionUtil.getInstance().getValue(request, "user");

		if (user != null) {
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/views/user/userInfo.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login");
		}
	}
}
