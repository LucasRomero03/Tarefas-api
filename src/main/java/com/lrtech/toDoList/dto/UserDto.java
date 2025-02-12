package com.lrtech.toDoList.dto;

import com.lrtech.toDoList.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserDto {
  private Long id;
  private String nome;
  private String email;
  private String senha;

  public UserDto(User user){
    id = user.getId();
    nome = user.getNome();
    email = user.getEmail();
    senha = user.getSenha();
  }

  public UserDto(String nome, String email, String senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }
  
}
