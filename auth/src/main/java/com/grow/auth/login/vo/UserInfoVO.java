package com.grow.auth.login.vo;

import com.grow.auth.user.entity.UserInfoSecurity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/07 9:38
 */
@Getter
@AllArgsConstructor
public class UserInfoVO implements Serializable {
    private final UserInfoSecurity userInfoSecurity;

    private final Collection roles;
}
