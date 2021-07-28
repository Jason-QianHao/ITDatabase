package com.qian.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/*
	 * 设置或添加字符串
	 */
	public void setStr(String key, String value) {
		setStr(key, value, null);
	}

	public void setStr(String key, String value, Integer time) {
		stringRedisTemplate.opsForValue().set(key, value);
		if (time != null) {
			stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	/*
	 * 查询
	 */
	public Object get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/*
	 * 删除
	 */
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}
}
