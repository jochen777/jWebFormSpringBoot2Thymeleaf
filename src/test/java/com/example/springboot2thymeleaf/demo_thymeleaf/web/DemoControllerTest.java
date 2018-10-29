package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import jwebform.spring.JWebFormProperties;
import jwebform.spring.SimpleJWebForm;
import jwebform.themes.sourcecode.ThemeJavaRenderer;
import jwebform.themes.sourcecode.mapper.StandardMapper;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DemoControllerTest {

  // example how to test controllers. Remark: No Spring-Context, no HttpRequest mocking!
  @Test
  public void test_DemoController() {
    DemoController controller = new DemoController();


    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Map<Object, Object> model = new HashMap<>();

    ThemeJavaRenderer renderer = new ThemeJavaRenderer(
      new StandardMapper(jwebform.themes.sourcecode.BootstrapTheme.instance(msg -> msg)));

    SimpleJWebForm<DemoController.DemoForm> form = new SimpleJWebForm(
      DemoController.DemoForm.class,
      ExampleRequests.exampleSubmittedRequest("firstname", "Jochen"),
      ExampleRequests.emptySessionGet(),
      ExampleRequests.emptySessionPut(),
      (t,v) -> model.put(t,v),
      validator,
      new JWebFormProperties(),
      renderer
      );
    ExtendedModelMap modelToController = new ExtendedModelMap();
    controller.demo(form, modelToController);

    assertTrue("Model that the SimpleWebForm fills should contain 2 entries (form and form_rendered)", model.size()==2);

    assertTrue("Model that the SimpleWebForm should contain the key 'form'", model.containsKey("form"));

    assertTrue("Model of the controller should contain the key 'success'", modelToController.containsKey("success"));
  }


}
