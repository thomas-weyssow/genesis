package com.interview.genesis.exception;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(Long id) {
        super("no contact exists with id " + id);
    }
}
