package com.grow.auth.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grow.auth.user.entity.UserInfoSecurity;
import com.grow.auth.user.entity.UserLoginRecordSecurity;
import com.grow.auth.user.entity.UserLoginSecurity;
import com.grow.auth.user.mapper.UserInfoSecurityMapper;
import com.grow.auth.user.mapper.UserLoginRecordSecurityMapper;
import com.grow.auth.user.mapper.UserLoginSecurityMapper;
import com.grow.auth.user.service.UserSecurityService;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.result.ResponseResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/26 14:51
 */
@Service
public class UserSecurityServiceImpl extends ServiceImpl<UserLoginSecurityMapper, UserLoginSecurity> implements UserSecurityService {

    private final UserLoginSecurityMapper userLoginSecurityMapper;

    private final UserInfoSecurityMapper userInfoSecurityMapper;

    private final UserLoginRecordSecurityMapper userLoginRecordSecurityMapper;

    @Autowired
    public UserSecurityServiceImpl(UserLoginSecurityMapper userLoginSecurityMapper, UserInfoSecurityMapper userInfoSecurityMapper,
                                   UserLoginRecordSecurityMapper userLoginRecordSecurityMapper) {
        this.userLoginSecurityMapper = userLoginSecurityMapper;
        this.userInfoSecurityMapper = userInfoSecurityMapper;
        this.userLoginRecordSecurityMapper = userLoginRecordSecurityMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserLoginSecurity findByName(String name) {
        UserLoginSecurity userLoginSecurity = userLoginSecurityMapper.selectOne(
                new QueryWrapper<UserLoginSecurity>().eq("user_name", name)
        );
        Optional.ofNullable(userLoginSecurity)
                .orElseThrow(() -> new BaseRuntimeException(ResponseResultUtils.getResponseResultF("用户名不正确")));
        return userLoginSecurity;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoSecurity findByUserId(String userId) {
        UserInfoSecurity userInfoSecurity = userInfoSecurityMapper.selectOne(new QueryWrapper<UserInfoSecurity>()
                .eq("user_id", userId)
                .eq("is_del", 0)
        );
        Optional.ofNullable(userInfoSecurity)
                .orElseThrow(() -> new BaseRuntimeException(ResponseResultUtils.getResponseResultF("用户信息不存在")));
        return userInfoSecurity;
    }

    @Override
    @Transactional
    public void insertUserLoginRecordSecurity(UserLoginRecordSecurity userLoginRecordSecurity) {
        userLoginRecordSecurityMapper.insert(userLoginRecordSecurity);
    }
}
