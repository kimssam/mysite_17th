package com.study.mysite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Acontroller {
	@GetMapping("/a")
	@ResponseBody
	public String helloSpring() {
		return "Hello Devtools And LiveReload";
	}
}
