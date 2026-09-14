package com.interview.genesis.service;

import com.interview.genesis.dto.company.CompanyResponse;
import com.interview.genesis.dto.company.CreateCompanyRequest;
import com.interview.genesis.dto.company.UpdateCompanyRequest;
import com.interview.genesis.exception.CompanyNotFoundException;
import com.interview.genesis.exception.ContactNotFoundException;
import com.interview.genesis.model.Company;
import com.interview.genesis.model.Contact;
import com.interview.genesis.repository.CompanyRepository;
import com.interview.genesis.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ContactRepository contactRepository;

    public CompanyService(CompanyRepository companyRepository, ContactRepository contactRepository) {
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
    }

    @Transactional(readOnly = true)
    public List<CompanyResponse> findAll() {

        List<Company> companies = companyRepository.findAllWithContacts();

        return companies
            .stream()
            .map(CompanyResponse::from)
            .toList()
        ;
    }

    @Transactional(readOnly = true)
    public CompanyResponse findByVat(String vat) {

        Company company = companyRepository
            .findByVat(vat)
            .orElseThrow(() -> new CompanyNotFoundException(vat))
        ;

        return CompanyResponse.from(company);
    }

    @Transactional
    public CompanyResponse create(CreateCompanyRequest request) {

        Company company = new Company(
            request.address(),
            request.vat()
        );

        companyRepository.save(company);
        return CompanyResponse.from(company);
    }

    @Transactional
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

    @Transactional
    public CompanyResponse addContact(Long companyId, Long contactId) {

        Company company = companyRepository
            .findById(companyId)
            .orElseThrow(() -> new CompanyNotFoundException(companyId))
        ;

        Contact contact = contactRepository
            .findById(contactId)
            .orElseThrow(() -> new ContactNotFoundException(contactId))
        ;

        if (!company.getContacts().contains(contact)) {
            // owning side: this is what writes the join row
            contact.getCompanies().add(company);
            // in-memory only, so the response is accurate
            company.getContacts().add(contact);
        }

        return CompanyResponse.from(company);
    }

    @Transactional
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
