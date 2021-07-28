package com.qian.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qian.entity.MessageInfoEntity;
import com.qian.util.Constants;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class MessageController extends BaseController{
	
	/*
	 * 分页跳转
	 */
	@RequestMapping("/splitPage")
	public String splitPage(HttpServletRequest req, Integer pageNo) {
		return ToIndex(req, pageNo);
	}
	
	/*
	 * 添加资源
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("sessionToken", UUID.randomUUID().toString());
		return ToAddMessage(req);
	}
	@RequestMapping("/addMessage")
	public String addMessage(MessageInfoEntity message, String messageTypeNew, HttpServletRequest req) {
		try {
			String token = req.getParameter("token");
			HttpSession session = req.getSession();
			if(session.getAttribute("sessionToken") == null) {
				log.info("MessageController/addMessage, 重复提交");
			}else if(session.getAttribute("sessionToken").equals(token)) {
				log.info("MessageController/addMessage, 资源提交成功");
				session.removeAttribute("sessionToken");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("MessageController/addMessage, 获取token失败,", e);
			req.setAttribute("error", "获取token失败,请重新登录或重试");
			return "redirect:/add";
		}
		messageService.addMessage(message, messageTypeNew, req);
		if(req.getAttribute("error") != null) {
			return "redirect:/add";
		}
		log.info("MessageController/addMessage, 添加资源成功, 跳转主页");
		req.removeAttribute("error");
		return ToIndex(req, null);
	}
	
	/*
	 * 删除资源
	 */
	@RequestMapping("/deleteMessage")
	public String deleteMessage(HttpServletRequest req) {
		String messageUrl = req.getParameter("url");
		messageService.deleteMessageByUrl(messageUrl, req);
		if(req.getAttribute("error") != null) {
			log.info("MessageController/deleteMessage, 删除资源失败");
		}else {
			log.info("MessageController/deleteMessage, 删除资源成功， url：" + messageUrl);
		}
		return ToIndex(req, null);
	}
	
	/*
	 * 修改资源
	 */
	@RequestMapping("/updateMessage")
	public String updateMessage(HttpServletRequest req) {
		String messageUrl = req.getParameter("url");
		MessageInfoEntity messageByUrl = messageService.getMessageByUrl(messageUrl);
		if(messageByUrl == null) {
			req.setAttribute("error", "更新资源失败");
			return ToIndex(req, null);
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("sessionToken", UUID.randomUUID().toString());
			req.setAttribute("au", messageByUrl);
		}
		return ToEditMessage(req);
	}
	@RequestMapping("/update")
	public String update(MessageInfoEntity message, String messageTypeNew, HttpServletRequest req) {
		try {
			String token = req.getParameter("token");
			HttpSession session = req.getSession();
			if(session.getAttribute("sessionToken") == null) {
				log.info("MessageController/update, 重复提交");
			}else if(session.getAttribute("sessionToken").equals(token)) {
				log.info("MessageController/update, 资源提交成功");
				session.removeAttribute("sessionToken");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("MessageController/update, 获取token失败,", e);
			req.setAttribute("error", "获取token失败,请重新登录或重试");
			return "redirect:/updateMessage";
		}
		messageService.updateMessage(message, messageTypeNew, req);
		if(req.getAttribute("error") != null) {
			return "redirect:/updateMessage";
		}
		log.info("MessageController/update, 修改资源成功, 跳转主页");
		req.removeAttribute("error");
		return ToIndex(req, null);
	}
}
