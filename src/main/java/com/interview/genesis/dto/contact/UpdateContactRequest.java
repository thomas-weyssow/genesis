package com.interview.genesis.dto.contact;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Partial update payload for a contact: a null field is left unchanged. The type cannot be changed.
 */
public record UpdateContactRequest(

    @Schema(example = "John")
    String firstName,

    @Schema(example = "Doe")
    String lastName,

    @Schema(example = "123 Main street, 1000 Brussels")
    String address,

    @Schema(example = "BE0123456789")
    String vat,

    @Schema(example = "[1, 2]")
    List<Long> companyIds

) {}
