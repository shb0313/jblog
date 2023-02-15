package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Handler Method의 @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		if (auth == null) {
			return true;
		}

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");

			return false;
		}

		String url = request.getServletPath();
		String[] id = url.split("/");

		// 7. 권한(Authorization) 체크를 위해 !Auth의 role 가져오기("ADMIN", "USER")
		if (auth != null) {
			String role = auth.role().toString();
			if ("ADMIN".equals(role)) {
				if (!id[1].equals(authUser.getId())) {
					response.sendRedirect(request.getContextPath());
					return false;
				}
			}
		}

		return true;
	}

}
