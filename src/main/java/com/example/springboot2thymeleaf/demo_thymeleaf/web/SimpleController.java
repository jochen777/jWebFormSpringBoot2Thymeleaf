package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import jwebform.field.HtmlType;
import jwebform.field.LabelType;
import jwebform.field.SubmitType;
import jwebform.integration.AjaxResult;
import jwebform.integration.ContainerFormRunner;
import jwebform.integration.bean2form.annotations.UseDecoration;
import jwebform.integration.bean2form.annotations.UseFieldType;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SimpleController {

  /*
   * an autoconfigured Argument resolver will fill the DemoForm with the request params and put the
   * formModel in the model as "form" and "form_rendered"
   */
  @RequestMapping("/simple")
  public String simple(ContainerFormRunner<DemoForm> form, Model model) {
    if (form.isValid()) {
      model.addAttribute("success", "YES");
      log.info("Form was successfully submitted: " + form.getBean().firstname);
    }
    return "simple_ajax";
  }

  // Example for ajax request.
  @RequestMapping("/simple_ajax")
  public @ResponseBody AjaxResult simpleAjax(ContainerFormRunner<DemoForm> form) {
    if (form.isValid()) {
      log.info("Form was successfully submitted: " + form.getBean().firstname);
    }
    return form.getAjaxResult();
  }


  /*
   * Demo form. Note: This must be "public static" here, because it have to be initialsed
   * programmatically See for handling beans:
   * https://github.com/jochen777/jWebForm/blob/master/doc/beans.md
   */
  public static class DemoForm {

    @UseDecoration(label = "firstname.label", helpText = "firstname.helptext")
    @Size(min = 2, max = 200, message = "The name must be between 10 and 200 chars")
    public String firstname = "";

    @NotEmpty
    public String lastname = "";


    @UseFieldType(LabelType.class)
    public String label = "I'm a label";

    @UseDecoration(label = "checkbox.label", helpText = "checkbox.help")
    public boolean checkBox = true;

    @UseDecoration(label = "birthday")
    public LocalDate birthday = LocalDate.now();

    @UseFieldType(HtmlType.class)
    public String html = "<hr>";


    @UseFieldType(SubmitType.class)
    public String submit = "";

  }
}
