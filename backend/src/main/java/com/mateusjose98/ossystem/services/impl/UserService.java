package com.mateusjose98.ossystem.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mateusjose98.ossystem.dtos.UserCreateDTO;
import com.mateusjose98.ossystem.entities.User;
import com.mateusjose98.ossystem.repositories.UserRepository;
import com.mateusjose98.ossystem.services.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, IUserService {

  final PasswordEncoder encoder;
  final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
  }

  @Override
  public Long save(UserCreateDTO userCreateDTO) {
    var user = userRepository.save(new User(userCreateDTO, encode(userCreateDTO.password())));
    return user.getId();
  }

  private String encode(String password) {
    return encoder.encode(password);
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
  }

  public User findByUsername(String username) {
    return userRepository.findByEmail(username).orElseThrow(() -> new IllegalArgumentException(""));
  }

}
