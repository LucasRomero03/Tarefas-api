package com.lrtech.toDoList.service.exceptions;

public class UnauthorizedException extends RuntimeException{
  
  public UnauthorizedException(String m){
    super(m);
  }
}
