package com.grow.auth.security.jwt;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.grow.common.enums.ResultEnum;
import com.grow.common.exception.BaseAuthentication;
import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.result.ResponseResultUtils;
import com.grow.common.security.RequestHolder;
import com.grow.config.jwt.JwtConfig;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtContext {

    private final JwtConfig jwtConfig;

    public JwtContext(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String getToken(){
        return RequestHolder.getHttpServletRequest().getHeader(jwtConfig.getHeader());
    }

    public String getTokenHMAC256() {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());

        DateTime now = DateUtil.date(Calendar.getInstance());
        DateTime expireDate = DateUtil.offsetMinute(now, jwtConfig.getExpiration());

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        map.put("cty", "");
        map.put("kid", IdUtil.randomUUID());

        return JWT.create()
                .withHeader(map) // 设置头部信息 Header
                .withClaim("key", "HS256") // 自定义信息
                .withIssuer("SERVER")// 签名是有谁生成 例如 服务器
                .withSubject("The login Token HMAC256") // 签名的主题
                .withNotBefore(now) // 定义在什么时间之前，该jwt都是不可用的
                .withAudience("APP") // 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(now) // 生成签名的时间
                .withExpiresAt(expireDate) // 签名过期的时间
                .sign(algorithm);
    }

    public DecodedJWT verifyTokenHMAC256(String token) {
        DecodedJWT jwt;
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("SERVER").build();
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e){
            if (e instanceof TokenExpiredException) {
                throw new BaseAuthentication(ResultEnum.TOKEN_EXPIRED.getMessage(),
                        new BaseRuntimeException(ResponseResultUtils.getResponseResult(ResultEnum.TOKEN_EXPIRED.getCode(),
                                ResultEnum.TOKEN_EXPIRED.getMessage())));
            }
            throw new BaseAuthentication("token验证失败",
                    new BaseRuntimeException(ResponseResultUtils.getResponseResultS("token验证失败；【{}】",
                            e.getMessage())));
        }
        return jwt;
    }

}
