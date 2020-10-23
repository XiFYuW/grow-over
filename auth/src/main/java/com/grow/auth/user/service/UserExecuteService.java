package com.grow.auth.user.service;

import com.grow.auth.security.UserDO;
import com.grow.auth.user.entity.UserInfoSecurity;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/02 12:02
 */
public interface UserExecuteService {

    UserInfoSecurity getUserInfo();

    UserDO getUserD0();

    String getLoginName();
}
