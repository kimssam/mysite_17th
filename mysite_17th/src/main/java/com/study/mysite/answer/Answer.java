package com.study.mysite.answer;

import java.time.LocalDateTime;

import com.study.mysite.question.Question;
import com.study.mysite.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Question question; //외래키(FK) 관계 : Answer(자식:N)<-Question(부모:1) 
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate; //질문수정일시 db에서는 modify_date
}
