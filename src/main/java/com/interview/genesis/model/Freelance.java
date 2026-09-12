package com.interview.genesis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Freelance extends Contact {

    @Column(nullable = false, unique = true)
    private String vat;

    public Freelance() {}

    public Freelance(String firstName, String lastName, String address, String vat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.vat = vat;
    }
}
