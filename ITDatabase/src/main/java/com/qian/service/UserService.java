package com.qian.service;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.entity.UserEntity;
import com.qian.mapper.UserMapping;
import com.qian.util.Constants;
import com.qian.util.MD5Util;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserMapping userMapping;
	
	@Autowired
	private RedisService redisService;
	
	/*
	 * 注册
	 */
	public void regist(UserEntity user, HttpServletRequest req) {
		String username = user.getUsername();
		String email = user.getEmail();
		String phone = user.getPhone();
		String password = user.getPassword();
		if(username.equals("") || email.equals("") || phone.equals("") || password.equals("")) {
			req.setAttribute("error", "必填信息不能为空");
		}
		// 邮箱过滤
		if(!email.endsWith("@qq.com") && !email.endsWith("@163.com")) {
			req.setAttribute("error", "暂时只支持qq邮箱和163邮箱");
			return;
		}
		// 这里可以继续验证手机号是否正确
		try {
			// 使用手机号和原始密码，通过MD5算法加密成为新的密码插入数据库
			String newPassword = MD5Util.MD5(phone + password);
			user.setPassword(newPassword);
			// UUID产生用户登录Id
			user.setOpenId(UUID.randomUUID().toString());
			userMapping.addUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			// 这里可能是发现重复的用户名、邮箱或者手机号
			log.info("UserService.regist(...)，报错:", e);
			req.setAttribute("error", "用户名、邮箱或手机号重复或错误");
		}
	}
	
	/*
	 * 登录
	 */
	public String login(UserEntity userEntity, HttpServletRequest req, HttpServletResponse resp) {
		// 默认使用的是username方式登录
		String loginStr = userEntity.getUsername();
		String password = userEntity.getPassword();
		UserEntity user = null;
		try {
			user = userMapping.getUserByUsername(loginStr);
			if(user == null) {
				user = userMapping.getUserByPhone(loginStr);
				if(user == null) {
					user = userMapping.getUserByEmail(loginStr);
					if(user == null) {
						log.info("UserService/login, 账号不存在，请先注册，用户名：" + loginStr + " 密码：" + password);
						req.setAttribute("error", "账号不存在，请先注册");
						return Constants.LOGIN;
					}else {
						log.info("UserService/login, 使用email登录 : " + loginStr);
					}
				}else {
					log.info("UserService/login, 使用手机号登录 : " + loginStr);
				}
			}else {
				log.info("UserService/login, 使用用户名登录 : " + loginStr);
			}
			// 查询到用户，进行登录密码校验
			String phone = user.getPhone();
			String loginPs = MD5Util.MD5(phone + password);
			if(loginPs.equals(user.getPassword())) {
				log.info("UserService/login, 登录成功");
				// 登录成功，写Cookie和Redis
				try {
					redisService.setStr("openId", user.getOpenId(), Constants.LoginCookieTime); // 设置redis过期时间为一天
				} catch (Exception e) {
					// TODO: handle exception
					log.info("UserService/login, redis设置登录时间失败，", e);
				}
				Cookie cookie = new Cookie("openId", user.getOpenId());
				cookie.setMaxAge(Constants.LoginCookieTime); // 设置Cookie过期时间为一天
				resp.addCookie(cookie);
				return Constants.SUCCESS;
			}else {
				log.info("UserService/login, 登录失败，用户名或密码错误，用户名：" + loginStr + " 密码：" + password);
				req.setAttribute("error", "用户名或密码错误");
				return Constants.LOGIN;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("UserService/login, 账号查询失败，请先注册或重新登录", e);
			req.setAttribute("error", "账号查询失败，请先注册或重新登录");
			return Constants.LOGIN;
		}
	}
	
	/*
	 * redis查询
	 */
	public String getRedis() {
		try {
			String openId = (String) redisService.get("openId");
			return String.valueOf(openId);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("UserService/logetRedis, 获取redis失败，", e);
			return Constants.FAIL;
		}
	}
}
