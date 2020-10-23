package com.grow.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/26 14:03
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component("jwtConfig")
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String header;

    private String secret;

    private int expiration;

    private String online;

    private String codeKey;
}
