package com.grow.common.log;

import com.alibaba.fastjson.JSON;
import com.grow.common.aop.JoinPointUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.lang.annotation.Annotation;

/**
 * @author https://github.com/XiFYuW
 * @since  2020/08/31 13:24
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class LogOutInterceptor {

    @Around("@annotation(com.grow.common.log.LogOutAnnotation) && @annotation(logOutAnnotation)")
    public Object sneakLifeLogAround(ProceedingJoinPoint point, LogOutAnnotation logOutAnnotation) throws Throwable{
        Object[] objects = point.getArgs();
        String url = logOutAnnotation.url();
        if (logOutAnnotation.request()) {
            Annotation[][] annotations = JoinPointUtil.getParameterAnnotations(point);
            Object validObject = JoinPointUtil.getParameterAnnotationObject(objects, annotations, Valid.class);
            if (validObject != null) {
                log.info("请求接口" + url + "请求参数：{}", JSON.toJSONString(validObject));
            }
        } else {
            log.info("请求接口" + url);
        }
        Object o = point.proceed(objects);
        log.info("请求接口" + url + "返回数据：{}", JSON.toJSONString(o));
        return o;
    }

}
