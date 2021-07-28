package com.qian.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageInfoEntity {

	// 数据库字段映射
	private int id;
	private String messageName;
	private String messageUrl;
	private int messageTypeId;
	private String messagePwd;
	private String messageAuthor;
	private Timestamp created;
	private Timestamp updated;
	
	// 新添字段
	private int messageId;
	private String typeName;
}
