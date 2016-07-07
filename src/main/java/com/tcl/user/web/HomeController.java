package com.tcl.user.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

	@RequestMapping("/ping")
	public String test()
	{
		return "userdataservice v1.1";
	}
}
