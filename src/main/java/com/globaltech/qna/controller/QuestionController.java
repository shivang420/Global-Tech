package com.globaltech.qna.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globaltech.qna.dto.QuestionsCreateDTO;
import com.globaltech.qna.service.CompanyService;
import com.globaltech.qna.service.QuestionsService;
import com.globaltech.qna.service.TagsService;
import com.globaltech.qna.service.TopicService;
import com.globaltech.qna.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionsService questionsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private TagsService tagsService;

	@ApiOperation(value = "New Question")
	@PostMapping(value = "/new")
	public ResponseEntity<?> newQuestion(@Valid @RequestBody QuestionsCreateDTO questionsCreateDTO){
		if(! userService.isExistById(questionsCreateDTO.getUserId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User does not exists!");
		}
		if(! topicService.isExistBySubTopicId(questionsCreateDTO.getSubTopicId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Sub Topic does not exists!");
		}
		if(! companyService.isExistByList(questionsCreateDTO.getCompanyIds())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Company does not exists!");
		}
		if(! tagsService.isExistByList(questionsCreateDTO.getTagIds())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Tag does not exists!");
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(questionsService.newQuestion(questionsCreateDTO));
	}
	
	@ApiOperation(value = "Fetch Question By Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getQuestionById(@PathVariable(value = "id") String questionId){
		if(! questionsService.isExist(questionId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Question does not exists!");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(questionsService.fetchQuestionById(questionId));
	}
	
	@ApiOperation(value = "Like A Question")
	@PostMapping(value = "/{id}/like")
	public ResponseEntity<?> likeQuestion(@PathVariable(value = "id") String questionId,
											@RequestParam(value = "userId")String userId){
		if(! questionsService.isExist(questionId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Question does not exists!");
		}
		if(! userService.isExistById(userId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User does not exists!");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(questionsService.likeAQuestion(questionId, userId));
	}
}
