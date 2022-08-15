package com.orbirpinar.student.management.Exception;

public enum Error {
    CLASS_NOT_FOUND("class_not_found","There is no class with given input"),
    INVALID_CLASS_FORMAT("invalid_classRoom_format","Given classRoom format is not valid"),
    USER_NOT_FOUND("user_not_found","There is no user given userId");
    private final String errorCode;
    private final String description;

    Error(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
