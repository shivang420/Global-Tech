package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.AnswerComments;

@Repository
public interface AnswerCommentsRepository extends JpaRepository<AnswerComments, String> {

}
