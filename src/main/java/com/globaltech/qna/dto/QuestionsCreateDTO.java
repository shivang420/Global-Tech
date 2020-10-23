package com.globaltech.qna.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QuestionsCreateDTO {

	@NotBlank
	@Size(min = 50, max = 500, message = "Question Text Should lie between 50 and 500")
	private String text;
	
	private List<String> companyIds;
	
	@NotBlank(message = "Sub Topic Id should not be blank")
	private String subTopicId;
	
	private List<String> tagIds;
	
	@NotBlank(message = "User Id should not be blank")
	private String userId;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(List<String> companyIds) {
		this.companyIds = companyIds;
	}
	public String getSubTopicId() {
		return subTopicId;
	}
	public void setSubTopicId(String subTopicId) {
		this.subTopicId = subTopicId;
	}
	public List<String> getTagIds() {
		return tagIds;
	}
	public void setTagIds(List<String> tagIds) {
		this.tagIds = tagIds;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
