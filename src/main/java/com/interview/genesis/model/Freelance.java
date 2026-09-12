package com.interview.genesis.model;

import jakarta.persistence.Entity;

@Entity
public class Freelance extends Contact {

    private String vat;

    public Freelance() {}

    public Freelance(String firstName, String lastName, String address, String vat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.vat = vat;
    }
}
