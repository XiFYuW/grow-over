package com.grow.common.aop;

import com.alibaba.fastjson.JSON;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.result.ResponseResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/21 10:38
 */
@Component
@Aspect
@Order(0)
@Slf4j
public class LockApiAnAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    private final Lock lockApi;

    private final static String LOCK_API_KEY = "LOCK_API_KEY:";

    public LockApiAnAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.lockApi = new ReentrantLock();
    }

    @Around("@annotation(com.grow.common.aop.LockApiAn) && @annotation(lockApiAn)")
    public Object lockApiAnAspectAround(ProceedingJoinPoint point, LockApiAn lockApiAn) throws Throwable {
        final ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        final Object[] objects = point.getArgs();
        final String value = JSON.toJSONString(objects[0]);
        lockApi.lock();
        try {
            Long rank = zSetOperations.rank(LOCK_API_KEY, value);
            log.info("" + rank);
            if (rank != null) {
                Double aDouble = zSetOperations.score(LOCK_API_KEY, value);
                Optional.ofNullable(aDouble).ifPresent(x -> {
                    long score = x.longValue();
                    if (System.currentTimeMillis() > score + lockApiAn.time()) {
                        zSetOperations.remove(LOCK_API_KEY, value);
                    } else {
                        throw new BaseRuntimeException(ResponseResultUtils.getResponseResultS("请勿重复访问"));
                    }
                });
            } else {
                zSetOperations.add(LOCK_API_KEY, value, System.currentTimeMillis());
            }
        }finally {
            lockApi.unlock();
        }

        final Object o = point.proceed(point.getArgs());
        if (!lockApiAn.isSingle()) {
            zSetOperations.remove(LOCK_API_KEY, value);
        }
        return o;
    }
}
