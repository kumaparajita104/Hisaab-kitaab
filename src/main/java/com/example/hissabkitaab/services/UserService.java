package com.example.hissabkitaab.services;

import com.example.hissabkitaab.entity.Role;
import com.example.hissabkitaab.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserByUserName(String username);
    UserDto getUserById(Long userId);
    List<Role> getRoleById(Long userId);
    List<UserDto> getALlUsers();
    UserDto updateUser(UserDto userDto,Long userId);
    void deleteUser(Long userId);
}
