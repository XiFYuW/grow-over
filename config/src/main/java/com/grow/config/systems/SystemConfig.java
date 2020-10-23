package com.grow.config.systems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component("systemConfig")
@Configuration
@ConfigurationProperties(prefix = "system.config")
public class SystemConfig {

    private String redisTokenDir;

    private String redisPrefix;
}
