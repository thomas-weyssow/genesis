package com.interview.genesis.dto.contact;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * Creation payload for a freelance, for which VAT number is mandatory.
 */
public record CreateFreelanceRequest(

    @NotBlank
    @Schema(example = "John")
    String firstName,

    @NotBlank
    @Schema(example = "Doe")
    String lastName,

    @NotBlank
    @Schema(example = "123 Main street, 1000 Brussels")
    String address,

    @NotBlank
    @Schema(example = "BE0123456789")
    String vat,

    @Schema(example = "[1, 2]")
    List<Long> companyIds

) implements CreateContactRequest {}
