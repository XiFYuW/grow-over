package com.grow.auth.system.service.impl;

import com.grow.auth.system.entity.SystemRoleUser;
import com.grow.auth.system.mapper.SystemRoleUserMapper;
import com.grow.auth.system.service.ISystemRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色用户
 *
 * @author XiFYuW
 * @since 2020-09-04
 */
@Service
public class SystemRoleUserServiceImpl extends ServiceImpl<SystemRoleUserMapper, SystemRoleUser> implements ISystemRoleUserService {

}
