package com.loja65.alarmintelbras.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IntelbrasException extends Exception{

    private Exception exception;
    private String message;

    public IntelbrasException(Exception e){
        this.exception = e;
    }

    public IntelbrasException(Exception e, String message){
        this.exception = e;
        this.message = message;
    }
}
