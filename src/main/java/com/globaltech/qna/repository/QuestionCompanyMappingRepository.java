package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.QuestionCompanyMapping;

@Repository
public interface QuestionCompanyMappingRepository extends JpaRepository<QuestionCompanyMapping, String> {

}
