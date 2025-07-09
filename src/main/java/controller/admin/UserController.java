package controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import enums.Sex;
import model.Role;
import model.User;
import service.IUserService;
import service.impl.UserService;
import utils.CloudinaryUtils;

@WebServlet(urlPatterns = {"/admin/user"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = -7964089221236897178L;
    
    private IUserService userService;
    
    private CloudinaryUtils cloudinaryUtils;
    
    @Override
    public void init() throws ServletException {
        userService = new UserService();
        cloudinaryUtils = new CloudinaryUtils();
    }

    @Override
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
            int totalUser = userService.count();
                        
            request.setAttribute("users", users);
            request.setAttribute("total", totalUser);
            
            request.getRequestDispatcher("/views/admin/listUsers.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!ServletFileUpload.isMultipartContent(request)) {
                throw new RuntimeException("Form must have enctype=multipart/form-data");
            }

            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            upload.setHeaderEncoding("UTF-8");
            List<FileItem> formItems = upload.parseRequest(request);
            
            User user = getUserFromRequest(formItems);
            String action = null;

            for (FileItem item : formItems) {
                if (item.isFormField() && "action".equals(item.getFieldName())) {
                    action = item.getString("UTF-8");
                    break;
                }
            }

            if ("add".equals(action)) {
                userService.insert(user);
            } else if ("edit".equals(action)) {
                userService.update(user);
            }

            response.sendRedirect(request.getContextPath() + "/admin/user?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("/views/admin/error.jsp").forward(request, response);
        }
    }
    
    private User getUserFromRequest(List<FileItem> formItems) throws Exception {
        User user = new User();
        String oldAvatar = null;
        String oldPublicId = null;       

        for (FileItem item : formItems) {
        	//item.isFormField() - item trường văn bản
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String value = item.getString("UTF-8");
                
                switch (fieldName) {
                    case "username": 
                    	user.setUsername(value); 
                    	break;
                    case "password": 
                    	user.setPassword(value); 
                    	break;
                    case "fullname": 
                    	user.setFullname(value); 
                    	break;
                    case "email": 
                    	user.setEmail(value); 
                    	break;
                    case "dob":
                        if (!value.isEmpty()) 
                        	user.setDob(Date.valueOf(value));
                        break;
                    case "sex":
                        if (!value.isEmpty()) 
                        	user.setSex(Sex.valueOf(value)); 
                        break;
                    case "role":
                        Role role = new Role(); 
                        role.setRole(value);
                        user.setRole(role); 
                        break;
                    case "avatar": 
                    	oldAvatar = value; 
                    	break;
                    case "publicId": 
                    	oldPublicId = value; 
                    	break;
                    case "id":
                        if (!value.isEmpty()) 
                        	user.setId(Integer.parseInt(value)); 
                        break;
                }
            }
        }
       
        for (FileItem item : formItems) {
            String fieldName = item.getFieldName();

            if (!item.isFormField() && "avatarFile".equals(fieldName) && item.getSize() > 0) {
                String fileName = FilenameUtils.getName(item.getName());
                InputStream inputStream = item.getInputStream();

                // Xoá ảnh cũ trước khi upload
                if (oldPublicId != null && !oldPublicId.isEmpty()) {
                    cloudinaryUtils.deleteFile(oldPublicId);
                }

                Map<String, String> result = cloudinaryUtils.uploadFileStream(inputStream, fileName);
                user.setAvatar(result.get("secure_url"));
                user.setPublicId(result.get("public_id"));
            }
        }
        
        if (user.getAvatar() == null) {
            user.setAvatar(oldAvatar);
            user.setPublicId(oldPublicId);
        }

        return user;
    }
}
