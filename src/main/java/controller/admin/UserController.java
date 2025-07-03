package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.IUserService;
import service.impl.UserService;

@WebServlet(urlPatterns = {"/admin/listUsers"})
public class UserController extends HttpServlet{
	private static final long serialVersionUID = -100638648497750852L;
	
	private IUserService userService;

	public UserController() {
		userService = new UserService(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userService.getAll();
		request.setAttribute("users", users);
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listUsers.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
