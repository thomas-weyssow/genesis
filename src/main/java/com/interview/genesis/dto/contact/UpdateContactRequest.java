package com.interview.genesis.dto.contact;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record UpdateContactRequest(

    @Schema(
        description = "First name",
        example = "John",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    String firstName,
    @Schema(
        description = "Last name",
        example = "Doe",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    String lastName,
    @Schema(
        description = "Address",
        example = "123 Main street, 1000 Brussels",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    String address,
    @Schema(
        description = "VAT number",
        example = "BE0123456789",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    String vat,
    @Schema(
        description = "IDs of the companies the contact works for",
        example = "[1, 2]",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    List<Long> companyIds

) {}
