package com.interview.genesis.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCompanyRequest(

    @Schema(
        description = "Address",
        example = "123 Main street, 1000 Brussels",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String address,
    @Schema(
        description = "VAT number",
        example = "BE0123456789",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    String vat

) {}
