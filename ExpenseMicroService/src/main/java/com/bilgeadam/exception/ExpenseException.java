package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class ExpenseException extends RuntimeException{

    private final EErrorType errorType;
    public ExpenseException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public ExpenseException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
