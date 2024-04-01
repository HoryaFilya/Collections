package ru.naumen.collection.task2.exception;

public class NoTicketFoundException extends RuntimeException{
    public NoTicketFoundException(String message) {
        super(message);
    }
}