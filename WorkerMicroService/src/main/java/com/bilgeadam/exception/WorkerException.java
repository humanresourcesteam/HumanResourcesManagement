package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class WorkerException extends RuntimeException{

    private final EErrorType errorType;

    public WorkerException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public WorkerException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
