package com.lrtech.toDoList.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lrtech.toDoList.dto.TodoDto;
import com.lrtech.toDoList.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/todos")
public class TodoController {
  // injeção de dependecia com construtor
  private TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }
  // //getById
  // @GetMapping(value ="/{id}")
  // public TodoDto getById(@PathVariable Long id){
  // return todoService.getById(id);
  // }
  // //getAll
  // @GetMapping
  // public Page<TodoDto> getAllTodos(Pageable pageable){
  // return todoService.getAllTodos(pageable);
  // }
  // //POST
  // @PostMapping
  // public TodoDto createTodo(@RequestBody TodoDto dto){
  // System.out.println(dto);
  // todoService.createTodo(dto);
  // return dto;
  // }
  // //DELETE
  // @DeleteMapping(value ="/{id}")
  // public void deleteTodo(@PathVariable Long id){
  // todoService.deleteTodo(id);
  // }
  // //PUT
  // @PutMapping(value="/{id}")
  // public TodoDto updateTodo(@PathVariable Long id,@RequestBody TodoDto dto){
  // return todoService.updateTodo(id, dto);
  // }

  //// USANDO OS REPONSE ENTITY
  // getById
  @GetMapping(value = "/{id}")
  public ResponseEntity<TodoDto> getById(@PathVariable Long id) {
    TodoDto dto = todoService.getById(id);
    return ResponseEntity.ok(dto);
  }
  // GETBYNAME
  @GetMapping(value = "/nome")
  public ResponseEntity<List<TodoDto>> getByNome(@RequestParam("nome") String nome) {
    List<TodoDto> listTododto = todoService.getByNome(nome);
    return ResponseEntity.ok(listTododto);
  }
  @GetMapping(value = "/id/{id}")
  public ResponseEntity<List<TodoDto>> getByNome(@PathVariable Long id) {
    List<TodoDto> listTododto = todoService.getTodoByUserId(id);
    return ResponseEntity.ok(listTododto);
  }
  // getAll
  @GetMapping
  public ResponseEntity<Page<TodoDto>> getAllTodos(Pageable pageable) {
    Page<TodoDto> todos = todoService.getAllTodos(pageable);
    return ResponseEntity.ok(todos);
  }

  // POST
  @PostMapping
  public ResponseEntity<TodoDto> createTodo(@RequestBody @Valid TodoDto dto) {
    TodoDto copy = todoService.createTodo(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(copy.getId())
        .toUri();
    return ResponseEntity.created(uri).body(copy);
  }

  // DELETE
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
    todoService.deleteTodo(id);
    return ResponseEntity.noContent().build();
  }

  // PUT
  @PutMapping(value = "/{id}")
  public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody @Valid TodoDto dto) {
    TodoDto copy = todoService.updateTodo(id, dto);
    return ResponseEntity.ok(copy);
  }
}
