package com.lrtech.toDoList.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrtech.toDoList.dto.TodoDto;
import com.lrtech.toDoList.service.TodoService;

@RestController
@RequestMapping(value ="/todos")
public class TodoController {
  
  private TodoService todoService;

  public TodoController(TodoService todoService){
    this.todoService=todoService;
  }

  @GetMapping
  public Page<TodoDto> getAllTodos(Pageable pageable){
    return todoService.getAllTodos(pageable);
  }

  @PostMapping
  public TodoDto createTodo(@RequestBody TodoDto dto){
    System.out.println(dto);
    todoService.createTodo(dto);
    return dto;
  }
  
}
