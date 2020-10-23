package com.globaltech.qna.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AnswerCreateDTO {

	@NotBlank(message = "Question Id cannot be blank")
	private String questionId;
	
	@NotBlank
	@Size(min = 50, max = 500, message = "Answer Text Should lie between 50 and 500")
	private String answerText;
	
	@NotBlank(message = "User Id cannot be blank")
	private String userId;
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
