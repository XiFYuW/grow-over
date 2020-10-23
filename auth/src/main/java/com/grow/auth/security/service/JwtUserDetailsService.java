package com.grow.auth.security.service;

import com.grow.auth.security.JwtUser;
import com.grow.auth.user.entity.UserLoginSecurity;
import com.grow.auth.user.service.UserSecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/26 13:10
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {

    private final UserSecurityService userSecurityService;

    private final JwtPermissionService jwtPermissionService;

    public JwtUserDetailsService(UserSecurityService userSecurityService, JwtPermissionService jwtPermissionService) {
        this.userSecurityService = userSecurityService;
        this.jwtPermissionService = jwtPermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final UserLoginSecurity userLoginSecurity = userSecurityService.findByName(s);
        return new JwtUser(
                userLoginSecurity,
                jwtPermissionService.getJwtPermission(userLoginSecurity)
        );
    }
}
