package com.grow.config.swagger;

import com.grow.config.jwt.JwtConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/08/01 13:14
 */
@Configuration
@EnableSwagger2
public class SwaggerAppConfig {

    private final JwtConfig jwtConfig;

    @Autowired
    public SwaggerAppConfig(@Qualifier("jwtConfig") JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name(jwtConfig.getHeader()).description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("")
                .required(false)
                .build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("grow-over(蔓延)")
                .description("")
                .termsOfServiceUrl("")
                .contact(new Contact("admin", "", "xif_yuw@162.com"))
                .version("0.0.1")
                .build();
    }
}
