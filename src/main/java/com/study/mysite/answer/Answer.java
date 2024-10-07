package com.study.mysite.answer;

import java.time.LocalDateTime;
import java.util.Set;

import com.study.mysite.question.Question;
import com.study.mysite.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany
	Set<SiteUser> voter; //다대다 관계(하나의 질문에 여러명이 좋아요를 클릭가능하다. 한명의 유저는 여러 질문에 좋아요를 클릭가능하다, 하나의 질문에 좋아요를 클릭하면 중복 클릭 안된다.)
}
