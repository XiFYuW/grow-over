package com.grow.auth.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.grow.auth.system.entity.SystemMenu;
import com.grow.auth.system.entity.SystemRole;
import com.grow.auth.system.entity.SystemRoleMenu;
import com.grow.auth.system.entity.SystemRoleUser;
import com.grow.auth.system.mapper.SystemMenuMapper;
import com.grow.auth.system.mapper.SystemRoleMapper;
import com.grow.auth.system.mapper.SystemRoleMenuMapper;
import com.grow.auth.system.mapper.SystemRoleUserMapper;
import com.grow.auth.user.entity.UserLoginSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtPermissionService {

    private final SystemRoleUserMapper systemRoleUserMapper;

    private final SystemRoleMenuMapper systemRoleMenuMapper;

    private final SystemMenuMapper systemMenuMapper;

    private final SystemRoleMapper systemRoleMapper;

    public JwtPermissionService(SystemRoleUserMapper systemRoleUserMapper, SystemRoleMenuMapper systemRoleMenuMapper,
                                SystemMenuMapper systemMenuMapper, SystemRoleMapper systemRoleMapper) {
        this.systemRoleUserMapper = systemRoleUserMapper;
        this.systemRoleMenuMapper = systemRoleMenuMapper;
        this.systemMenuMapper = systemMenuMapper;
        this.systemRoleMapper = systemRoleMapper;
    }

    public Collection<SystemRoleUser> getSystemRoleUser(final Long userId){
        return systemRoleUserMapper.selectList(
                new LambdaQueryWrapper<SystemRoleUser>().eq(SystemRoleUser::getUserId, userId)
        );
    }

    public Collection<String> getRoleName(final Long userId){
        List<SystemRoleUser> systemRoleUserList = (List<SystemRoleUser>) getSystemRoleUser(userId);

        return systemRoleUserList.stream()
                .filter(x -> !x.getSystemRoleId().equals(0))
                .map(x -> {
                    SystemRole systemRole = systemRoleMapper.selectOne(
                            new LambdaQueryWrapper<SystemRole>()
                                    .eq(SystemRole::getId, x.getSystemRoleId())
                                    .eq(SystemRole::getIsDel, 0));
                    return Optional.ofNullable(systemRole).map(SystemRole::getSystemRoleName).orElse("");
                })
                .collect(Collectors.toSet());
    }

    public Set<Integer> getSystemRoleMenuId(List<SystemRoleUser> systemRoleUserList) {
        return systemRoleUserList.stream()
                .filter(x -> !x.getSystemRoleId().equals(0))
                .map(x -> {
                    List<SystemRoleMenu> systemRoleMenu = systemRoleMenuMapper.selectList(
                            new LambdaQueryWrapper<SystemRoleMenu>()
                                    .eq(SystemRoleMenu::getSystemRoleId, x.getSystemRoleId())
                                    .eq(SystemRoleMenu::getIsDel, 0));
                    return systemRoleMenu.stream().map(SystemRoleMenu::getSystemMenuId).collect(Collectors.toSet());
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    Collection<GrantedAuthority> getJwtPermission(final UserLoginSecurity userLoginSecurity) {

        List<SystemRoleUser> systemRoleUserList = (List<SystemRoleUser>) getSystemRoleUser(userLoginSecurity.getId());

        Set<Integer> systemRoleMenuSet = getSystemRoleMenuId(systemRoleUserList);


        Set<String> permissions = systemRoleMenuSet.stream()
                .filter(x -> !x.equals(0))
                .map(x -> {
                    SystemMenu systemMenu = systemMenuMapper.selectOne(new LambdaQueryWrapper<SystemMenu>()
                            .eq(SystemMenu::getId, x)
                            .eq(SystemMenu::getIsDel, 0));
                    return Optional.ofNullable(systemMenu).map(SystemMenu::getSystemPermissions).orElse("");
                })
                .filter(StringUtils::isNoneBlank)
                .collect(Collectors.toSet());

        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
