package com.study.mysite.answer;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.mysite.question.Question;
import com.study.mysite.question.QuestionService;
import com.study.mysite.user.SiteUser;
import com.study.mysite.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PostMapping("/create/{questionId}")
	public String createAnswer(Model model, @PathVariable("questionId") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		//현재 로그인 한 사용자의 정보를 알려면 스프링 시큐리티가 제공하는 Principal(본인) 객체를 사용해야 한다.Principal의 getName()를 이용하면 현재 로그인한 사용자ID를 알 수 있다.
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		this.answerService.create(question, answerForm.getContent(),siteUser);
		return String.format("redirect:/question/detail/%s", id);
	}
	
	
	
	
	
}
