package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.UserDto;

public interface UserService {

    UserDto getUserById(Integer id);
    UserDto getUserByEmail(String email);
    UserDto updateUser(UserDto userDto, Integer id);
}
