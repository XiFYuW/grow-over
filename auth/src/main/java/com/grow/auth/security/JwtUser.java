package com.grow.auth.security;

import com.grow.auth.user.entity.UserLoginSecurity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/26 13:11
 */
@Getter
@AllArgsConstructor
public class JwtUser implements UserDetails {
    private final UserLoginSecurity userLoginSecurity;

    @JsonIgnore
    private final Collection<GrantedAuthority> authorities;

    private final Collection roles;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return "ccc---nnn---mmm";
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return userLoginSecurity.getUserName();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return userLoginSecurity.getIsExpired() == 1;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return userLoginSecurity.getIsLocked() == 1;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return userLoginSecurity.getIsEnabled() == 1;
    }
}
