package com.qian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qian.entity.MessageInfoEntity;
import com.qian.entity.MessageTypeEntity;
import com.qian.mapper.MessageInfoMapping;
import com.qian.mapper.MessageTypeMapping;
import com.qian.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageService {

	@Autowired
	private MessageInfoMapping messageInfoMapping;
	@Autowired
	private MessageTypeMapping messageTypeMapping;
	/*
	 * index主页显示所有资源
	 */
	public List<MessageInfoEntity> getAllMessage(){
		try {
			List<MessageInfoEntity> messageAll = messageInfoMapping.getMessageAll();
			// 如何添加序号显示?
			return messageAll;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("MessageService/getAllMessage, 获取资源列表失败，", e);
			return null;
		}
	}
	
	/*
	 * 添加资源
	 */
	public void addMessage(MessageInfoEntity message, String messageTypeNew, HttpServletRequest req) {
		String messageName = message.getMessageName();
		int messageTypeId = message.getMessageTypeId();
		String messageUrl = message.getMessageUrl();
		String messagePwd = message.getMessagePwd();
		String messageAuthor = message.getMessageAuthor();
		// 前端已经判断过了
//		if(messageName.equals("") || messageTypeId == 0 || messageUrl.equals("") || messagePwd.equals("") 
//				|| messageAuthor.equals("")) {
//			req.setAttribute("error", "输入不能为空");
//			return;
//		}
		if(!messageTypeNew.equals("") && messageTypeId == Constants.ZIDINGYI) {
			try {
				messageTypeMapping.addType(messageTypeNew);
				message.setMessageTypeId(messageTypeMapping.getTypeByName(messageTypeNew).getId());
			} catch (Exception e) {
				// TODO: handle exception
				log.info("MessageService/addMessage, 添加资源类型失败，", e);
				req.setAttribute("error", "添加资源类型失败或已经存在！");
				return;
			}
		}
		try {
			if(messageInfoMapping.getMessageByUrl(messageUrl) != null) {
				req.setAttribute("error", "资源重复提交！");
				return;
			}
			messageInfoMapping.addMessage(message);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("MessageService/addMessage, 查询资源失败,", e);
			req.setAttribute("error", "资源提交失败");
		}
	}
	
	/*
	 * 获取所有资源类型
	 */
	public List<MessageTypeEntity> getAllType(){
		try {
			return messageTypeMapping.getTypeAll();
		} catch (Exception e) {
			// TODO: handle exception
			log.info("MessageService/getAllType, 获取资源类型列表失败, ", e);
			return null;
		}
	}
	
	/*
	 * 删除资源
	 */
	public void deleteMessageByUrl(String messageUrl, HttpServletRequest req) {
		try {
			messageInfoMapping.deleteMessageByUrl(messageUrl);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("MessageService/deleteMessageByUrl, 删除资源失败，请稍后再试," ,e);
			req.setAttribute("error", "删除资源失败，请稍后再试");
		}
	}
	

	/*
	 * 按url查询message
	 */
	public MessageInfoEntity getMessageByUrl(String url) {
		try {
			return messageInfoMapping.getMessageByUrl(url);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("MessageService/getMessageByUrl, 查询资源失败，", e);
			return null;
		}
		
	}
	
	/*
	 * 修改资源
	 */
	public void updateMessage(MessageInfoEntity message, String messageTypeNew, HttpServletRequest req) {
		String oldUrl = req.getParameter("oldUrl");
		int messageTypeId = message.getMessageTypeId();
		String messageName = message.getMessageName();
		String messageAuthor = message.getMessageAuthor();
		String messagePwd = message.getMessagePwd();
		String messageUrl = message.getMessageUrl();
		// 直接判断是否需要新添资源
		if(!messageTypeNew.equals("") && messageTypeId == Constants.ZIDINGYI) {
			try {
				messageTypeMapping.addType(messageTypeNew);
				message.setMessageTypeId(messageTypeMapping.getTypeByName(messageTypeNew).getId());
			} catch (Exception e) {
				// TODO: handle exception
				log.info("MessageService/updateMessage, 添加资源类型失败，", e);
				req.setAttribute("error", "添加资源类型失败或已经存在！");
				return;
			}
		}
		try {
			// 通过以前的url定位记录，并更新
			MessageInfoEntity messageByUrl = messageInfoMapping.getMessageByUrl(oldUrl);
			if(!messageName.equals("") || messageName != null) {
				messageByUrl.setMessageName(messageName);
			}
			if(!messageAuthor.equals("") || messageAuthor != null) {
				messageByUrl.setMessageAuthor(messageAuthor);
			}
			if(!messagePwd.equals("") || messagePwd != null) {
				messageByUrl.setMessagePwd(messagePwd);
			}
			if(!messageUrl.equals("") || messageUrl != null) {
				messageByUrl.setMessageUrl(messageUrl);
			}
			messageByUrl.setMessageTypeId(messageTypeId);
			messageInfoMapping.updateMessage(messageByUrl, oldUrl);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("MessageService/updateMessage, 更新资源失败", e);
			req.setAttribute("error", "更新资源失败");
		}
	}
}
