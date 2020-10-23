package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.Answers;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, String> {

}
