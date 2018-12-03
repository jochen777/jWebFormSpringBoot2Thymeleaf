package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import jwebform.field.HtmlType;
import jwebform.field.LabelType;
import jwebform.field.SubmitType;
import jwebform.integration.ContainerFormRunner;
import jwebform.integration.FormRunner;
import jwebform.integration.bean2form.FormResultWithBean;
import jwebform.integration.bean2form.annotations.UseDecoration;
import jwebform.integration.bean2form.annotations.UseFieldType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Controller
@Slf4j
public class SimpleController {

  /*
   * an autoconfigured Argument resolver will fill the DemoForm with the request params and put the
   * formModel in the model as "form" and "form_rendered"
   */
  @RequestMapping("/simple")
  public void simple(ContainerFormRunner<DemoForm> form, Model model) {
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

    @UseDecoration(label = "firstname.label", helpText = "firstname.helptext")
    public String firstname = "";

    @NotEmpty
    public String lastname = "";


    @UseFieldType(LabelType.class)
    public String label = "I'm a label";

    @UseDecoration(label="checkbox.label", helpText = "checkbox.help")
    public boolean checkBox = true;

    // TODO: Translations unlogisch (Day, Month, Year), label nicht übersetztt.
    @UseDecoration(label="birthday")
    public LocalDate birthday = LocalDate.now();

    @UseFieldType(HtmlType.class)
    public String html = "<hr>";


    @UseFieldType(SubmitType.class)
    public String submit = "";

  }
}
