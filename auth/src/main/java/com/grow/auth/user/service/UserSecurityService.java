package com.grow.auth.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.grow.auth.user.entity.UserInfoSecurity;
import com.grow.auth.user.entity.UserLoginRecordSecurity;
import com.grow.auth.user.entity.UserLoginSecurity;

/**
 * @author https://github.com/XiFYuW
 * @since  2020/08/26 14:48
 */
public interface UserSecurityService extends IService<UserLoginSecurity> {

    UserLoginSecurity findByName(String name);

    UserInfoSecurity findByUserId(Long userId);

    UserLoginSecurity findById(Long userId);

    void insertUserLoginRecordSecurity(UserLoginRecordSecurity userLoginRecordSecurity);
}
