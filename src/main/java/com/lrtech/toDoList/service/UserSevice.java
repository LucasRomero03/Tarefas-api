package com.lrtech.toDoList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lrtech.toDoList.dto.UserDto;
import com.lrtech.toDoList.entity.User;
import com.lrtech.toDoList.repository.UserRepository;
import com.lrtech.toDoList.service.exceptions.ResourceNotFound;

@Service
public class UserSevice {
  private UserRepository userRep;

  public UserSevice(UserRepository userRep){
    this.userRep=userRep;
  }

  public UserDto getById(Long id){
    User user = userRep.findById(id).orElseThrow( () -> new ResourceNotFound("recurso nao encontrado"));

    return new UserDto(user);
  }

  public List<UserDto> getAllUsers(){
    List<User> users = userRep.findAll();
    return users.stream().map(x -> new UserDto(x)).toList();
    
  }

  public UserDto createUser(UserDto userDto){
    User user = new User();
    user.setNome(userDto.getNome());
    user.setEmail(userDto.getEmail());
    user.setSenha(userDto.getSenha());
    userRep.save(user);
    return new UserDto(user);
  }
  public void deleteUser(Long id){
   if(!userRep.existsById(id)){
    throw new ResourceNotFound("recurso nao encontrado");
   }
    try {
     
      userRep.deleteById(id);
    } catch (Exception e) {
      throw new ResourceNotFound("recurso nao encontrado 1232132");
    }
  }
  public UserDto atualizarUser(Long id, UserDto userDto){
    if (!userRep.existsById(id)) {
      throw new ResourceNotFound("recurso nao encontrado");
    }
    try {
      User user = userRep.getReferenceById(id);
      user.setNome(userDto.getNome());
      user.setEmail(userDto.getEmail());
      user.setSenha(userDto.getSenha());
      userRep.save(user);
      return userDto;
    } catch (Exception e) {
      throw new ResourceNotFound("email ja existe");
    }
    
   
  }
}
