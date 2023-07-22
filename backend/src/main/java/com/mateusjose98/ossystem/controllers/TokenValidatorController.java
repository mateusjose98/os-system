package com.mateusjose98.ossystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenValidatorController {

  private final JwtDecoder jwtDecoder;

  @Autowired
  public TokenValidatorController(JwtDecoder jwtDecoder) {
    this.jwtDecoder = jwtDecoder;
  }

  @GetMapping("/validate-token")
  public ResponseEntity<?> validateToken(@RequestHeader String token) {
    try {
      Jwt jwt = jwtDecoder.decode(token);

      return new ResponseEntity<>("Token JWT válido!", HttpStatus.OK);
    } catch (Exception ex) {

      return new ResponseEntity<>("Token JWT inválido!", HttpStatus.BAD_REQUEST);
    }
  }
}