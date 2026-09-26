package dev.engripaye.backend.common;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Optional;

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
                java.util.stream.Collectors.toMap(x -> x.getField(), x-> Optional.ofNullable(x.getDefaultMessage()).orElse("Invalid"), (a, b) -> a, LinkedHashMap::new)));
        return p;
    }

    private ProblemDetail problem(HttpStatus status, String message) {
        ProblemDetail p = ProblemDetail.forStatusAndDetail(status, message);
        p.setProperty("timestamp", Instant.now());
        return p;
    }

}
