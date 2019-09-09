package com.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class MainController {

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("exampleLayout.html")
	public ModelAndView exampleLayout(ModelAndView mav) {
		mav.setViewName("exampleLayout");
		return mav;
	}

	@RequestMapping("content.html")
	public ModelAndView content(ModelAndView mav) {
		mav.setViewName("content");
		return mav;
	}

//	@Controller
//	@RequestMapping("/content")
//    public String content() {
//        return "content";
//    }

}
