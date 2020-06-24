package com.mohammadreza.mirali.accountManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class Swagger {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors
            .basePackage("com.mohammadreza.mirali.accountManagement"))
        .paths(PathSelectors.regex("/.*"))
        .build().apiInfo(apiEndPointsInfo());
  }

  private ApiInfo apiEndPointsInfo() {
    return new ApiInfoBuilder().title("Account management REST API")
        .description("Account management REST API Technical Test")
        .contact(new Contact("Mohammadreza Mirali",
            "https://www.linkedin.com/in/mohamadreza-mirali-6a053188/", "smrm1363@gmail.com@gmail.com"))
        .version("1.0.0")
        .build();
  }

}
