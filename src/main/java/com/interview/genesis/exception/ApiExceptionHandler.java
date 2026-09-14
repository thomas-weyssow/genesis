package com.interview.genesis.exception;

import com.interview.genesis.model.Employee;
import com.interview.genesis.model.Freelance;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Translates application exceptions into RFC 9457 problem responses.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ProblemDetail onUnreadableBody(HttpMessageNotReadableException exception) {

        return ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            "the request body could not be parsed; " +
                "\"type\" must be one of [" + Employee.TYPE + ", " + Freelance.TYPE + "]"
        );
    }

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
            HttpStatus.CONFLICT,
            "the request violates a data constraint, most likely a duplicate VAT number"
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail onValidationFailure(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new LinkedHashMap<>();

        exception
            .getBindingResult()
            .getFieldErrors()
            .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()))
        ;

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            "the request body is invalid"
        );

        problem.setProperty("errors", errors);

        return problem;
    }
}
