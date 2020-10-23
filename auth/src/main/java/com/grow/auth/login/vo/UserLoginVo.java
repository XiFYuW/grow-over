package com.grow.auth.login.vo;

import com.grow.auth.security.JwtUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/27 18:42
 */
@Getter
@AllArgsConstructor
public class UserLoginVo {
    private final String token;

    private final JwtUser user;
}
