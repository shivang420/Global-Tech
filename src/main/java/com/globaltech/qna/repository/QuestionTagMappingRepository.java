package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.QuestionTagsMapping;

@Repository
public interface QuestionTagMappingRepository extends JpaRepository<QuestionTagsMapping, String> {

}
