package com.qian.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageTypeEntity {

	private int id;
	private String typeName;
	
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return typeName;
	}
}
