package com.example.springboot2thymeleaf;

import jwebform.FormBuilder;
import jwebform.integration.Bean2Form;
import jwebform.themes.FormRenderer;
import jwebform.themes.sourcecode.ThemeJavaRenderer;
import jwebform.themes.sourcecode.mapper.StandardMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static jwebform.field.builder.BuildInType.text;

@SpringBootApplication public class SpringBoot2ThymeleafApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBoot2ThymeleafApplication.class, args);
  }

/*
  @Bean public FormRenderer formrRenderer() {
    return (a, b, c, d) -> "Hello World";
  }
  */

/*
  @Bean public Bean2Form bean2Form() {
    return o -> FormBuilder.simple().typeBuilder(text("name").label("Dein Name")).build();
  }*/
}
