package com.lrtech.toDoList.dto.auth;

import com.lrtech.toDoList.entity.enums.UserRole;

public record RegistrarDto(String nome,String email, String senha, UserRole role) {
  
}
