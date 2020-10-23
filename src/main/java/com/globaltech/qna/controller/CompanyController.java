package com.globaltech.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globaltech.qna.service.CompanyService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@ApiOperation(value = "New Company")
	@PostMapping(value = "/new")
	public ResponseEntity<?> newCompany(@RequestParam(value = "Company Name")String companyName){
		if(companyService.isExistByName(companyName)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Duplicate Company Name!");
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(companyService.newCompany(companyName));
	}
	
	@ApiOperation(value = "Get All Companies")
	@GetMapping(value = "/fetch")
	public ResponseEntity<?> fetchAllCompanies(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(companyService.fetchAll());
	}
	
	@ApiOperation(value = "Get Company By Id")
	@GetMapping(value = "/fetch/{id}")
	public ResponseEntity<?> fetchCompanyById(@PathVariable(value = "id") String companyId){
		if(companyService.isExistById(companyId)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(companyService.fetchById(companyId));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Company does not exists!");
	}
	
	@ApiOperation(value = "Update Company")
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateCompany(@PathVariable(value = "id") String companyId,
											@RequestParam(value = "Company Name")String companyName ){
		if(companyService.isExistById(companyId)) {
			if(companyService.isExistByName(companyName)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body("Duplicate Company Name!");
			}
			return ResponseEntity.status(HttpStatus.OK)
					.body(companyService.updateCompany(companyId, companyName));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Company does not exists!");
	}
}
