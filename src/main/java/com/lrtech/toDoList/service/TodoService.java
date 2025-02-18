package com.lrtech.toDoList.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lrtech.toDoList.dto.TodoDto;
import com.lrtech.toDoList.entity.Todo;
import com.lrtech.toDoList.repository.TodoRepository;
import com.lrtech.toDoList.service.exceptions.ResourceNotFound;
import com.lrtech.toDoList.service.exceptions.ResourceNotFoundException;

@Service
public class TodoService {
  private TodoRepository todorep;

  public TodoService(TodoRepository todorep) {
    this.todorep = todorep;
  }

  public TodoDto getById(Long id) {
    Todo todo = todorep.findById(id).orElseThrow(() -> new ResourceNotFoundException("recurso não encontrado"));
    return new TodoDto(todo);
  }

  public List<TodoDto> getByNome(String nome) {
    List<Todo> listTodos = todorep.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
    if (listTodos.isEmpty()) {
      throw new ResourceNotFound("recurso nao encontrado");
    }
    return listTodos.stream().map(x -> new TodoDto(x)).toList();
  }

  public Page<TodoDto> getAllTodos(Pageable pageable) {
    Page<Todo> todos = todorep.findAll(pageable);
    return todos.map(x -> new TodoDto(x));
  }

  public TodoDto createTodo(TodoDto dto) {
    Todo todo = new Todo();
    todo.setNome(dto.getNome());
    todo.setDescricao(dto.getDescricao());
    todo.setPrioridade(dto.getPrioridade());
    todo.setRealizado(dto.getRealizado());
    todorep.save(todo);
    return new TodoDto(todo);
  }

  public void deleteTodo(Long id) {
    if(!todorep.existsById(id)) throw new ResourceNotFoundException("recurso nao encontrado ");
    todorep.deleteById(id);

  }

  public TodoDto updateTodo(Long id, TodoDto dto) {
    if(!todorep.existsById(id)) throw new ResourceNotFoundException("recurso nao encontrado ");
    Todo todo = todorep.getReferenceById(id);
    todo.setNome(dto.getNome());
    todo.setDescricao(dto.getDescricao());
    todo.setPrioridade(dto.getPrioridade());
    todo.setRealizado(dto.getRealizado());
    todorep.save(todo);
    return new TodoDto(todo);

  }
}
