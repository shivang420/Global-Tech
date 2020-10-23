package com.globaltech.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globaltech.qna.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

	Company findByCompanyName(String companyName);

}
