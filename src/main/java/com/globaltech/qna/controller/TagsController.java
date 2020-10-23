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

import com.globaltech.qna.service.TagsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tags")
public class TagsController {
	
	@Autowired
	private TagsService tagsService;

	@ApiOperation(value = "New Tag")
	@PostMapping(value = "/new")
	public ResponseEntity<?> newTag(@RequestParam(value = "Tag Name")String tagName){
		if(tagsService.isExistByName(tagName)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Duplicate Tag Name!");
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(tagsService.newTag(tagName));
	}
	
	@ApiOperation(value = "Get All Tags")
	@GetMapping(value = "/fetch")
	public ResponseEntity<?> fetchAllTags(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(tagsService.fetchAll());
	}
	
	@ApiOperation(value = "Get Tags By Id")
	@GetMapping(value = "/fetch/{id}")
	public ResponseEntity<?> fetchTagsById(@PathVariable(value = "id") String tagId){
		if(tagsService.isExistById(tagId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(tagsService.fetchById(tagId));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Tag does not exists!");
	}
	
	@ApiOperation(value = "Update Tag")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateCompany(@PathVariable(value = "id") String tagId,
											@RequestParam(value = "Tag Name")String tagName ){
		if(tagsService.isExistById(tagId)) {
			if(tagsService.isExistByName(tagName)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Duplicate Tag Name!");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(tagsService.updateTags(tagId, tagName));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Tag does not exists!");
	}
}
