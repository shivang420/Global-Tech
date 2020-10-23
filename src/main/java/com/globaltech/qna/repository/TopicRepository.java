package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {

	Topic findByTopic(String topicName);

}
