package com.interview.genesis.dto.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.interview.genesis.model.Company;
import com.interview.genesis.model.Contact;
import com.interview.genesis.model.Freelance;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "Contact")
public record ContactResponse(
    Long id,
    String firstName,
    String lastName,
    String address,
    ContactType type,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String vat,
    List<Long> companyIds
) {
    public static ContactResponse from(Contact contact) {

        return new ContactResponse(
            contact.getId(),
            contact.getFirstName(),
            contact.getLastName(),
            contact.getAddress(),
            (contact instanceof Freelance) ? ContactType.FREELANCE: ContactType.EMPLOYEE,
            (contact instanceof Freelance freelance) ? freelance.getVat() : null,
            contact
                .getCompanies()
                .stream()
                .map(Company::getId)
                .toList()
        );
    }
}
