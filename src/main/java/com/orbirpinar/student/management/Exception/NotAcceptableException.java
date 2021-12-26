package com.orbirpinar.student.management.Exception;

public class NotAcceptableException extends RuntimeException {
    public NotAcceptableException(String message) {
        super(NotAcceptableException.generateMessage(message));
    }

    private static String generateMessage(String message) {
        return message;
    }
}
