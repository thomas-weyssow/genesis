package com.interview.genesis.controller;

import com.interview.genesis.dto.company.CompanyResponse;
import com.interview.genesis.dto.company.CreateCompanyRequest;
import com.interview.genesis.dto.company.UpdateCompanyRequest;
import com.interview.genesis.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@RequestBody CreateCompanyRequest request) {

        CompanyResponse response = companyService.create(request);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.id())
            .toUri()
        ;

        return ResponseEntity
            .created(location)
            .body(response)
        ;
    }

    @PutMapping("/{id}")
    public CompanyResponse update(@RequestBody UpdateCompanyRequest request, @PathVariable Long id) {

        return companyService.update(request, id);
    }
}
