package com.grow.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.grow.common.service.RedisService;
import com.grow.config.jwt.JwtConfig;
import com.grow.config.systems.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final SystemConfig systemConfig;

    private final JwtConfig jwtConfig;

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate,
                            @Qualifier("systemConfig") SystemConfig systemConfig,
                            @Qualifier("jwtConfig") JwtConfig jwtConfig) {
        this.redisTemplate = redisTemplate;
        this.systemConfig = systemConfig;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Object getDataByToken(String token) {
        return redisTemplate.opsForValue().get(systemConfig.getRedisTokenDir() + systemConfig.getRedisPrefix() + token);
    }

    @Override
    public void addTokeData(String token, Object object) {
        final String key = systemConfig.getRedisTokenDir() + systemConfig.getRedisPrefix() + token;
        redisTemplate.opsForValue().set(key, object);
        redisTemplate.expire(key, jwtConfig.getTokenValidityInMinute(), TimeUnit.MINUTES);
    }

    @Override
    public <T> T getClassByToken(String authToken, Class<T> clazz) {
        return JSONObject.parseObject(JSONObject.toJSONString(this.getDataByToken(authToken)), clazz);
    }

    @Override
    public void delTokeData(String token) {
        redisTemplate.delete(token);
    }
}
