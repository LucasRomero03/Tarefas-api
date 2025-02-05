package com.lrtech.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrtech.toDoList.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{
  
}
