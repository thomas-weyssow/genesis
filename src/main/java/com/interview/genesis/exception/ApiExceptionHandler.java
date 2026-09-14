package com.interview.genesis.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({
        ContactNotFoundException.class,
        CompanyNotFoundException.class
    })
    ProblemDetail onNotFound(RuntimeException exception) {

        return ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            exception.getMessage()
        );
    }

    @ExceptionHandler(CompaniesNotFoundException.class)
    ProblemDetail onUnknownCompanies(CompaniesNotFoundException exception) {

        return ProblemDetail.forStatusAndDetail(
            HttpStatus.UNPROCESSABLE_CONTENT,
            exception.getMessage()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ProblemDetail onDataIntegrityViolation(DataIntegrityViolationException exception) {

        return ProblemDetail.forStatusAndDetail(
            HttpStatus.UNPROCESSABLE_CONTENT,
            exception.getMessage()
        );
    }
}
