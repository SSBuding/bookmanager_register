package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .contact(new Contact("你的名字", "https://www.bilibili.com", "javastudy111*@163.com"))
                .title("图书管理系统 - 在线API接口文档")
                .description("欢迎访问在线接口文档,在查阅时可以进行调试")
                .version("1.1")
                .build();
    }
}
