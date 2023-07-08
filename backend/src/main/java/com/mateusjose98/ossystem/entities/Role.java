package com.mateusjose98.ossystem.entities;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_role")
@Getter
@Setter
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String authority;
}