package com.interview.genesis.service;

import com.interview.genesis.dto.CreateCompanyRequest;
import com.interview.genesis.model.Company;
import com.interview.genesis.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void create(CreateCompanyRequest request) {

        Company company = new Company(
            request.address(),
            request.vat()
        );

        companyRepository.save(company);
    }
}
