package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class CompanyException extends RuntimeException{

    private final EErrorType errorType;

    public CompanyException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CompanyException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
