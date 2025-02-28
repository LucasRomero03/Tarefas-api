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
public class UserDtoResponse {
  private Long id;
  private String nome;

  public UserDtoResponse(User user) {
    id = user.getId();
    nome = user.getNome();

  }

  public UserDtoResponse(String nome) {
    this.nome = nome;
  }

  

}
