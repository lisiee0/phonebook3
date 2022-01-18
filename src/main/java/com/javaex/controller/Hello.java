package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
	
	@RequestMapping("/test")
	public String TestPrint() {
		System.out.println("TestPrint");
		
		return "/WEB-INF/views/Test.jsp";
	}
	
	@RequestMapping("/aaa")
	public void TestPrint2() {
		System.out.println("TestPrint2");
	}
	
}