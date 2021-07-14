package com.example.springboot2thymeleaf.demo_thymeleaf.web.form;

import jwebform.Form;
import jwebform.FormBuilder;
import jwebform.processor.FormGenerator;
import jwebform.validation.criteria.Criteria;

import static jwebform.field.builder.BuildInType.*;

public class FullFormBuilder implements FormGenerator {
    @Override
    public Form generateForm() {
            return FormBuilder.simple().typeBuilder(
                    text("name").
                            label("name").
                            criteria(Criteria.required()),
                    checkbox("optin").
                            label("checkbox.label"),
                    submit("ok").
                            label("ok")
            ).build();
    }
}
