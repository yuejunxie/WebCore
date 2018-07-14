package com.stonesmiller.web.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/6/28 13:13
 * Description:
 */
@Configuration
@EnableSwagger2
@Profile("default")
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SPRING_WEB).apiInfo(new ApiInfoBuilder().title("StoneSmiller-斫石者").version("1.0.0").build())
                .select().paths(PathSelectors.any()).apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("")).apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }
}
