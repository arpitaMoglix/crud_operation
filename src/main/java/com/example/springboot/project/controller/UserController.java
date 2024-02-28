package com.example.springboot.project.controller;


import com.example.springboot.project.Service.CartServiceInterface;
import com.example.springboot.project.Service.UserInterface;
import com.example.springboot.project.dto.CartDtoRequest;
import com.example.springboot.project.dto.CartDtoResponse;
import com.example.springboot.project.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserInterface userService;



    @Autowired
    private CartServiceInterface cartService;

    @PostMapping("/{userId}/addProductToCart")
    public ResponseEntity<CartDtoResponse> addProductToCart(@PathVariable Long userId, @RequestBody CartDtoRequest cartDtoRequest) {
        // Check if user exists
        UserDTO userDto = userService.getUserById(userId);
        if (userDto != null) {
            // Create cart if it doesn't exist for the user
            CartDtoResponse cartDtoResponse = cartService.addProductToCartV1(userId, cartDtoRequest);
            return ResponseEntity.ok(cartDtoResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Endpoint to get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDto = userService.getUserById(userId);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userDTO.setId(userId);
        UserDTO updatedUser = userService.updateUser(userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }


}
