package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.QuestionLikes;

@Repository
public interface QuestionLikesRepository extends JpaRepository<QuestionLikes, String> {

}
