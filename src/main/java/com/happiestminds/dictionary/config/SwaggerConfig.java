package com.happiestminds.dictionary.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.google.common.base.Predicates;

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
public class SwaggerConfig {

  @Autowired
  private Environment env;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)//
      .select()//
      .apis(RequestHandlerSelectors.basePackage(env.getProperty("application.groupid")))//
      .paths(Predicates.not(PathSelectors.regex("/error")))//
      .build()//
      .apiInfo(metadata())//
      .useDefaultResponseMessages(false)//
      .genericModelSubstitutes(Optional.class);

  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()//
      .title(env.getProperty("application.name"))//
      .description(env.getProperty("application.description"))//
      .version(env.getProperty("application.version"))//
      .contact(new Contact(null, null, "rajamohan.mupp@gmail.com"))//
      .build();
  }

}
