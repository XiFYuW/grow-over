package com.grow.config.multipart;

import com.grow.config.file.FileConfig;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/09/01 10:00
 */
@Configuration
public class MultipartConfig {

    private final FileConfig fileConfig;

    public MultipartConfig(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(fileConfig.getUploadFolder());
        return factory.createMultipartConfig();
    }
}
