package com.globaltech.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globaltech.qna.model.User;
import com.globaltech.qna.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public String newUser(String userName) {
		User user = new User();
		user.setUserName(userName);
		return userRepository.save(user).getId();
	}
	
	public boolean isExistByName(String userName) {
		User user = userRepository.findByUserName(userName);
		if(user == null) {
			return false;
		}
		return true;
	}
	
	public boolean isExistById(String userId) {
		return userRepository.existsById(userId);
	}

	public List<User> fetchAll() {
		return userRepository.findAll();
	}

	public User fetchById(String userId) {
		return userRepository.getOne(userId);
	}

	public String updateUser(String userId, String userName) {
		User user = userRepository.getOne(userId);
		user.setUserName(userName);
		return userRepository.save(user).getId();
	}
}
