package com.lrtech.toDoList.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {
  

  public ResourceNotFoundException(String message) {
    super(message);
  }


}
