package com.lrtech.toDoList.service.exceptions;

public class ResourceNotFound extends RuntimeException {
  
  public ResourceNotFound(String message){
    super(message);
  }
}
