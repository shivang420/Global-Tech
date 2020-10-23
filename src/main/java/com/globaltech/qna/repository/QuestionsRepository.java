package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, String> {

}
