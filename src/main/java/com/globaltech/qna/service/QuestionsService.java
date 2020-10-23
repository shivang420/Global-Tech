package com.globaltech.qna.service;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globaltech.qna.dto.QuestionsCreateDTO;
import com.globaltech.qna.model.QuestionCompanyMapping;
import com.globaltech.qna.model.QuestionLikes;
import com.globaltech.qna.model.QuestionTagsMapping;
import com.globaltech.qna.model.Questions;
import com.globaltech.qna.repository.QuestionCompanyMappingRepository;
import com.globaltech.qna.repository.QuestionLikesRepository;
import com.globaltech.qna.repository.QuestionTagMappingRepository;
import com.globaltech.qna.repository.QuestionsRepository;

@Service
public class QuestionsService {
	
	@Autowired
	private QuestionsRepository questionsRepository;
	
	@Autowired
	private QuestionCompanyMappingRepository questionCompanyMappingRepository;
	
	@Autowired
	private QuestionTagMappingRepository questionTagMappingRepository;
	
	@Autowired
	private QuestionLikesRepository questionLikesRepository;

	public String newQuestion(QuestionsCreateDTO questionsCreateDTO) {
		Questions question = new Questions();
		question.setText(questionsCreateDTO.getText());
		question.setSubTopicId(questionsCreateDTO.getSubTopicId());
		question.setUserId(questionsCreateDTO.getUserId());
		String questionId = questionsRepository.save(question).getId();
		ListIterator<String> companyIterator = questionsCreateDTO.getCompanyIds().listIterator();
		while (companyIterator.hasNext()) {
			QuestionCompanyMapping questionCompanyMapping = new QuestionCompanyMapping();
			questionCompanyMapping.setCompanyId(companyIterator.next());
			questionCompanyMapping.setQuestionId(questionId);
			questionCompanyMappingRepository.save(questionCompanyMapping);
		}
		ListIterator<String> tagIterator = questionsCreateDTO.getTagIds().listIterator();
		while (tagIterator.hasNext()) {
			QuestionTagsMapping questionTagsMapping = new QuestionTagsMapping();
			questionTagsMapping.setTagId(tagIterator.next());
			questionTagsMapping.setQuestionId(questionId);
			questionTagMappingRepository.save(questionTagsMapping);
		}
		return questionId;
	}

	public Questions fetchQuestionById(String questionId) {
		return questionsRepository.getOne(questionId);
	}
	
	public boolean isExist(String questionId) {
		return questionsRepository.existsById(questionId);
	}
	
	public String likeAQuestion(String questionId, String userId) {
		QuestionLikes questionLike = new QuestionLikes();
		questionLike.setQuestionId(questionId);
		questionLike.setUserId(userId);
		return questionLikesRepository.save(questionLike).getId();
	}
}
