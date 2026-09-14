package com.interview.genesis.service;

import com.interview.genesis.dto.company.CompanyResponse;
import com.interview.genesis.dto.company.CreateCompanyRequest;
import com.interview.genesis.dto.company.UpdateCompanyRequest;
import com.interview.genesis.exception.CompanyNotFoundException;
import com.interview.genesis.model.Company;
import com.interview.genesis.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyResponse> all() {

        List<Company> companies = companyRepository.findAll();

        return companies
            .stream()
            .map(CompanyResponse::from)
            .toList()
        ;
    }

    public CompanyResponse create(CreateCompanyRequest request) {

        Company company = new Company(
            request.address(),
            request.vat()
        );

        companyRepository.save(company);
        return CompanyResponse.from(company);
    }

    public CompanyResponse update(UpdateCompanyRequest request, Long id) {

        Company company = companyRepository
            .findById(id)
            .orElseThrow(() -> new CompanyNotFoundException(id))
        ;

        if (request.address() != null)
            company.setAddress(request.address());
        if (request.vat() != null)
            company.setVat(request.vat());

        return CompanyResponse.from(company);
    }

    public void delete(Long id) {

        Company company = companyRepository
            .findById(id)
            .orElseThrow(() -> new CompanyNotFoundException(id))
        ;

        company
            .getContacts()
            .forEach(c -> c.getCompanies().remove(company))
        ;

        companyRepository.delete(company);
    }
}
