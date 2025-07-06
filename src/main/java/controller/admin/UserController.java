package controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.Sex;
import model.Role;
import model.User;
import service.IUserService;
import service.impl.UserService;

@WebServlet(urlPatterns = {"/admin/user"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = -7964089221236897178L;
	
	private IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.getOne(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/views/admin/editUser.jsp").forward(request, response);

        } else if ("add".equals(action)) {
            request.getRequestDispatcher("/views/admin/addUser.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userService.delete(id);
            response.sendRedirect(request.getContextPath() + "/admin/user?action=list");

        } else {
            List<User> users = userService.getAll();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/views/admin/listUsers.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            User user = getUserFromRequest(request);
            
            System.out.print(request.getParameter("role"));
            
            userService.insert(user);
            response.sendRedirect(request.getContextPath() + "/admin/user?action=list");

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = getUserFromRequest(request);
            user.setId(id);
            userService.update(user);
            response.sendRedirect(request.getContextPath() + "/admin/user?action=list");
        }
    }
    
	private User getUserFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFullname(request.getParameter("fullname"));
        user.setEmail(request.getParameter("email"));

        String dob = request.getParameter("dob");
        if (dob != null && !dob.isEmpty()) {
            user.setDob(Date.valueOf(dob));
        }

        String sex = request.getParameter("sex");
        if (sex != null) {
            user.setSex(Sex.valueOf(sex)); 
        }

        user.setAvatar(request.getParameter("avatar"));

        String roleParameter = request.getParameter("role");
        if (roleParameter != null) {
            Role role = new Role();
            role.setRole(roleParameter);
            user.setRole(role);
        }

        return user;
    }

}

