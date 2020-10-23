package com.grow.auth.login.service;

import com.grow.common.result.ResponseResult;
import com.grow.auth.login.dto.LoginDTO;

public interface LoginService {

    ResponseResult login(LoginDTO loginDTO);

    ResponseResult getInfoNew();
}
