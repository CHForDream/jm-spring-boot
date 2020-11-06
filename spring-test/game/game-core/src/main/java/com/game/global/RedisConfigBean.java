package com.game.global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class RedisConfigBean {
	@Value("${game.service.redis.host}")
	private String serviceRedisHost = "";
	@Value("${game.service.redis.port}")
	private int serviceRedisPort = 0;
	@Value("${game.service.redis.password}")
	private String serviceRedisPassword = "";
	@Value("${game.service.redis.db}")
	private int serviceRedisDb = 0;

	@Value("${game.home.redis.host}")
	private String homeRedisHost = "";
	@Value("${game.home.redis.port}")
	private int homeRedisPort = 0;
	@Value("${game.home.redis.password}")
	private String homeRedisPassword = "";
	@Value("${game.home.redis.db}")
	private int homeRedisDb = 0;
}
