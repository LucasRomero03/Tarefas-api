package com.lrtech.toDoList.dto;

import com.lrtech.toDoList.entity.Todo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class TodoDto {

  private Long id;
  @NotBlank(message="dados obrigatorios")
  private String nome;
  @NotBlank(message="dados obrigatorios")
  private String descricao;
  private Boolean realizado;
  private int prioridade;
  private UserDtoResponse userDto;

  public TodoDto(Todo todo) {
    id = todo.getId();
    nome = todo.getNome();
    descricao = todo.getDescricao();
    realizado = todo.getRealizado();
    prioridade = todo.getPrioridade();
    userDto = new UserDtoResponse(todo.getUser());

  }

  public TodoDto(String nome, String descricao, Boolean realizado, int prioridade, UserDtoResponse userDto) {
    this.nome = nome;
    this.descricao = descricao;
    this.realizado = realizado;
    this.prioridade = prioridade;
    this.userDto = userDto;
  }

}
