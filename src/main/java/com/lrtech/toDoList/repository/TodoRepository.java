package com.lrtech.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrtech.toDoList.entity.Todo;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long>{

    Optional<Todo> findByNomeIgnoreCase(String nome);
}
