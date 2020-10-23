package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.AnswerLikes;

@Repository
public interface AnswerLikesRepository extends JpaRepository<AnswerLikes, String> {

}
