package com.tj.exercise.flash.sale.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: tj
 * @Date: 2023/5/15 22:27
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                //配置网站的基本信息
                .apiInfo(new ApiInfoBuilder()
                        //网站标题
                        .title("flash-sale-demo项目在线接口文档")
                        //标题后面的版本号
                        .version("v1.0")
                        .description("flash-sale-demo项目接口文档")
                        //联系人信息
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tj.exercise.flash.sale.demo.controller"))
                //指定接口的位置
                .paths(PathSelectors.any())
                .build();
    }
}
