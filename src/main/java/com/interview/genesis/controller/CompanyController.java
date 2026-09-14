package com.interview.genesis.controller;

import com.interview.genesis.dto.company.CompanyResponse;
import com.interview.genesis.dto.company.CreateCompanyRequest;
import com.interview.genesis.dto.company.UpdateCompanyRequest;
import com.interview.genesis.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST endpoints for companies
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> findAll() {

        return companyService.findAll();
    }

    @GetMapping("/vat/{vat}")
    public CompanyResponse findByVat(@PathVariable String vat) {

        return companyService.findByVat(vat);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse create(@Valid @RequestBody CreateCompanyRequest request) {

        return companyService.create(request);
    }

    @PostMapping("/{companyId}/contact/{contactId}")
    public CompanyResponse addContact(@PathVariable Long companyId, @PathVariable Long contactId) {

        return companyService.addContact(companyId, contactId);
    }

    @PutMapping("/{id}")
    public CompanyResponse update(@Valid @RequestBody UpdateCompanyRequest request, @PathVariable Long id) {

        return companyService.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        companyService.delete(id);
    }


}
