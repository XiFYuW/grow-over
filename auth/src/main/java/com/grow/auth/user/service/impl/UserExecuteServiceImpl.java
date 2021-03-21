package com.grow.auth.user.service.impl;

import com.grow.auth.security.UserDO;
import com.grow.auth.security.jwt.JwtContext;
import com.grow.auth.user.entity.UserInfoSecurity;
import com.grow.auth.user.service.UserExecuteService;
import com.grow.common.enums.ResultEnum;
import com.grow.common.exception.BaseAuthentication;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.result.ResponseResultUtils;
import com.grow.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author https://github.com/XiFYuW
 * @since  2020/09/02 12:02
 */
@Service
@Slf4j
public class UserExecuteServiceImpl implements UserExecuteService {

    private final JwtContext jwtContext;

    private final RedisService redisService;

    public UserExecuteServiceImpl(JwtContext jwtContext, RedisService redisService) {
        this.jwtContext = jwtContext;
        this.redisService = redisService;
    }

    @Override
    public UserInfoSecurity getUserInfo() {
        final UserDO userDO = this.getUserD0();
        Optional.ofNullable(userDO).orElseThrow(() ->
                new BaseRuntimeException(ResponseResultUtils.getResponseResult(ResultEnum.TOKEN_EXPIRED.getCode(),
                        ResultEnum.TOKEN_EXPIRED.getMessage())));
        return userDO.getUserInfoSecurity();
    }

    @Override
    public UserDO getUserD0() {
        final String authToken = jwtContext.getToken();
        UserDO userDO = redisService.getClassByToken(authToken, UserDO.class);
        if (!StringUtils.isEmpty(authToken) && userDO == null) {
            throw new BaseAuthentication(ResultEnum.TOKEN_ERR.getMessage(), new BaseRuntimeException(
                    ResponseResultUtils.getResponseResult(ResultEnum.TOKEN_ERR.getCode(), ResultEnum.TOKEN_ERR.getMessage())));
        }
        return userDO;
    }

    @Override
    public String getLoginName() {
        final UserDO userDO = this.getUserD0();
        return userDO == null ? null : userDO.getLoginName();
    }
}
