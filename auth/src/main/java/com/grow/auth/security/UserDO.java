package com.grow.auth.security;

import com.grow.auth.user.entity.UserInfoSecurity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/02 11:55
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDO implements Serializable {
    private String loginName;

    private UserInfoSecurity userInfoSecurity;
}
