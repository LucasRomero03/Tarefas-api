package com.lrtech.toDoList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrtech.toDoList.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{

    List<Todo> findByNomeIgnoreCase(String nome);
    List<Todo> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
}

