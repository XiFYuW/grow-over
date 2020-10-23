package com.grow.common.security;

import cn.hutool.json.JSONObject;
import com.grow.common.enums.ResultEnum;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.result.ResponseResultUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 获取当前登录的用户
 */
public class SecurityUtils {

    public static UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BaseRuntimeException(ResponseResultUtils.getResponseResult(
                    ResultEnum.TOKEN_EXPIRED.getCode(), "登录状态过期"));
        }
        return userDetails;
    }

    /**
     * 获取系统用户名称
     * @return 系统用户名称
     */
    public static String getUserName(){
        Object obj = getUserDetails();
        return new JSONObject(obj).get("userName", String.class);
    }

    /**
     * 获取系统用户id
     * @return 系统用户id
     */
    public static Long getUserId(){
        Object obj = getUserDetails();
        return new JSONObject(obj).get("userId", Long.class);
    }
}
