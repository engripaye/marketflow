package dev.engripaye.backend.common;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ApiExceptionalHandler {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    ProblemDetail notFound(ChangeSetPersister.NotFoundException e) {
        return problem(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    ProblemDetail conflict(ConflictException e){
        return problem(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail validation(MethodArgumentNotValidException e) {
        ProblemDetail p = problem(HttpStatus.BAD_REQUEST, "Request validation failed");
        p.setProperties("fields", e.getBindingResult().getFieldErrors().stream().collect(
                java.util.stream.Collectors.toMap()
        ));
    }

}
