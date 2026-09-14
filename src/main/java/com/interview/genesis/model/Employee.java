package com.interview.genesis.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Contact {

    public Employee() {}

    public Employee(String firstName, String lastName, String address) {
        super(firstName, lastName, address);
    }
}
