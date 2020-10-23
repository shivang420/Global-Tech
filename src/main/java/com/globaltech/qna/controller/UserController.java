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

import com.globaltech.qna.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "New User")
	@PostMapping(value = "/new")
	public ResponseEntity<?> newUser(@RequestParam(value = "User Name")String userName){
		if(userService.isExistByName(userName)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Duplicate User Name!");
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.newUser(userName));
	}
	
	@ApiOperation(value = "Get All Users")
	@GetMapping(value = "/fetch")
	public ResponseEntity<?> fetchAllUsers(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.fetchAll());
	}
	
	@ApiOperation(value = "Get Users By Id")
	@GetMapping(value = "/fetch/{id}")
	public ResponseEntity<?> fetchUserById(@PathVariable(value = "id") String userId){
		if(userService.isExistById(userId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(userService.fetchById(userId));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Users does not exists!");
	}
	
	@ApiOperation(value = "Update User")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") String userId,
											@RequestParam(value = "User Name")String userName ){
		if(userService.isExistById(userId)) {
			if(userService.isExistByName(userName)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Duplicate User Name!");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(userService.updateUser(userId, userName));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("User does not exists!");
	}
}
