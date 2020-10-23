package com.globaltech.qna.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentCreateDTO {

	@NotBlank(message = "Answer Id cannot be blank")
	private String answerId;
	
	@NotBlank(message = "User Id cannot be blank")
	private String userId;
	
	@NotBlank
	@Size(min = 50, max = 500, message = "Comment Text Should lie between 50 and 500")
	private String commentText;
	
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
}
