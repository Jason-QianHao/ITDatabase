package com.qian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.qian.entity.MessageTypeEntity;

public interface MessageTypeMapping {

	@Select("select * from `message_type` where id = #{id}")
	MessageTypeEntity getTypeById(int id);
	@Select("select * from `message_type` where type_name = #{typeName}")
	MessageTypeEntity getTypeByName(String typeName);
	@Select("select * from `message_type`")
	List<MessageTypeEntity> getTypeAll();
	@Insert("insert into `message_type` values(null, #{typeName})")
	void addType(String typeName);
}
