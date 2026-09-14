package com.interview.genesis.dto.contact;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.interview.genesis.model.Employee;
import com.interview.genesis.model.Freelance;

import java.util.List;

/**
 * Creation payload for a contact. Polymorphic: the {@code type} property selects the
 * concrete request, which is what makes the VAT number mandatory for a freelance.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreateEmployeeRequest.class, name = Employee.TYPE),
    @JsonSubTypes.Type(value = CreateFreelanceRequest.class, name = Freelance.TYPE)
})
public sealed interface CreateContactRequest permits CreateEmployeeRequest, CreateFreelanceRequest {

    String firstName();
    String lastName();
    String address();
    List<Long> companyIds();
}
