package com.qian.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qian.entity.UserEntity;
import com.qian.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController extends BaseController{
	
	/*
	 * 首页
	 */
	@RequestMapping("/ITDatabase")
	public String ITDatabase(HttpServletRequest req) {
		// 检查cookie
		try {
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("openId")) {
					String openId = cookie.getValue();
					log.info("UserController/ITDatabase, 浏览器含有登录cookie，所登录openId: " + openId);
					return ToIndex(req, null);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("UserController/ITDatabase, 查询cookie出错，", e);
		}
		// 检查Redis
		String redis = userService.getRedis();
		if(!redis.equals(Constants.FAIL)) {
			log.info("UserController/ITDatabase, redis查询成功, openId： " + redis);
			return ToIndex(req, null);
		}
		return Constants.LOGIN;
	}
	
	/*
	 * 注册
	 */
	@RequestMapping("/regist")
	public String regist() {
		return Constants.REGIST;
	}
	@RequestMapping("/registForm")
	public String registForm(UserEntity user, HttpServletRequest req) {
		userService.regist(user, req);
		// 注册失败，返回注册页面
		if(req.getAttribute("error") != null) {
			return Constants.REGIST;
		}
		log.info("UserController/registForm log, 插入数据库成功，user" + user + " 转到登录页面");
		req.removeAttribute("error");
		return Constants.LOGIN;
	}
	
	/*
	 * 登录
	 */
	@RequestMapping("/login")
	public String login(UserEntity userEntity, HttpServletRequest req, HttpServletResponse resp) {
		String result = userService.login(userEntity, req, resp);
		if(result.equals(Constants.SUCCESS)) {
			log.info("UserController/login, 登录成功，准备跳转主页");
			return ToIndex(req, null);
		}else {
			log.info("UserController/login, 登录失败，准备跳转相应页面");
			return result;
		}
	}
	
}
