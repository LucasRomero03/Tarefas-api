package com.lrtech.toDoList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lrtech.toDoList.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{

    List<Todo> findByNomeIgnoreCase(String nome);
    List<Todo> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

    @Query(value ="SELECT * FROM todos WHERE user_id = :id", nativeQuery = true)
    List<Todo> findTodosByUserId(@Param("id") Long id);
}

