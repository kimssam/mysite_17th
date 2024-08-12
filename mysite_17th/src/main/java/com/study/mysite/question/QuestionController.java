package com.study.mysite.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.mysite.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	
	//localhost:8080/question/list 이 요청이 오면 게시판 목록 페이지가 떠야 한다!
	
	//게시판 리스트로 이동
	@GetMapping("/list")
	public String list(Model model) {
//		List<Question> questionList = this.questionRepository.findAll();
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		
		return "question_list";
		//자바 코드를 삽입할 수 있는 html 형식의 파일인 템플릿이 필요=>타임리프 Thymeleaf를 사용예정!
	}
	
	//상세페이지로 이동
	@GetMapping("/detail/{userid}")
	public String detail(Model model, @PathVariable("userid") Integer id, AnswerForm answerForm) { //Integer타입의 id 컬럼값과 연결하여 @PathVariable("변수명")으로 변경한다!!=>사용자 요청 url의 변수명으로 사용가능하다!
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question );
		return "question_detail";
		
	}
	
	//질문 등록 페이지로 이동
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	/*@PostMapping("/create")
	public String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list";
	}*/
	
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
	}
}



