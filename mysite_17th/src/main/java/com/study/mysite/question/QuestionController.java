package com.study.mysite.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.study.mysite.answer.AnswerForm;
import com.study.mysite.user.SiteUser;
import com.study.mysite.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final UserService userService;
	
	//localhost:8080/question/list 이 요청이 오면 게시판 목록 페이지가 떠야 한다!
	
	//게시판 리스트로 이동
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page",defaultValue="0") int page) {
//		List<Question> questionList = this.questionRepository.findAll();
		/*List<Question> questionList = this.questionService.getList();*/
		Page<Question> paging = this.questionService.getList(page);
//		model.addAttribute("questionList", questionList);
		model.addAttribute("paging", paging);
		
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
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	/*@PostMapping("/create")
	public String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content) {
		this.questionService.create(subject, content);
		return "redirect:/question/list";
	}*/
	
	@PreAuthorize("isAuthenticated()")
	//isAuthenticated(인증):사용자가 누구인지 확인하는 과정
	//Authorize(인가):인증된 사용자가 특정 자원에 접근할 수 있는 권한이 있는지 확인하는 과정
	//@PreAuthorize("isAuthenticated()") 애너테이션이 있으면 메소드 동작시키기 전에 회원 로그인했는지 먼저 확인하고 로그인 안했으면 로그인 페이지로 보낸다.
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult,Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
		return "redirect:/question/list";
	}
	
	//질문을 수정하는 메소드
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	
	//질문 수정 요청시
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm,BindingResult bindingResult,  @PathVariable("id") Integer id, Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
		
		return String.format("redirect:/question/detail/%s", id);
	}
	
	//질문 삭제 요청시
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.questionService.delete(question);
		return "redirect:/";
	}
	
	//좋아요 클릭시	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String questionVote(Principal principal, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.vote(question, siteUser);
		//db에 저장
		
		return String.format("redirect:/question/detail/%s", id);
		
	}
	
}



