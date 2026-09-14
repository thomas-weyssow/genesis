package com.interview.genesis.service;

import com.interview.genesis.dto.contact.*;
import com.interview.genesis.exception.CompaniesNotFoundException;
import com.interview.genesis.exception.ContactNotFoundException;
import com.interview.genesis.model.Company;
import com.interview.genesis.model.Contact;
import com.interview.genesis.model.Employee;
import com.interview.genesis.model.Freelance;
import com.interview.genesis.repository.CompanyRepository;
import com.interview.genesis.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    public ContactService(ContactRepository contactRepository, CompanyRepository companyRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }

    public ContactResponse create(CreateContactRequest request) {

        Contact contact = switch (request) {

            case CreateEmployeeRequest eReq -> new Employee(
                eReq.firstName(),
                eReq.lastName(),
                eReq.address()
            );
            case CreateFreelanceRequest fReq -> new Freelance(
                fReq.firstName(),
                fReq.lastName(),
                fReq.address(),
                fReq.vat()
            );
        };
        if (request.companyIds() != null) {
            List<Company> companies = resolveCompanies(request.companyIds());
            contact.getCompanies().addAll(companies);
        }

        contactRepository.save(contact);
        return ContactResponse.from(contact);
    }

    public ContactResponse update(UpdateContactRequest request, Long id) {

        Contact contact = contactRepository
            .findById(id)
            .orElseThrow(() -> new ContactNotFoundException(id))
        ;

        if (request.firstName() != null)
            contact.setFirstName(request.firstName());

        if (request.lastName() != null)
            contact.setLastName(request.lastName());

        if (request.address() != null)
            contact.setAddress(request.address());

        if (contact instanceof Freelance freelance && request.vat() != null)
            freelance.setVat(request.vat());

        if (request.companyIds() != null) {
            List<Company> companies = resolveCompanies(request.companyIds());
            contact.getCompanies().clear();
            contact.getCompanies().addAll(companies);
        }

        return ContactResponse.from(contact);
    }

    private List<Company> resolveCompanies(List<Long> companyIds) {

        List<Company> companies = companyRepository
            .findAllById(companyIds)
        ;
        List<Long> missingIds = companyIds
            .stream()
            .filter(id -> companies.stream().noneMatch(company -> company.getId().equals(id)))
            .toList()
        ;

        if (!missingIds.isEmpty())
            throw new CompaniesNotFoundException(missingIds);

        return companies;
    }
}
