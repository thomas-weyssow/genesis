package com.interview.genesis.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 *  A "freelance" contact, with an additional VAT number attribute.
 */
@Getter
@Setter
@Entity
@DiscriminatorValue(Freelance.TYPE)
public class Freelance extends Contact {

    /**
     * Discriminator value, shared by the JPA mapping, the JSON discriminator and the API response.
     */
    public static final String TYPE = "FREELANCE";

    @Column(unique = true)
    private String vat;

    public Freelance() {}

    public Freelance(String firstName, String lastName, String address, String vat) {
        super(firstName, lastName, address);
        this.vat = vat;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
