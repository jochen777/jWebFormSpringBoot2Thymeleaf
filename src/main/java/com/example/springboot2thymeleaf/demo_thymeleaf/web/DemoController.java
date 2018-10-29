package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import jwebform.field.SubmitType;
import jwebform.integration.annotations.UseFieldType;
import jwebform.spring.SimpleJWebForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DemoController {



  @RequestMapping("/demo")
  public void demo(SimpleJWebForm<DemoForm> form) {
    if (form.isOk()){
      log.info("Form was successfully submitted: " + form.getBean().firstname);
    }
  }

  public static class DemoForm {

    public String firstname="";
    public String lastname="";
    @UseFieldType(type= SubmitType.class)
    public String submit="";
  }
}
