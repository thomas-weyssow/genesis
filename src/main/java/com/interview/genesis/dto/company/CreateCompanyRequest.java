package com.interview.genesis.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Creation payload for a company.
 */
public record CreateCompanyRequest(

    @NotBlank
    @Schema(example = "123 Main street, 1000 Brussels")
    String address,

    @NotBlank
    @Schema(example = "BE0123456789")
    String vat

) {}
