package com.mateusjose98.ossystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mateusjose98.ossystem.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

  @EntityGraph(attributePaths = { "roles" })
  Optional<User> findByEmail(String email);

}
