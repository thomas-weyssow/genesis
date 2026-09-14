package com.interview.genesis.repository;


import com.interview.genesis.model.Contact;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access for contacts.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    /**
     * Find all contacts query using the EntityGraph annotation to avoid Hibernate's N+1 query problem.
     */
    @EntityGraph(attributePaths = "companies")
    @Query("select c from Contact c")
    List<Contact> findAllWithCompanies();
}
