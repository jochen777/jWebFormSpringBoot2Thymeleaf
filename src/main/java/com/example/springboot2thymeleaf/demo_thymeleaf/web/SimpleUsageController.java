package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import javax.servlet.ServletRequest;
import javax.validation.constraints.NotEmpty;

import com.example.springboot2thymeleaf.demo_thymeleaf.web.form.FullFormBuilder;
import jwebform.FormResult;
import jwebform.env.Request;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jwebform.field.SubmitType;
import jwebform.integration.ContainerFormRunner;
import jwebform.integration.FormResultAndBean;
import jwebform.integration.FormRunner;
import jwebform.integration.bean2form.annotations.UseDecoration;
import jwebform.integration.bean2form.annotations.UseFieldType;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SimpleUsageController {

  /*
   * an autoconfigured Argument resolver will fill the DemoForm with the request params and put the
   * formModel in the model as "form" and "form_rendered"
   */
  @RequestMapping("/demo")
  public void demo(ContainerFormRunner<DemoForm> form, Model model) {
    if (form.isValid()) {
      model.addAttribute("success", "YES");
      log.info("Form was successfully submitted: " + form.getBean().firstname + " " + form.getBean().lastname);
    }
  }


  /*
   * here the bean is created manually. This is useful in case you have to add contructor arguments
   */
  @RequestMapping("/demo2")
  public void demo2(ContainerFormRunner<FullFormBuilder> form) {
    if (form.isValid()) { 
      log.info("Form was successfully submitted: " + form.getStringValue("textInput"));
    }
  }


  /*
   * Demo form. Note: This must be "public static" here, because it have to be initialsed
   * programmatically See for handling beans:
   * https://github.com/jochen777/jWebForm/blob/master/doc/beans.md
   */
  public static class DemoForm {

    @UseDecoration(label = "firstname.label", helpText = "firstname.helptext")
    public String firstname = "";

    @NotEmpty
    public String lastname = "";

    @UseFieldType(SubmitType.class)
    public String submit = "";
  }
}
