package com.lrtech.toDoList.service.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lrtech.toDoList.dto.auth.AutenticadoDto;
import com.lrtech.toDoList.dto.auth.RegistrarDto;
import com.lrtech.toDoList.entity.User;
import com.lrtech.toDoList.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

  private UserRepository repository;

  private PasswordEncoder encoder;

  public AuthorizationService(UserRepository repository, PasswordEncoder encoder) {
    this.repository = repository;
    this.encoder = encoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByEmail(username);
  }

  public void registrar(RegistrarDto dto) {
    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    User user = new User();
    user.setNome(dto.nome());
    user.setEmail(dto.email());
    user.setSenha(encoder.encode(dto.senha()));
    user.setRole(dto.role());

    repository.save(user);

  }

  protected User authenticated() {
    try {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (User) repository.findByEmail(username);
    } catch (Exception e) {
        throw new UsernameNotFoundException("User not found");
    }
}

  @Transactional(readOnly = true)
  public AutenticadoDto getMe() {

    User user = authenticated();

    return new AutenticadoDto(user.getNome(),user.getEmail(),user.getRole());
  }

}
