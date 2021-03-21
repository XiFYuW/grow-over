package com.grow.auth.user.service;

import com.grow.auth.user.dto.UserListDTO;
import com.grow.common.result.ResponseResult;

public interface UserService {

    ResponseResult userList(UserListDTO userListDTO);
}
