package com.lrtech.toDoList.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrtech.toDoList.dto.auth.LoginDto;
import com.lrtech.toDoList.dto.auth.LoginResponseDto;
import com.lrtech.toDoList.dto.auth.RegistrarDto;
import com.lrtech.toDoList.entity.User;
import com.lrtech.toDoList.repository.UserRepository;
import com.lrtech.toDoList.service.auth.AuthorizationService;
import com.lrtech.toDoList.service.auth.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private AuthenticationManager authenticationManager;
  private UserRepository repository;
  private AuthorizationService authorizationService;
  private TokenService tokenService;

  public AuthController(AuthenticationManager authenticationManager, UserRepository repository, AuthorizationService authorizationService,TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.repository = repository;
    this.authorizationService = authorizationService;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDto dto) {
    try {
      var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
      var auth = this.authenticationManager.authenticate(userNamePassword);

      var token = tokenService.generateToken((User)auth.getPrincipal());
      return ResponseEntity.ok(new LoginResponseDto(token));
    } catch (Exception e) {
      return ResponseEntity.status(403).body("usuario errou as credenciais ou nao existe");
    }

  }

  @PostMapping("/registrar")
  public ResponseEntity<?> registrar(@RequestBody RegistrarDto dto) {
    if (repository.findByEmail(dto.email()) != null)
      return ResponseEntity.badRequest().body("usuario ja existe");

    authorizationService.registrar(dto);
    return ResponseEntity.ok().body("usuairo criado com sucesso");
  }

}
