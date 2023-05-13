package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class PermissionException extends RuntimeException{

    private final EErrorType errorType;

    public PermissionException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public PermissionException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
