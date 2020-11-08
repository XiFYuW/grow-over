package com.grow.auth.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    private Collection<SystemRoleUser> getSystemRoleUser(final Long userId){
        return systemRoleUserMapper.selectList(
                new QueryWrapper<SystemRoleUser>().eq("user_id", userId)
        );
    }

    public Collection<String> getRoleName(final Long userId){
        List<SystemRoleUser> systemRoleUserList = (List<SystemRoleUser>) getSystemRoleUser(userId);

        return systemRoleUserList.stream()
                .filter(x -> StringUtils.isNotEmpty(x.getSystemRoleId()))
                .map(x -> {
                    SystemRole systemRole = systemRoleMapper.selectOne(
                            new QueryWrapper<SystemRole>()
                                    .eq("system_role_id", x.getSystemRoleId())
                                    .eq("is_del", 0));
                    return Optional.ofNullable(systemRole).map(SystemRole::getSystemRoleName).orElse("");
                })
                .collect(Collectors.toSet());
    }

    Collection<GrantedAuthority> getJwtPermission(final UserLoginSecurity userLoginSecurity) {

        List<SystemRoleUser> systemRoleUserList = (List<SystemRoleUser>) getSystemRoleUser(userLoginSecurity.getId());

        Set<String> systemRoleMenuSet = systemRoleUserList.stream()
                .filter(x -> StringUtils.isNotEmpty(x.getSystemRoleId()))
                .map(x -> {
                    SystemRoleMenu systemRoleMenu = systemRoleMenuMapper.selectOne(
                            new QueryWrapper<SystemRoleMenu>()
                                    .eq("system_role_id", x.getSystemRoleId())
                                    .eq("is_del", 0));
                    return Optional.ofNullable(systemRoleMenu).map(SystemRoleMenu::getSystemMenuId).orElse("");
                })
                .collect(Collectors.toSet());


        Set<String> permissions = systemRoleMenuSet.stream()
                .filter(StringUtils::isNotEmpty)
                .map(x -> {
                    SystemMenu systemMenu = systemMenuMapper.selectOne(new QueryWrapper<SystemMenu>()
                            .eq("system_menu_id", x)
                            .eq("is_del", 0));
                    return Optional.ofNullable(systemMenu).map(SystemMenu::getSystemPermissions).orElse("");
                })
                .collect(Collectors.toSet());

        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
