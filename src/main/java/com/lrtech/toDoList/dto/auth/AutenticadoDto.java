package com.lrtech.toDoList.dto.auth;

import com.lrtech.toDoList.entity.enums.UserRole;

public record AutenticadoDto(String nome, String email, UserRole role) {
  
}
