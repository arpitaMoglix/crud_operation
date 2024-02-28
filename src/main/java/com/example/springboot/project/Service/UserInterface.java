package com.example.springboot.project.Service;

import com.example.springboot.project.dto.UserDTO;

import java.util.List;

public interface UserInterface {
    UserDTO createUser(UserDTO userDto);

    UserDTO getUserById(Long userId);

    UserDTO updateUser( UserDTO userDto);

    void deleteUser(Long userId);

    List<UserDTO> getAllUsers();

}
