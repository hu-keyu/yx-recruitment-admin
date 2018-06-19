package org.jypj.dev.controller;

import javax.annotation.Resource;

import org.jypj.dev.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 考生报考自动审核控制器
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/test2")
public class TestController2 {
	@Resource
	private TestService testService;
	
	@RequestMapping(value = "test")
	public String toTest(Model model) {
		model.addAttribute("themes", testService.selectAllTheme());
		model.addAttribute("postsets", testService.selectAllPostset());
		model.addAttribute("dictionaries", testService.selectAllSchool());
		return "/test/test.vm";
	}
	
	@RequestMapping(value = "test1")
	@ResponseBody
	public String test1() {
		return "/test/test.vm";
	}

}
