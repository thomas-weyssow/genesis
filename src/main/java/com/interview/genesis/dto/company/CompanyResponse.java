package com.interview.genesis.dto.company;

import com.interview.genesis.model.Company;
import com.interview.genesis.model.Contact;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * A company as returned by the API.
 */
@Schema(name = "Company")
public record CompanyResponse(
    Long id,
    String address,
    String vat,
    List<Long> contactIds
) {
    public static CompanyResponse from(Company company) {

        return new CompanyResponse(
            company.getId(),
            company.getAddress(),
            company.getVat(),
            company.getContacts()
                .stream()
                .map(Contact::getId)
                .toList()
        );
    }
}
