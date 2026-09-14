package com.interview.genesis.exception;

/**
 * Thrown when a company id or VAT number taken from the URL matches no company. Mapped to 404.
 */
public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long id) {
        super("no company exists with id " + id);
    }

    public CompanyNotFoundException(String vat) {
        super("no company exists with VAT number " + vat);
    }
}
