package com.example.springboot.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoWithCart {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String location;
    private Date createdAt;
    private Date updatedAt;
    private CartDTO cart;
}
