package com.example.springboot2thymeleaf.demo_thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {

	 @RequestMapping("/")
	  public ModelAndView start() {
		 return new ModelAndView("start");
	  }

}
