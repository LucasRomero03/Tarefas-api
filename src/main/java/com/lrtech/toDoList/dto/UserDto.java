package com.lrtech.toDoList.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lrtech.toDoList.entity.User;

import jakarta.validation.constraints.NotBlank;
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
  @NotBlank(message = "campo obrigatorio")
  private String email;
  private String senha;

  private List<TodoDto> todos = new ArrayList<>();
  

  public UserDto(User user) {
    id = user.getId();
    nome = user.getNome();
    email = user.getEmail();
    senha = user.getSenha();
    todos = user.getTodos().stream().map(x -> new TodoDto(x)).collect(Collectors.toList());
  }

  public UserDto(String nome, String email, String senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }

}
