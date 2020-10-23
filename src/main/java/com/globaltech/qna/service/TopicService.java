package com.globaltech.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globaltech.qna.model.SubTopic;
import com.globaltech.qna.model.Topic;
import com.globaltech.qna.repository.SubTopicRepository;
import com.globaltech.qna.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private SubTopicRepository subTopicRepository;
	
	public String newTopic(String topicName) {
		Topic topic = new Topic();
		topic.setTopic(topicName);
		return topicRepository.save(topic).getId();
	}
	
	public String newSubTopic(String topicId, String subTopicName) {
		SubTopic subTopic = new SubTopic();
		subTopic.setSubTopic(subTopicName);
		subTopic.setTopicId(topicId);
		return subTopicRepository.save(subTopic).getId();
	}

	public boolean isExistByTopicName(String topicName) {
		Topic topic = topicRepository.findByTopic(topicName);
		if(topic == null) {
			return false;
		}
		return true;
	}
	
	public boolean isExistBySubTopicName(String subTopicName) {
		SubTopic subTopic = subTopicRepository.findBySubTopic(subTopicName);
		if(subTopic == null) {
			return false;
		}
		return true;
	}

	public boolean isExistByTopicId(String topicId) {
		return topicRepository.existsById(topicId);
	}
	
	public boolean isExistBySubTopicId(String subTopicId) {
		return subTopicRepository.existsById(subTopicId);
	}

	public List<Topic> fetchAllTopics() {
		return topicRepository.findAll();
	}
	
	public List<SubTopic> fetchAllSubTopics() {
		return subTopicRepository.findAll();
	}
	
	public Topic fetchTopicById(String topicId) {
		return topicRepository.getOne(topicId);
	}
	
	public SubTopic fetchSubTopicById(String subTopicId) {
		return subTopicRepository.getOne(subTopicId);
	}

	public String updateTopic(String topicId, String topicName) {
		Topic topic = topicRepository.getOne(topicId);
		topic.setTopic(topicName);
		return topicRepository.save(topic).getId();
	}
	
	public String updateSubTopic(String subTopicId, String subTopicName) {
		SubTopic subTopic = subTopicRepository.getOne(subTopicId);
		subTopic.setSubTopic(subTopicName);
		return subTopicRepository.save(subTopic).getId();
	}
}
