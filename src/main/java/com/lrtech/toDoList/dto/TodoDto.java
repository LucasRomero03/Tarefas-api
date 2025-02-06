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
  @NotBlank
  private String nome;
  @NotBlank
  private String descricao;
  private Boolean realizado;
  private int prioridade;

  public TodoDto(Todo todo) {
    id = todo.getId();
    nome = todo.getNome();
    descricao = todo.getDescricao();
    realizado = todo.getRealizado();
    prioridade = todo.getPrioridade();

  }

  public TodoDto(String nome, String descricao, Boolean realizado, int prioridade) {
    this.nome = nome;
    this.descricao = descricao;
    this.realizado = realizado;
    this.prioridade = prioridade;
  }

}
