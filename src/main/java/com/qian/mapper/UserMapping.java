package com.qian.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qian.entity.UserEntity;

public interface UserMapping {

	@Insert("insert into `users` (`username`, `password`, `phone`, `email`, `openId`) values(#{username}, #{password}, #{phone}, #{email}, "
			+ "#{openId})")
	void addUser(UserEntity user);
	@Select("select * from `users` where openId = #{openId}")
	UserEntity getUserByOpenId(@Param("openId") String openId);
	@Select("select * from `users` where username = #{username}")
	UserEntity getUserByUsername(@Param("username") String username);
	@Select("select * from `users` where phone = #{phone}")
	UserEntity getUserByPhone(@Param("phone") String phone);
	@Select("select * from `users` where email = #{email}")
	UserEntity getUserByEmail(String email);
}
