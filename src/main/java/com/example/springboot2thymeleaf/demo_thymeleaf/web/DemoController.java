package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import javax.validation.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jwebform.field.SubmitType;
import jwebform.integration.ContainerFormRunner;
import jwebform.integration.bean2form.annotations.UseDecoration;
import jwebform.integration.bean2form.annotations.UseFieldType;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DemoController {

  /*
   * an autoconfigured Argument resolver will fill the DemoForm with the request params and put the
   * formModel in the model as "form" and "form_rendered"
   */
  @RequestMapping("/demo")
  public void demo(ContainerFormRunner<DemoForm> form, Model model) {
    if (form.isValid()) {
      model.addAttribute("success", "YES");
      log.info("Form was successfully submitted: " + form.getBean().firstname);
    }
  }

  /*
   * Demo form. Note: This must be "public static" here, because it have to be initialsed
   * programmatically See for handling beans:
   * https://github.com/jochen777/jWebForm/blob/master/doc/beans.md
   */
  public static class DemoForm {

    @UseDecoration(label = "Your firstname", helpText = "Don't cheat here!")
    public String firstname = "";

    @NotEmpty
    public String lastname = "";

    @UseFieldType(SubmitType.class)
    public String submit = "";
  }
}
