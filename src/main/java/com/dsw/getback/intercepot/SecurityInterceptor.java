package com.dsw.getback.intercepot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class.getName());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("SecurityInterceptor.preHandle invoke() start.");
		/*String requestUri = request.getRequestURI();
		if (requestUri.indexOf("loginCheck.do") > 0) {
			return true;
		}

		// intercept
		// 获取Session
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username != null) {
			return true;
		} else {
			ServletOutputStream out = response.getOutputStream();
			out.print("unlogin");// 返回给前端页面的未登陆标识
			out.flush();
			out.close();
		}
		// 不符合条件的，跳转到登录界面
		//request.getRequestDispatcher("/login.jsp").forward(request, response);
		logger.info("LoginInterceptor.preHandle invoke() end.");*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("SecurityInterceptor.postHandle invoke().");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("SecurityInterceptor.afterCompletion invoke().");

	}
}