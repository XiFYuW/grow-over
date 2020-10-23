package com.grow.auth.security;

import com.alibaba.fastjson.JSON;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        if (authException == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        if (authException instanceof InsufficientAuthenticationException) {
            log.info("过滤【InsufficientAuthenticationException】异常：【{}】", authException.getMessage());
            return;
        }

        Throwable th = authException.getCause();
        if (th != null) {
            if (th instanceof BaseRuntimeException) {
                ResponseResult responseResult = ((BaseRuntimeException) th).getResponseResult();
                log.info("异常返回：【{}】", JSON.toJSONString(responseResult));
                response.setContentType("application/json; charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                PrintWriter out = response.getWriter();
                out.print(JSON.toJSONString(responseResult));
            }
            return;
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
