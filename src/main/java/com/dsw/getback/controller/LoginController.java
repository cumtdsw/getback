package com.dsw.getback.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsw.getback.domain.Users;
import com.dsw.getback.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	protected UserService userService;

	// http://localhost:8080/getback/login/loginCheck.do
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String loginCheck(HttpServletRequest request) {
		logger.info("loginCheck start invoke..");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean isValidUser = userService.hasMatchUser(username, password);
		String result = "";
		if (!isValidUser) {
			result = "FAILD";
		} else {
			Users user = userService.findUserByUserName(username);
			user.setLastIp(request.getLocalAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			request.getSession().setAttribute("username", user.getUsername());
			result = "SUCCESS";
		}
		logger.info("loginCheck end invoke..");
		return result;
	}

	// http://localhost:8080/getback/login/getSession.do
	@RequestMapping(value = "/getSession.do", method = RequestMethod.GET)
	@ResponseBody
	public String getSession(HttpServletRequest request) {
		logger.info("getSession start invoke..");
		String result = "";
		if (null != request.getSession().getAttribute("username")) {
			result = request.getSession().getAttribute("username").toString();
		} 
		logger.info("getSession end invoke..");
		return result;
	}

	// http://localhost:8080/getback/login/getSession.html
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	@ResponseBody
	public void logout(HttpServletRequest request) {
		logger.info("logout start invoke..");
		request.getSession().setAttribute("username", null);
		logger.info("logout end invoke..");
	}

}
