package com.interview.genesis.model;

import com.interview.genesis.dto.ContactType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("FREELANCE")
public class Freelance extends Contact {

    @Column(unique = true)
    private String vat;

    public Freelance() {}

    public Freelance(String firstName, String lastName, String address, String vat) {
        super(firstName, lastName, address);
        this.vat = vat;
    }
}
