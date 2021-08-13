package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springboot2thymeleaf.demo_thymeleaf.web.form.FullFormBuilder;

import jwebform.FormResult;
import jwebform.integration.FormRunner;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ComplexUsageController {

	/*
	 * The formRunner holds everything to process a form (for example information about request vars)
	 * Use runWithFormSupplier to process a form, that is prefilled and constructed at runtime. 
	 * Useful for example, when you edit records and content of form must be prefilled with content from database.
	 */
	@RequestMapping("/demoComplex")
	public void demo(FormRunner formRunner, Model model) {
		String atRuntime = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
		FormResult formResult = formRunner.runWithFormSupplier(() -> new FullFormBuilder().generateFormWithDate(atRuntime));
        if (formResult.isValid()) {
			log.info("Form was successfully submitted: " + formResult.getStringValue("textInput")+ " " + 
			formResult.getStringValue("textInput2"));
		}
	}

}
