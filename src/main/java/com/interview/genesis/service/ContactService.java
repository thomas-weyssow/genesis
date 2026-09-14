package com.interview.genesis.service;

import com.interview.genesis.dto.CreateContactRequest;
import com.interview.genesis.dto.CreateEmployeeRequest;
import com.interview.genesis.dto.CreateFreelanceRequest;
import com.interview.genesis.model.Company;
import com.interview.genesis.model.Contact;
import com.interview.genesis.model.Employee;
import com.interview.genesis.model.Freelance;
import com.interview.genesis.repository.CompanyRepository;
import com.interview.genesis.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    public ContactService(ContactRepository contactRepository, CompanyRepository companyRepository) {
        this.contactRepository = contactRepository;
        this.companyRepository = companyRepository;
    }

    public void create(CreateContactRequest request) throws Exception {

        List<Company> companies = companyRepository.findAllById(request.companyIds());

        List<Long> missingIds = request
            .companyIds()
            .stream()
            .filter(id -> companies.stream().noneMatch(c -> c.getId().equals(id)))
            .toList()
        ;
        if (!missingIds.isEmpty())
            throw new Exception("no company exists with id " + missingIds.stream().map(String::valueOf).collect(Collectors.joining(", ")));

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
        contact.getCompanies().addAll(companies);

        contactRepository.save(contact);
    }
}
