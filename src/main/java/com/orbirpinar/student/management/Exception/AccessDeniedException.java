package com.orbirpinar.student.management.Exception;


public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super(AccessDeniedException.generateMessage("You cannot access this page"));
    }

    public static String generateMessage(String message) {
        return message;
    }
}
