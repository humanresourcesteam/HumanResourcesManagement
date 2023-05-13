package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class AdvanceException extends RuntimeException{

    private final EErrorType errorType;

    public AdvanceException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AdvanceException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
