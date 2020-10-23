package com.globaltech.qna.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globaltech.qna.model.Company;
import com.globaltech.qna.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	public String newCompany(String companyName) {
		Company company = new Company();
		company.setCompanyName(companyName);
		return companyRepository.save(company).getId();
	}
	
	public boolean isExistByName(String companyName) {
		Company company = companyRepository.findByCompanyName(companyName);
		if(company == null) {
			return false;
		}
		return true;
	}
	
	public boolean isExistById(String companyId) {
		return companyRepository.existsById(companyId);
	}
	
	public boolean isExistByList(List<String> companyIds) {
		ListIterator<String> listIterator = companyIds.listIterator();
		while (listIterator.hasNext()) {
			if(! companyRepository.existsById(listIterator.next())) {
				return false;
			}
		}
		return true;
	}

	public List<Company> fetchAll() {
		return companyRepository.findAll();
	}

	public Company fetchById(String companyId) {
		return companyRepository.getOne(companyId);
	}

	public String updateCompany(String companyId, String companyName) {
		Company company = companyRepository.getOne(companyId);
		company.setCompanyName(companyName);
		return companyRepository.save(company).getId();
	}
	
}
