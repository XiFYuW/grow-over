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
import com.grow.auth.user.entity.UserLoginSecurity;
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
import com.grow.common.utils.RsaUtils;
import com.grow.config.rsa.RsaProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
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

    private final RsaProperties rsaProperties;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public LoginServiceImpl(RedisService redisService, @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
                            UserSecurityService userSecurityService, JwtContext jwtContext,
                            UserExecuteService userExecuteService, JwtPermissionService jwtPermissionService, RsaProperties rsaProperties, RedisTemplate<String, Object> redisTemplate) {
        this.redisService = redisService;
        this.userDetailsService = userDetailsService;
        this.userSecurityService = userSecurityService;
        this.jwtContext = jwtContext;
        this.userExecuteService = userExecuteService;
        this.jwtPermissionService = jwtPermissionService;
        this.rsaProperties = rsaProperties;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional
    public ResponseResult login(LoginDTO loginDTO) throws Exception {
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(rsaProperties.getPrivateKey(), loginDTO.getPassword());
        // 查询验证码
        String code = (String) redisTemplate.opsForValue().get(loginDTO.getUuid());
        // 清除验证码
        redisTemplate.delete(loginDTO.getUuid());
        if (StringUtils.isBlank(code)) {
            return ResponseResultUtils.getResponseResultF("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(loginDTO.getCode()) || !loginDTO.getCode().equalsIgnoreCase(code)) {
            return ResponseResultUtils.getResponseResultF("验证码错误");
        }

        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(loginDTO.getUsername());
        if(!jwtUser.getUserLoginSecurity().getPassword().equals(EncryptUtils.encryptPassword(password))){
            throw new AccountExpiredException("密码错误");
        }

        final UserInfoSecurity userInfoSecurity = userSecurityService.findByUserId(jwtUser.getUserLoginSecurity().getId());
        final UserDO userDO = new UserDO(jwtUser.getUsername(), userInfoSecurity);

        String token = jwtContext.getTokenHMAC256();
        redisService.addTokeData(token, userDO);

        // 登录记录
        final HttpServletRequest request = RequestHolder.getHttpServletRequest();
        String ip = IPUtils.getIp(request);
        userSecurityService.insertUserLoginRecordSecurity(
                UserLoginRecordSecurity.builder()
                        .loginBrowser(IPUtils.getBrowser(request))
                        .loginIp(ip)
                        .loginTime(DateUtil.toLocalDateTime(DateUtil.date()))
                        .loginLocation(IPUtils.getLocation(ip))
                        .isDel(0)
                        .userId(jwtUser.getUserLoginSecurity().getId())
                        .build()
        );
        return ResponseResultUtils.getResponseResultS("登录成功", new UserLoginVo(token, jwtUser));
    }

    @Override
    public ResponseResult loginOut() {
        String token = jwtContext.getToken();
        redisService.delTokeData(token);
        return ResponseResultUtils.getResponseResultS("登出成功");
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseResult getUserInfo() {
        final UserInfoSecurity userInfoSecurity = userExecuteService.getUserInfo();
        final UserLoginSecurity userLoginSecurity = userSecurityService.findById(userInfoSecurity.getUserId());
        userInfoSecurity.setUserName(userLoginSecurity.getUserName());
        final Collection roleName = jwtPermissionService.getRoleName(userInfoSecurity.getUserId());
        return ResponseResultUtils.getResponseResultS("获取成功", new UserInfoVO(userInfoSecurity, roleName));
    }
}
