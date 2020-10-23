package com.grow.common.utils;

import org.springframework.util.DigestUtils;

public class EncryptUtils {

    /**
     * 密码加密
     */
    public static String encryptPassword(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
