package com.qian.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {

	private int id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String openId;
	private Timestamp created;
	private Timestamp updated;
	
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return username + " " + phone + " " + email;
	}
}
