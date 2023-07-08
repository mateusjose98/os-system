package com.mateusjose98.ossystem.services;

import com.mateusjose98.ossystem.dtos.UserCreateDTO;

public interface IUserService {
  Long save(UserCreateDTO userCreateDTO);
}
