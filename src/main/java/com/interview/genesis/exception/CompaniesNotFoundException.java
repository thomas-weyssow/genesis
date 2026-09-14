package com.interview.genesis.exception;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CompaniesNotFoundException extends RuntimeException {

    private static String buildMessage(Collection<Long> ids) {
        String joined = ids
            .stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "))
        ;
        return "no company exists with id(s) " + joined;
    }

    public CompaniesNotFoundException(List<Long> ids) {
        super(buildMessage(ids));
    }
}
