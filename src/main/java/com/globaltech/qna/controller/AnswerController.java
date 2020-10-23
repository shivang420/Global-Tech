package com.globaltech.qna.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globaltech.qna.dto.AnswerCreateDTO;
import com.globaltech.qna.dto.CommentCreateDTO;
import com.globaltech.qna.service.AnswersService;
import com.globaltech.qna.service.QuestionsService;
import com.globaltech.qna.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private AnswersService answersService;
	
	@Autowired
	private QuestionsService questionsService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "New Answer")
	@PostMapping(value = "/new")
	public ResponseEntity<?> newAnswer(@Valid @RequestBody AnswerCreateDTO answerCreateDTO){
		if(! questionsService.isExist(answerCreateDTO.getQuestionId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Question does not exists!");
		}
		if(! userService.isExistById(answerCreateDTO.getUserId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User does not exists!");
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(answersService.newAnswer(answerCreateDTO));
	}
	
	@ApiOperation(value = "Like A Answer")
	@PostMapping(value = "/{id}/like")
	public ResponseEntity<?> likeQuestion(@PathVariable(value = "id") String answerId,
											@RequestParam(value = "userId")String userId){
		if(! answersService.isExist(answerId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Answer does not exists!");
		}
		if(! userService.isExistById(userId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User does not exists!");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(answersService.likeAnswer(answerId, userId));
	}
	
	@ApiOperation(value = "New Comment")
	@PostMapping(value = "/{id}/comment/new")
	public ResponseEntity<?> newComment(@Valid @RequestBody CommentCreateDTO commentCreateDTO){
		if(! answersService.isExist(commentCreateDTO.getAnswerId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Answer does not exists!");
		}
		if(! userService.isExistById(commentCreateDTO.getUserId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User does not exists!");
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(answersService.newComment(commentCreateDTO));
	}
}
