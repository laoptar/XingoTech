package com.cheny.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cheny.utils.JwtUtil;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从请求头获取 token
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			writeError(response, "缺少认证令牌");
			return false;
		}

		String token = authHeader.substring(7);

		if (!JwtUtil.validateToken(token)) {
			writeError(response, "认证令牌无效或已过期");
			return false;
		}

		// 将用户信息存入 request 属性，供后续使用
		request.setAttribute("userId", JwtUtil.getUserId(token));
		request.setAttribute("username", JwtUtil.getUsername(token));
		request.setAttribute("platform", JwtUtil.getPlatform(token));

		return true;
	}

	private void writeError(HttpServletResponse response, String message) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.write("{\"code\":401,\"msg\":\"" + message + "\"}");
		writer.flush();
		writer.close();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}
}