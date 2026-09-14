package com.interview.genesis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
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
