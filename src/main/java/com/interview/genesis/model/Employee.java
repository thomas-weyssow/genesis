package com.interview.genesis.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * An "employee" contact.
 */
@Entity
@DiscriminatorValue(Employee.TYPE)
public class Employee extends Contact {

    /**
     * Discriminator value, shared by the JPA mapping, the JSON discriminator and the API response.
     */
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
