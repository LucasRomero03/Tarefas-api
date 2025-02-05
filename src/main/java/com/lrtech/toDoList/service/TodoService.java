package com.lrtech.toDoList.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lrtech.toDoList.dto.TodoDto;
import com.lrtech.toDoList.entity.Todo;
import com.lrtech.toDoList.repository.TodoRepository;

@Service
public class TodoService {
  private TodoRepository todorep;

  public TodoService(TodoRepository todorep){
    this.todorep= todorep;
  }

  public Page<TodoDto>getAllTodos(Pageable pageable){
    Page<Todo> todos = todorep.findAll(pageable);
    return todos.map(x -> new TodoDto(x));
  }
  public TodoDto createTodo(TodoDto dto){ 
    Todo todo = new Todo();
    todo.setNome(dto.getNome());
    todo.setDescricao(dto.getDescricao());
    todo.setPrioridade(dto.getPrioridade());
    todo.setRealizado(dto.getRealizado());
    todorep.save(todo);
    return new TodoDto(todo);
  }

  public List<TodoDto>deleteTodo(){ return null;}
  public List<TodoDto>updateTodo(){ return null;}
}
