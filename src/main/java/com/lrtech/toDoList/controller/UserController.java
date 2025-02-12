package com.lrtech.toDoList.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lrtech.toDoList.dto.UserDto;
import com.lrtech.toDoList.service.UserSevice;




@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

  private UserSevice userSerSevice;

  public UserController(UserSevice userSerSevice){
    this.userSerSevice=userSerSevice;
  }


  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable Long id) {
      UserDto userDto = userSerSevice.getById(id);
      return ResponseEntity.ok(userDto);
  }
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers(){
    return ResponseEntity.ok(userSerSevice.getAllUsers());
  }
  
  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
      UserDto dto1 = userSerSevice.createUser(userDto);
      URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(dto1.getId())
        .toUri();
        
      
      return ResponseEntity.created(uri).body(dto1);
  }
  @DeleteMapping
  public ResponseEntity<Void> deleteUserByid(@PathVariable Long id){
    userSerSevice.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
  @PutMapping(value = "/{id}")
  public ResponseEntity<UserDto> atualizarUser(@PathVariable Long id, @RequestBody UserDto userDto) {
      userSerSevice.atualizarUser(id,userDto);
      return ResponseEntity.ok(userDto);
  }
}
