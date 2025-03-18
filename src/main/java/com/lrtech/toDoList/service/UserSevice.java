package com.lrtech.toDoList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lrtech.toDoList.dto.UserDto;
import com.lrtech.toDoList.entity.User;
import com.lrtech.toDoList.repository.UserRepository;
import com.lrtech.toDoList.service.exceptions.DataBaseException;
import com.lrtech.toDoList.service.exceptions.ResourceNotFoundException;

@Service
public class UserSevice {
  private UserRepository userRep;

  public UserSevice(UserRepository userRep) {
    this.userRep = userRep;
  }

  public UserDto getById(Long id) {
    User user = userRep.getById(id);

    
    return new UserDto(user);
  }

  // public UserDtoResponse getById1(Long id) {
  //   User user = userRep.findById(id).orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado"));
    
  //   return new UserDtoResponse(user);
  // }

  public List<UserDto> getAllUsers() {
    List<User> users = userRep.findAll();
    return users.stream().map(x -> new UserDto(x)).toList();

  }

  public UserDto createUser(UserDto userDto) {
    try {
      User user = new User();
      user.setNome(userDto.getNome());
      user.setEmail(userDto.getEmail());
      user.setSenha(userDto.getSenha());
      userRep.save(user);
      return new UserDto(user);  
    } catch (Exception e) {
      throw new DataBaseException("email já existe ");
    }
    
  }

  public void deleteUser(Long id) {
    if(!userRep.existsById(id)){
    throw new ResourceNotFoundException("recurso nao encontrado");
    }

    userRep.deleteById(id);

  }

  public UserDto atualizarUser(Long id, UserDto userDto) {
    
      if(!userRep.existsById(id)) throw new ResourceNotFoundException("recurso nao encontrado ");
      User user = userRep.getReferenceById(id);
      user.setNome(userDto.getNome());
      user.setEmail(userDto.getEmail());
      user.setSenha(userDto.getSenha());
      userRep.save(user);
      return userDto;
    

  }
}
