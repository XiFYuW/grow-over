package com.grow.auth.login.service.impl;

import cn.hutool.core.date.DateUtil;
import com.grow.auth.login.vo.UserInfoVO;
import com.grow.auth.login.vo.UserLoginVo;
import com.grow.auth.security.JwtUser;
import com.grow.auth.security.UserDO;
import com.grow.auth.security.jwt.JwtContext;
import com.grow.auth.security.service.JwtPermissionService;
import com.grow.auth.user.entity.UserInfoSecurity;
import com.grow.auth.user.entity.UserLoginRecordSecurity;
import com.grow.auth.user.service.UserExecuteService;
import com.grow.auth.user.service.UserSecurityService;
import com.grow.common.ip.IPUtils;
import com.grow.common.result.ResponseResult;
import com.grow.common.result.ResponseResultUtils;
import com.grow.auth.login.dto.LoginDTO;
import com.grow.auth.login.service.LoginService;
import com.grow.common.security.RequestHolder;
import com.grow.common.service.RedisService;
import com.grow.common.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class LoginServiceImpl implements LoginService {

    private final RedisService redisService;

    private final UserDetailsService userDetailsService;

    private final UserSecurityService userSecurityService;

    private final JwtContext jwtContext;

    private final UserExecuteService userExecuteService;

    private final JwtPermissionService jwtPermissionService;

    @Autowired
    public LoginServiceImpl(RedisService redisService, @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
                            UserSecurityService userSecurityService, JwtContext jwtContext,
                            UserExecuteService userExecuteService, JwtPermissionService jwtPermissionService) {
        this.redisService = redisService;
        this.userDetailsService = userDetailsService;
        this.userSecurityService = userSecurityService;
        this.jwtContext = jwtContext;
        this.userExecuteService = userExecuteService;
        this.jwtPermissionService = jwtPermissionService;
    }

    @Override
    @Transactional
    public ResponseResult login(LoginDTO loginDTO) {
        final HttpServletRequest request = RequestHolder.getHttpServletRequest();
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(loginDTO.getUserName());
        if(!jwtUser.getUserLoginSecurity().getPassword().equals(EncryptUtils.encryptPassword(loginDTO.getPassword()))){
            throw new AccountExpiredException("密码错误");
        }

        final UserInfoSecurity userInfoSecurity = userSecurityService.findByUserId(jwtUser.getUserLoginSecurity().getUserId());
        final UserDO userDO = new UserDO(jwtUser.getUsername(), userInfoSecurity);

        String token = jwtContext.getTokenHMAC256();
        redisService.addTokeData(token, userDO);

        // 登录记录
        String ip = IPUtils.getIp(request);
        userSecurityService.insertUserLoginRecordSecurity(
                UserLoginRecordSecurity.builder()
                        .loginBrowser(IPUtils.getBrowser(request))
                        .loginIp(ip)
                        .loginTime(DateUtil.toLocalDateTime(DateUtil.date()))
                        .loginLocation(IPUtils.getLocation(ip))
                        .isDel(0)
                        .userId(jwtUser.getUserLoginSecurity().getUserId())
                        .build()
        );
        return ResponseResultUtils.getResponseResultS("登录成功", new UserLoginVo(token, jwtUser));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult getInfoNew() {
        final UserInfoSecurity userInfoSecurity = userExecuteService.getUserInfo();
        final Collection roleName = jwtPermissionService.getRoleName(userInfoSecurity.getUserId());
        return ResponseResultUtils.getResponseResultS("获取成功", new UserInfoVO(userInfoSecurity, roleName));
    }
}
