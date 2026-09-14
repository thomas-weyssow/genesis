package com.interview.genesis.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreateEmployeeRequest.class, name="EMPLOYEE"),
    @JsonSubTypes.Type(value = CreateFreelanceRequest.class, name="FREELANCE")
})
public sealed interface CreateContactRequest permits CreateEmployeeRequest, CreateFreelanceRequest {

    String firstName();
    String lastName();
    String address();
    List<Long> companyIds();
}
