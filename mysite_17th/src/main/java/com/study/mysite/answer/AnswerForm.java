package com.study.mysite.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
	@NotEmpty(message="답변내용을 입력하셔야 합니다.")
	private String content;
}
