package com.study.mysite;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.mysite.question.Question;
import com.study.mysite.question.QuestionRepository;

@SpringBootTest
class Mysite17thApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;//테스트 코드에서는 생성자를 통한 객체 주입방식을 지원하지 않아서 @Autowired를 사용했지만 순환참조 문제이슈가 발생할 수 있으므로 실제 코드에서는 setter나 생성자 주입방식을 사용하는 것을 권장한다.
	
	@Test
	void testJpa() {
		/*
		Question q1 = new Question();
		q1.setSubject("JPA가 무엇인가요?");
		q1.setContent("알고 싶어요");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1); //첫번째 질문 저장
		
		Question q2 = new Question();
		q2.setSubject("ORM이 무엇인가요?");
		q2.setContent("궁금합니다.");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); //두번째 질문 저장
		
		Question q3 = new Question();
		q3.setSubject("hibernate란 무엇인가요?");
		q3.setContent("궁금합니다.");
		q3.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q3); //두번째 질문 저장
		
		List<Question> all = this.questionRepository.findAll();
		assertEquals(3, all.size());
		
		Question q = all.get(0);
		assertEquals("JPA가 무엇인가요?", q.getSubject());
		
		
		Question q = this.questionRepository.findBySubject("ORM이 무엇인가요?");
		assertEquals(3, q.getId());
		
		
		Question q = this.questionRepository.findBySubjectAndContent("ORM이 무엇인가요?", "궁금합니다.");
		assertEquals(3, q.getId());
		
		
		List<Question> qList = this.questionRepository.findBySubjectLike("%무엇%");
		this.questionRepository.findAll();
		*/
		
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("무엇이든 궁금하면 물어보세요");
		this.questionRepository.save(q);
	}

}







