package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import jwebform.field.SubmitType;
import jwebform.integration.annotations.UseDecoration;
import jwebform.integration.annotations.UseFieldType;
import jwebform.spring.SimpleJWebForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class DemoController {

  /*
   an autoconfigured Argument resolver will fill the DemoForm with the request params
   and put the formModel in the model as "form" and "form_rendered"
  */
  @RequestMapping("/demo")
  public void demo(SimpleJWebForm<DemoForm> form) {
    if (form.isOk()){
      log.info("Form was successfully submitted: " + form.getBean().firstname);
    }
  }

  /*
   Demo form.
   Note: This must be "public static" here, because it have to be initialsed programmatically
   There are some
    */
  public static class DemoForm {

    @UseDecoration(label = "Your firstname", helpText = "Don't cheat here!")
    public String firstname="";

    public String lastname="";

    @UseFieldType(type= SubmitType.class)
    public String submit="";
  }
}
