package com.interview.genesis.exception;

/**
 * Thrown when a contact id taken from the URL matches no contact. Mapped to 404.
 */
public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(Long id) {
        super("no contact exists with id " + id);
    }
}
