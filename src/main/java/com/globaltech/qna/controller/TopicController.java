package com.globaltech.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globaltech.qna.service.TopicService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@ApiOperation(value = "New Topic")
	@PostMapping(value = "/new")
	public ResponseEntity<?> newTopic(@RequestParam(value = "Topic Name")String topicName){
		if(topicService.isExistByTopicName(topicName)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Duplicate Topic Name!");
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(topicService.newTopic(topicName));
	}
	
	@ApiOperation(value = "New Sub Topic")
	@PostMapping(value = "/{topicId}/subtopic/new")
	public ResponseEntity<?> newSubTopic(@PathVariable(value = "topicId") String topicId,
										@RequestParam(value = "Sub Topic Name")String subTopicName){
		if(topicService.isExistByTopicId(topicId)) {
			if(topicService.isExistBySubTopicName(subTopicName)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Duplicate Sub Topic Name!");
			}
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(topicService.newSubTopic(topicId, subTopicName));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Topic does not exists!");
	}
	
	@ApiOperation(value = "Get All Topics")
	@GetMapping(value = "/fetch")
	public ResponseEntity<?> fetchAllTopics(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(topicService.fetchAllTopics());
	}
	
	@ApiOperation(value = "Get Topic By Id")
	@GetMapping(value = "/{topicId}/topicId")
	public ResponseEntity<?> fetchTopicById(@PathVariable(value = "topicId") String topicId){
		if(topicService.isExistByTopicId(topicId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(topicService.fetchTopicById(topicId));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Topic does not exists!");
	}
	
	@ApiOperation(value = "Update Topic")
	@PutMapping(value = "/{topicId}/update")
	public ResponseEntity<?> updateTopic(@PathVariable(value = "topicId") String topicId,
											@RequestParam(value = "Topic Name")String topicName ){
		if(topicService.isExistByTopicId(topicId)) {
			if(topicService.isExistByTopicName(topicName)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Duplicate Topic Name!");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(topicService.updateTopic(topicId, topicName));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Topic does not exists!");
	}
	
	@ApiOperation(value = "Update Sub Topic")
	@PutMapping(value = "/subtopic/{subTopicId}/update")
	public ResponseEntity<?> updateSubTopic(@PathVariable(value = "subTopicId") String subTopicId,
							@RequestParam(value = "subTopicName")String subTopicName){
		if(topicService.isExistBySubTopicId(subTopicId)) {
			if(topicService.isExistBySubTopicName(subTopicName)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Duplicate Sub Topic Name!");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(topicService.updateSubTopic(subTopicId, subTopicName));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Sub Topic does not exists!");
	}
}
