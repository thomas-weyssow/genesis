package com.interview.genesis.controller;

import com.interview.genesis.dto.company.CompanyResponse;
import com.interview.genesis.dto.company.CreateCompanyRequest;
import com.interview.genesis.dto.company.UpdateCompanyRequest;
import com.interview.genesis.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        companyService.delete(id);
    }


}
