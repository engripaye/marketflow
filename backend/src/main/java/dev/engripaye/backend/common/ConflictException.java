package dev.engripaye.backend.common;

public class ConflictException extends RuntimeException{
    public ConflictException(String message){
        super(message);
    }
}
