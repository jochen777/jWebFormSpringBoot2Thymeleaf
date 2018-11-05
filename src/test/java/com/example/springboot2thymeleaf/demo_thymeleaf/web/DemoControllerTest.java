package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import jwebform.FormModel;
import jwebform.integration.DefaultBean2Form;
import jwebform.integration.Bean2Form;
import jwebform.integration.beanvalidation.BeanValidationRuleDeliverer;
import jwebform.integration.beanvalidation.BeanValidationValidator;
import jwebform.integration.beanvalidation.ExternalValidation;
import jwebform.integration.beanvalidation.ExternalValidationDescription;
import jwebform.spring.FormRunnerConfig;
import jwebform.spring.JWebFormProperties;
import jwebform.spring.SimpleJWebForm;
import jwebform.themes.sourcecode.ThemeJavaRenderer;
import jwebform.themes.sourcecode.mapper.StandardMapper;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;
import java.util.*;

import static org.junit.Assert.*;

public class DemoControllerTest {

  // example how to test controllers. Remark: No Spring-Context, no HttpRequest mocking!
  @Test
  public void test_DemoController() {
    DemoController controller = new DemoController();

    Map<Object, Object> model = new HashMap<>();

    SimpleJWebForm<DemoController.DemoFormJWebFormAPI> form = getForm(model);
    ExtendedModelMap modelToController = new ExtendedModelMap();

    controller.demo(form, modelToController);

    assertTrue("Model that the SimpleWebForm fills should contain 2 entries (form and form_rendered)", model.size()==2);

    assertTrue("Model that the SimpleWebForm should contain the key 'form'", model.containsKey("form"));

    assertTrue("Model of the controller should contain the key 'success'", modelToController.containsKey("success"));
  }

  private SimpleJWebForm<DemoController.DemoFormJWebFormAPI> getForm(Map<Object, Object> model) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Bean2Form bean2FromContract = generateBean2Form(validator);

    ThemeJavaRenderer renderer = new ThemeJavaRenderer(
      new StandardMapper(jwebform.themes.sourcecode.BootstrapTheme.instance(msg -> msg)));

    FormRunnerConfig formRunnerConfig = new FormRunnerConfig(renderer, bean2FromContract, FormModel::new, new JWebFormProperties());

    return (SimpleJWebForm<DemoController.DemoFormJWebFormAPI>) new SimpleJWebForm(
      DemoController.DemoForm.class,
      ExampleRequests.exampleSubmittedRequest("lastname", "Pier"),
      ExampleRequests.emptySessionGet(),
      ExampleRequests.emptySessionPut(),
      (t,v) -> model.put(t,v),
      formRunnerConfig
      );
  }

  private Bean2Form generateBean2Form(Validator validator) {
    return new DefaultBean2Form(getBeanValidator(validator), getRuleDeliverer(validator));
  }


  private BeanValidationRuleDeliverer getRuleDeliverer(Validator validator) {
    return (bean, name) -> {
      Set<ExternalValidationDescription> criteraSet = new HashSet<>();
      BeanDescriptor i = validator.getConstraintsForClass(bean.getClass());
      PropertyDescriptor b = i.getConstraintsForProperty(name);
      if (b != null) {
        Set<ConstraintDescriptor<?>> z = b.getConstraintDescriptors();
        z.forEach(constraintDesc -> {
          criteraSet.add(new ExternalValidationDescription(
            constraintDesc.getAnnotation().annotationType().getSimpleName(),
            constraintDesc.getAttributes()));

        });
      }
      return criteraSet;
    };
  }

  private BeanValidationValidator getBeanValidator(Validator validator) {

    return (b) -> {
      Set<ConstraintViolation<Object>> vals = validator.validate(b);
      List<ExternalValidation> externalVals = new ArrayList<>();
      vals.forEach(constr -> {
        ExternalValidation e =
          new ExternalValidation(constr.getPropertyPath().toString(), constr.getMessage());
        externalVals.add(e);
      });

      return externalVals;
    };
  }


}
