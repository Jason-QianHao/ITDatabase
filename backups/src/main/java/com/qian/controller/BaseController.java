package com.qian.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qian.entity.MessageInfoEntity;
import com.qian.entity.MessageTypeEntity;
import com.qian.service.MessageService;
import com.qian.service.UserService;
import com.qian.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

	@Autowired
	protected MessageService messageService;
	
	@Autowired
	protected UserService userService;
	
	/*
	 * 跳转主页
	 */
	public String ToIndex(HttpServletRequest req, Integer indexPage) {
		if(indexPage == null) {
			indexPage = 1;
		}
		
		// 开始分页，pageNo=当前页数， pageSize=每页显示几条
        PageHelper.startPage(indexPage, Constants.PAGESIZE);
		// 封装数据库已有信息,必须在PageHelper后，原理涉及threadlocal
		List<MessageInfoEntity> allMessage = messageService.getAllMessage();
		req.setAttribute("listResource", allMessage);
        // 设置当前页的数据
        PageInfo<MessageInfoEntity> pageInfo = new PageInfo<MessageInfoEntity>(allMessage);
        req.setAttribute("pageInfo", pageInfo);
        List<Integer> pageNumList = new ArrayList<Integer>();
        for(int i = 1; i < pageInfo.getPages(); i++) {
        	pageNumList.add(i);
        }
        req.setAttribute("pageNumList", pageNumList);
		return Constants.INDEX;
	}
	
	/*
	 * 跳转添加资源页面
	 */
	public String ToAddMessage(HttpServletRequest req) {
		List<MessageTypeEntity> allType = messageService.getAllType();
		log.info("BaseController/ToAddMessage, 资源类型有：" + allType.toString());
		req.setAttribute("listType", allType);
		return Constants.ADDMESSAGE;
	}
	
	/*
	 * 跳转修改资源页面
	 */
	public String ToEditMessage(HttpServletRequest req) {
		List<MessageTypeEntity> allType = messageService.getAllType();
		log.info("BaseController/ToEditMessage, 资源类型有：" + allType.toString());
		req.setAttribute("listType", allType);
		return Constants.EDITMESSAGE;
	}
}
