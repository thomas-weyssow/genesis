package com.interview.genesis.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A person working for zero or more companies. Mapped with single-table inheritance;
 * {@link Employee} and {@link Freelance} are the concrete types.
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(check = @CheckConstraint(
    name = "ck_freelance_has_vat",
    constraint = "type <> '" + Freelance.TYPE + "' or vat is not null"
))
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

    @ManyToMany
    protected List<Company> companies = new ArrayList<>();

    protected Contact() {}

    protected Contact(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    /**
     * The discriminator value of this contact, as exposed by the API.
     */
    public abstract String getType();
}
