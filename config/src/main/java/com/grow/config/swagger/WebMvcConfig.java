package com.grow.config.swagger;

import com.grow.config.file.FileConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/01 14:44
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final FileConfig fileConfig;

    public WebMvcConfig(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + fileConfig.getUploadFolder());

    }
}
