package com.fullstack.feedbacks.infra.exceptions;

public class ExceptionNotFound extends RuntimeException{
    public ExceptionNotFound(String mensagem){
        super(mensagem);
    }
}
