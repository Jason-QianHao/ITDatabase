package com.qian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qian.entity.MessageInfoEntity;

public interface MessageInfoMapping {

	@Select("select a.id, a.message_name, a.message_url, a.message_pwd, a.message_author, a.created, a.updated, "
			+ "b.type_name from `message_info` as a inner join `message_type` as b "
			+ "on a.message_typeId = b.id")
	List<MessageInfoEntity> getMessageAll();
	@Insert("insert into `message_info` (`message_name`, `message_url`, `message_typeId`,`message_pwd`, `message_author`) "
			+ "values(#{messageName}, #{messageUrl}, #{messageTypeId}, #{messagePwd}, #{messageAuthor})")
	void addMessage(MessageInfoEntity message);
	@Select("select * from `message_info` where message_url = #{messageUrl}")
	MessageInfoEntity getMessageByUrl(String messageUrl);
	@Delete("delete from `message_info` where message_url = #{messageUrl}")
	void deleteMessageByUrl(String messageUrl);
	@Update("update `message_info` SET message_name = #{message.messageName}, message_url = #{message.messageUrl}, "
			+ "message_typeId=#{message.messageTypeId}, message_pwd=#{message.messagePwd}, message_author=#{message.messageAuthor} "
			+ "where message_url=#{oldUrl}")
	void updateMessage(@Param("message")MessageInfoEntity message, @Param("oldUrl") String oldUrl);
}
