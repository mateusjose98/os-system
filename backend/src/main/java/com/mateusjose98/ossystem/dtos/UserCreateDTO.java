package com.mateusjose98.ossystem.dtos;

public record UserCreateDTO(
    String firstName,
    String lastName,
    String email,
    String password) {

}
