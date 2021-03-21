package com.grow.auth.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.grow.auth.user.dto.UserListDTO;
import com.grow.auth.user.entity.UserInfoSecurity;
import com.grow.auth.user.mapper.UserInfoSecurityMapper;
import com.grow.auth.user.service.UserService;
import com.grow.common.page.PageUtils;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserInfoSecurityMapper userInfoSecurityMapper;

    public UserServiceImpl(UserInfoSecurityMapper userInfoSecurityMapper) {
        this.userInfoSecurityMapper = userInfoSecurityMapper;
    }

    @Override
    public ResponseResult userList(UserListDTO userListDTO) {
        final Map<String, Object> data = PageUtils.getDateMap(() ->
                userInfoSecurityMapper.selectPage(
                                PageUtils.getPage(
                                        userListDTO.getPage(),
                                        userListDTO.getSize()
                                ),
                        new LambdaQueryWrapper<UserInfoSecurity>()
                                .eq(UserInfoSecurity::getIsDel, 0)
                                .orderByDesc(UserInfoSecurity::getCreateTime)
                        )
        );
        return ResponseResultUtils.getResponseResultS("查询成功", data);
    }
}
