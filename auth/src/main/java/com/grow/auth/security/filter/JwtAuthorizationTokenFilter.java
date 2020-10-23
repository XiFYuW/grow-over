package com.grow.auth.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.grow.auth.security.JwtAuthenticationEntryPoint;
import com.grow.auth.security.JwtUser;
import com.grow.auth.security.jwt.JwtContext;
import com.grow.auth.user.service.UserExecuteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private final UserExecuteService userExecuteService;

    private final JwtContext jwtContext;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final UserDetailsService userDetailsService;

    public JwtAuthorizationTokenFilter(UserExecuteService userExecuteService, JwtContext jwtContext,
                                       JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                       @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService) {
        this.userExecuteService = userExecuteService;
        this.jwtContext = jwtContext;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String authToken = jwtContext.getToken();
            String loginName = userExecuteService.getLoginName();
            Optional.ofNullable(loginName).ifPresent(u -> {
                DecodedJWT jwt = jwtContext.verifyTokenHMAC256(authToken);
                Optional.ofNullable(jwt).ifPresent(x -> {
                    final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(u);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });
            });
        } catch (AuthenticationException authenticationException) {
            try {
                SecurityContextHolder.clearContext();
                jwtAuthenticationEntryPoint.commence(request, response, authenticationException);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,content-type,X-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, response);
    }
}
