package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import jwebform.Form;
import jwebform.FormBuilder;
import jwebform.field.SubmitType;
import jwebform.integration.annotations.UseDecoration;
import jwebform.integration.annotations.UseFieldType;
import jwebform.processor.FormGenerator;
import jwebform.spring.SimpleJWebForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotEmpty;

import static jwebform.field.builder.BuildInType.text;

@Controller
@Slf4j
public class DemoController {

  /*
   an autoconfigured Argument resolver will fill the DemoForm with the request params
   and put the formModel in the model as "form" and "form_rendered"
  */
  @RequestMapping("/demo")
  public void demo(SimpleJWebForm<DemoFormJWebFormAPI> form, Model model) {
    if (form.isOk()){
      model.addAttribute("success", "YES");
      //log.info("Form was successfully submitted: " + form.getBean().firstname);
      log.info("Form was successfully submitted: " + form.getStringValue("hello"));
    }
  }

  public static class DemoFormJWebFormAPI implements FormGenerator {

    @Override public Form generateForm() {
      return FormBuilder.simple().typeBuilder(text("hello").label("hello")).build();
    }
  }

  /*
   Demo form.
   Note: This must be "public static" here, because it have to be initialsed programmatically
   See for handling beans: https://github.com/jochen777/jWebForm/blob/master/doc/beans.md
    */
  public static class DemoForm {

    @UseDecoration(label = "Your firstname", helpText = "Don't cheat here!")
    public String firstname="";

    @NotEmpty
    public String lastname="";

    @UseFieldType(SubmitType.class)
    public String submit="";
  }
}
