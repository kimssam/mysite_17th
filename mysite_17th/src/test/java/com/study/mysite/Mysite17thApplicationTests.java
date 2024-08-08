package com.study.mysite;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.mysite.question.Question;
import com.study.mysite.question.QuestionRepository;

@SpringBootTest
class Mysite17thApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("JPA가 무엇인가요?");
		q1.setContent("알고 싶어요");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1); //첫번째 질문 저장
	}

}







