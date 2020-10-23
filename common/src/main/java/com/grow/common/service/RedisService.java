package com.grow.common.service;

public interface RedisService {

    Object getDataByToken(String token);

    void addTokeData(String token, Object object);

    <T> T getClassByToken(String authToken, Class<T> clazz);
}
