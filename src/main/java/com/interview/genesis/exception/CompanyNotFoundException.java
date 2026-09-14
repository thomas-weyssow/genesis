package com.interview.genesis.exception;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long id) {
        super("no company exists with id " + id);
    }
}
