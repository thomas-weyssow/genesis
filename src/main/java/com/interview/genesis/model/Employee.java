package com.interview.genesis.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(Employee.TYPE)
public class Employee extends Contact {

    public static final String TYPE = "EMPLOYEE";

    public Employee() {}

    public Employee(String firstName, String lastName, String address) {
        super(firstName, lastName, address);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
