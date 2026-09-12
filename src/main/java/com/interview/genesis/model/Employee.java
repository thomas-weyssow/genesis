package com.interview.genesis.model;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Contact {

    public Employee() {}

    public Employee(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
