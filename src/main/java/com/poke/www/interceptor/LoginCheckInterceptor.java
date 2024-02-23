package com.poke.www.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestURI = request.getRequestURI();

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginMember") == null) {

			// 로그인으로 redirect
			response.sendRedirect("/login?rd-url=" + requestURI);
			return false;
		}
		return true;
	}

}
