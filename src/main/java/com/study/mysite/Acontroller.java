package com.study.mysite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Acontroller {
	@GetMapping("/a")
	@ResponseBody
	public String helloSpring() {
		return "Devtools과 LiveReload를 사용하면 서버를 껐다 키지 않아도 편리하게 자동으로 서버가 켜지고 브라우저가 refresh 됩니다.";
	}
}
