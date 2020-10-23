package com.grow.auth.login.controller;

import com.grow.auth.login.service.LoginService;
import com.grow.auth.security.annotation.AnonymousAccess;
import com.grow.auth.user.entity.UserInfoSecurity;
import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import com.grow.auth.login.dto.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/web")
@Slf4j
@Api(tags = "登录")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(value="后台登录")
    @PostMapping(value = "/newLogin", produces = "application/json;charset=UTF-8")
    @Validated
    @AnonymousAccess
    @LogOutAnnotation(url = "/web/newLogin")
    public ResponseResult login(@Valid LoginDTO loginDTO){
        return loginService.login(loginDTO);
    }

    @PostMapping(value = "/getInfoNew", produces = "application/json;charset=UTF-8")
    @LogOutAnnotation(url = "/web/getInfoNew", request = false)
    @PreAuthorize("@el.check('user:getInfo')")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = UserInfoSecurity.class)
    })
    public ResponseResult getUserInfo(){
        return loginService.getInfoNew();
    }
}
