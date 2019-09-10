package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController2 {

	@RequestMapping("/demo1")
	public String demo1() {
		return "demo1";
	}

	@RequestMapping("/demo2")
	public @ResponseBody String demo2() {
		return "Hello demo2";
	}

}

