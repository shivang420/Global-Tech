package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.SubTopic;

@Repository
public interface SubTopicRepository extends JpaRepository<SubTopic, String> {

	SubTopic findBySubTopic(String subTopicName);

}
