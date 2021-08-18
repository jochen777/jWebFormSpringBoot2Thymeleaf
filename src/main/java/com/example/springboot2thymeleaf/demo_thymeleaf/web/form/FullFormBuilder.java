package com.example.springboot2thymeleaf.demo_thymeleaf.web.form;

import jwebform.Form;
import jwebform.FormBuilder;
import jwebform.processor.FormGenerator;
import jwebform.validation.Criterion;
import jwebform.validation.criteria.Criteria;

import java.time.LocalDate;

import static jwebform.field.builder.BuildInType.*;

public class FullFormBuilder implements FormGenerator {

    Criterion req = Criteria.required();
    
    

    @Override
    public Form generateForm() {
    	return generateFormWithDate("Peter\"Paul");
    }



	public Form generateFormWithDate(String atRuntime) {
        return FormBuilder.simple().typeBuilder(
        		xsrfProtection(),
                text           ("textInput", atRuntime).
                
                        criteria     (req, Criteria.maxLength(3)).
                        label        ("TextInputLabel"),

                text           ("textInput2", "Peter\"Paul").
                        criteria     (req).
                        label        ("TextInputLabel2").
                        helpText     ("Help-Text").
                        placeholder  ("Placeholder"),
                label          ("lbl"). label("TextInputLabel2"),


                html           ("<strong>HTML</strong>"),

                hidden         ("hddn", "hddn-value"),
                number         ("nbr", 5).
                        criteria     (req).
                        label        ("nbr-label").
                        helpText     ("nrb-help"),
                checkbox       ("chk", true).
                        criteria     (req).
                        label        ("chk-label").
                        helpText     ("chk_help"),
                
               textArea       ("area", "Area-Prebuild").
                        criteria     (req).
                        label        ("Area").
                        helpText     ("Area-Help").
                        placeholder  ("Area-Placeholder"),
               password       ("pssword").
                        label        ("Password").
                        helpText     ("password-help")
                        ,
                select         ("gender", "", new String[] {"m", "f"}, new String[] {"Male", "Female"}).
                        label        ("Gender"),
                radio          ("radio", "1", new String[] {"1", "2"}, new String[] {"yes", "no"})
                        .label       ("Radio"),
                upload         ("upld").
                        label        ("Upload"),
                textDate       ("dateInput", LocalDate.of(2017, 7, 4)).
                        criteria     (req).
                        label        ("date_input").
                        helpText     ("datehelptext"),
              
                       

                        
                submit("ok").
                        label("ok")

                ).build();
	}
}
