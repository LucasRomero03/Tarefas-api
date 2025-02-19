package com.lrtech.toDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrtech.toDoList.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
 
  User findByEmail(String email);

}
