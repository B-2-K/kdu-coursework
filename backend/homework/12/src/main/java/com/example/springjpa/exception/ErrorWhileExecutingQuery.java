package com.example.springjpa.exception;

public class ErrorWhileExecutingQuery extends RuntimeException{
    public ErrorWhileExecutingQuery(String msg){
        super(msg);
    }
}
