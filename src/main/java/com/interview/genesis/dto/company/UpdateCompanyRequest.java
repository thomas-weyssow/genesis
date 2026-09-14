package com.interview.genesis.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateCompanyRequest(

    @Schema(example = "123 Main street, 1000 Brussels")
    String address,

    @Schema(example = "BE0123456789")
    String vat

) {}
