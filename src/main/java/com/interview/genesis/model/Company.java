package com.interview.genesis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private String vat;

    @ManyToMany(mappedBy = "companies")
    @JsonBackReference
    private List<Contact> contacts = new ArrayList<>();

    public Company() {}

    public Company(String address, String vat) {
        this.address = address;
        this.vat = vat;
    }
}
