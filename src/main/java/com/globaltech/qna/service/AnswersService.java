package com.globaltech.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globaltech.qna.dto.AnswerCreateDTO;
import com.globaltech.qna.dto.CommentCreateDTO;
import com.globaltech.qna.model.AnswerComments;
import com.globaltech.qna.model.AnswerLikes;
import com.globaltech.qna.model.Answers;
import com.globaltech.qna.repository.AnswerCommentsRepository;
import com.globaltech.qna.repository.AnswerLikesRepository;
import com.globaltech.qna.repository.AnswerRepository;

@Service
public class AnswersService {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private AnswerLikesRepository answerLikesRepository;
	
	@Autowired
	private AnswerCommentsRepository answerCommentsRepository;
	
	public String newAnswer(AnswerCreateDTO answerCreateDTO) {
		Answers answer = new Answers();
		answer.setAnswerText(answerCreateDTO.getAnswerText());
		answer.setQuestionId(answerCreateDTO.getQuestionId());
		answer.setUserId(answerCreateDTO.getUserId());
		return answerRepository.save(answer).getId();
	}

	public boolean isExist(String answerId) {
		return answerRepository.existsById(answerId);
	}

	public String likeAnswer(String answerId, String userId) {
		AnswerLikes answerLike = new AnswerLikes();
		answerLike.setAnswerId(answerId);
		answerLike.setUserId(userId);
		return answerLikesRepository.save(answerLike).getId();
	}
	
	public String newComment(CommentCreateDTO commentCreateDTO) {
		AnswerComments answerComment = new AnswerComments();
		answerComment.setAnswerId(commentCreateDTO.getAnswerId());
		answerComment.setUserId(commentCreateDTO.getUserId());
		answerComment.setUserId(commentCreateDTO.getCommentText());
		return answerCommentsRepository.save(answerComment).getId();
	}
}
