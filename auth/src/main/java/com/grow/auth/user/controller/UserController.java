package com.grow.auth.user.controller;

import com.grow.auth.security.annotation.AnonymousAccess;
import com.grow.auth.user.dto.UserListDTO;
import com.grow.auth.user.service.UserService;
import com.grow.common.log.LogOutAnnotation;
import com.grow.common.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/user")
@Slf4j
@Api(tags = "用户管理")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value="获取用户信息")
    @GetMapping(value = "/userList", produces = "application/json;charset=UTF-8")
    @AnonymousAccess
    @LogOutAnnotation(url = "/userList")
    public ResponseResult userList(@Validated UserListDTO userListDTO) {
        return userService.userList(userListDTO);
    }
}
