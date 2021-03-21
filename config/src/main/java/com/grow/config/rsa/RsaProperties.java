package com.grow.config.rsa;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component("rsaProperties")
@Configuration
@ConfigurationProperties(prefix = "rsa")
public class RsaProperties {

    public String privateKey;

}
