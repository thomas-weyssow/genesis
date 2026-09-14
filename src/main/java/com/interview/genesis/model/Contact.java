package com.interview.genesis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Check(constraints = "type <> 'FREELANCE' OR vat IS NOT NULL")
public abstract class Contact {

    @Id
    @GeneratedValue
    protected Long id;
    @Column(nullable = false)
    protected String firstName;
    @Column(nullable = false)
    protected String lastName;
    @Column(nullable = false)
    protected String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "contact_company",
        joinColumns = @JoinColumn(name = "contact_id"),
        inverseJoinColumns = @JoinColumn(name = "company_id"),
        foreignKey = @ForeignKey(name = "fk_contact_company_contact"),
        inverseForeignKey = @ForeignKey(name = "fk_contact_company_company")
    )
    @JsonManagedReference
    protected List<Company> companies = new ArrayList<>();

    protected Contact() {}

    protected Contact(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
