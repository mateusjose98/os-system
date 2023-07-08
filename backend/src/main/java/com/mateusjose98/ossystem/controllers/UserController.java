package com.mateusjose98.ossystem.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusjose98.ossystem.dtos.UserCreateDTO;
import com.mateusjose98.ossystem.entities.User;
import com.mateusjose98.ossystem.services.impl.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  final UserService userService;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Long> create(UserCreateDTO dto) {
    return new ResponseEntity<Long>(userService.save(dto), HttpStatusCode.valueOf(201));
  }

  @GetMapping("{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<User> findOne(@PathVariable Long id) {
    log.info("Buscando id {}", id);
    return new ResponseEntity<User>(userService.findById(id), HttpStatusCode.valueOf(200));
  }

}
