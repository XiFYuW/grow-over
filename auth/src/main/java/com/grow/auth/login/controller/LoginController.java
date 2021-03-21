package com.grow.auth.login.controller;

import cn.hutool.core.util.IdUtil;
import com.grow.auth.login.vo.UserInfoVO;
import com.grow.common.result.ResponseResultUtils;
import com.grow.config.jwt.JwtConfig;
import com.grow.config.login.LoginCodeEnum;
import com.grow.config.login.LoginConfig;
import com.grow.auth.login.service.LoginService;
import com.grow.auth.security.annotation.AnonymousAccess;
import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import com.grow.auth.login.dto.LoginDTO;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/web")
@Slf4j
@Api(tags = "登录")
public class LoginController {

    private final LoginService loginService;

    private final LoginConfig loginConfig;

    private final JwtConfig jwtConfig;

    private final RedisTemplate<String, Object> redisTemplate;

    public LoginController(LoginService loginService,
                           @Qualifier("loginConfig") LoginConfig loginConfig,
                           @Qualifier("jwtConfig") JwtConfig jwtConfig,
                           RedisTemplate<String, Object> redisTemplate) {
        this.loginService = loginService;
        this.loginConfig = loginConfig;
        this.jwtConfig = jwtConfig;
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation(value="后台登录")
    @PostMapping(value = "/login")
    @AnonymousAccess
    @LogOutAnnotation(url = "/login")
    public ResponseResult login(@Validated @RequestBody LoginDTO loginDTO) throws Exception {
        return loginService.login(loginDTO);
    }

    @ApiOperation(value="后台登出")
    @DeleteMapping(value = "/loginOut")
    @AnonymousAccess
    @LogOutAnnotation(url = "/loginOut")
    public ResponseResult loginOut() {
        return loginService.loginOut();
    }

    @GetMapping(value = "/info")
    @LogOutAnnotation(url = "/web/info", request = false)
//    @PreAuthorize("@el.check('user:getInfo')")
    @AnonymousAccess
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = UserInfoVO.class)
    })
    public ResponseResult getUserInfo(){
        return loginService.getUserInfo();
    }

    @ApiOperation("获取验证码")
    @GetMapping(value = "/login/code")
    @AnonymousAccess
    public ResponseEntity<ResponseResult> getCode() {
        // 获取运算的结果
        Captcha captcha = loginConfig.getCaptcha();
        String uuid = jwtConfig.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisTemplate.opsForValue().set(uuid, captchaValue, loginConfig.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return new ResponseEntity<>(ResponseResultUtils.getResponseResultS("获取成功", imgResult), HttpStatus.OK);
    }
}
