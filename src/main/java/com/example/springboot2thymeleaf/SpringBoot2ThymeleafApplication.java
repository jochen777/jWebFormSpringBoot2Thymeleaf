package com.example.springboot2thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot2ThymeleafApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBoot2ThymeleafApplication.class, args);
  }

  /*
   * @Bean public FormRenderer formrRenderer() { return (a, b, c, d) -> "Hello World"; }
   */

  /*
   * @Bean public Bean2Form bean2Form() { return o ->
   * FormBuilder.simple().typeBuilder(text("name").label("Dein Name")).build(); }
   */
}
