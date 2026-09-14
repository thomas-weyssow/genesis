package com.interview.genesis.repository;

import com.interview.genesis.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Data access for companies.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Find all companies query using the EntityGraph annotation to avoid Hibernate's N+1 query problem.
     */
    @EntityGraph(attributePaths = "contacts")
    @Query("select c from Company c")
    List<Company> findAllWithContacts();

    Optional<Company> findByVat(String vat);
}
