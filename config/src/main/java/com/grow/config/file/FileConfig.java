package com.grow.config.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/01 12:01
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component("fileConfig")
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileConfig {

    private String uploadFolder;

    private String urlFolder;
}
