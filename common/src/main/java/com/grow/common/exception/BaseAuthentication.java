package com.grow.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/05 16:21
 */
public class BaseAuthentication extends AuthenticationException {
    public BaseAuthentication(String msg, Throwable t) {
        super(msg, t);
    }

    public BaseAuthentication(String msg) {
        super(msg);
    }
}
