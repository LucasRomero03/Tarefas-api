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
import com.lrtech.toDoList.dto.auth.AutenticadoDto;
import com.lrtech.toDoList.service.UserSevice;
import com.lrtech.toDoList.service.auth.AuthorizationService;

import jakarta.validation.Valid;




@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

  private UserSevice userSerSevice;
  private AuthorizationService authorizationService;

  public UserController(UserSevice userSerSevice, AuthorizationService authorizationService){
    this.userSerSevice=userSerSevice;
    this.authorizationService=authorizationService;
  }


  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getById(@PathVariable Long id) {
      UserDto userDto = userSerSevice.getById(id);
      return ResponseEntity.ok(userDto);
  }

  @GetMapping(value = "/me")
  public ResponseEntity<AutenticadoDto> getMe() {

    AutenticadoDto dto = authorizationService.getMe();

    return ResponseEntity.ok(dto);

  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers(){
    return ResponseEntity.ok(userSerSevice.getAllUsers());
  }
  
  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
      UserDto dto1 = userSerSevice.createUser(userDto);
      URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(dto1.getId())
        .toUri();
        
      
      return ResponseEntity.created(uri).body(dto1);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserByid(@PathVariable Long id){
    userSerSevice.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
  @PutMapping(value = "/{id}")
  public ResponseEntity<UserDto> atualizarUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
      userSerSevice.atualizarUser(id,userDto);
      return ResponseEntity.ok(userDto);
  }
}
