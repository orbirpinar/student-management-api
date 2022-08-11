package com.orbirpinar.student.management.Exception;



public class StudentManagementBusinessException extends RuntimeException{
    private final Error error;
    public StudentManagementBusinessException(String message, Error error) {
        super(message);
        this.error = error;
    }

    public StudentManagementBusinessException(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
