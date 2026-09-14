package com.interview.genesis.repository;

import com.interview.genesis.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @EntityGraph(attributePaths = "contacts")
    @Query("select c from Company c")
    List<Company> findAllWithContacts();

    Optional<Company> findByVat(String vat);
}
