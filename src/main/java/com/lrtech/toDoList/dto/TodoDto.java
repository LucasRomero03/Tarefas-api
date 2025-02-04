package com.lrtech.toDoList.dto;

import com.lrtech.toDoList.entity.Todo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TodoDto {

  private Long id;
  private String nome;
  private String descricao;
  private Boolean realizado;
  private int prioridade;

  public TodoDto(Todo todo){
    id=todo.getId();
    nome = todo.getNome();
    descricao = todo.getDescricao();
    realizado = todo.getRealizado();
    prioridade = todo.getPrioridade();

  }

}
