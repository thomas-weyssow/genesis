package com.interview.genesis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Contact {

    @Id
    @GeneratedValue
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String address;

    @ManyToMany
    @JsonManagedReference
    protected List<Company> companies = new ArrayList<>();
}
