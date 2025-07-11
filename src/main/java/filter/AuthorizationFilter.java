package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Constant;
import model.User;
import utils.SessionUtil;

public class AuthorizationFilter implements Filter{
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().replace(request.getContextPath(), "");
        User model = (User) SessionUtil.getInstance().getValue(request, "user");

        if (url.startsWith("/admin")) {
            if (model != null) {
                if (Constant.ROLE_ADMIN.equals(model.getRole().getRole())) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_permission");                   
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login");
            }
        } else if (url.startsWith("/user")) {
            if (model != null) {
                if (Constant.ROLE_USER.equals(model.getRole().getRole())) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_permission");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
