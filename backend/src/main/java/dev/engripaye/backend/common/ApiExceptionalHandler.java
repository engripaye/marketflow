package dev.engripaye.backend.common;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ApiExceptionalHandler {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    ProblemDetail notFound(ChangeSetPersister.NotFoundException e) {
        return problem(HttpStatus.CONFLICT, e.getMessage());
    }


}
