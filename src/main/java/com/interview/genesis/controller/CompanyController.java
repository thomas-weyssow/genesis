package com.interview.genesis.controller;

import com.interview.genesis.dto.CreateCompanyRequest;
import com.interview.genesis.service.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public void create(@RequestBody CreateCompanyRequest createCompanyRequest) {
        companyService.create(createCompanyRequest);
    }
}
