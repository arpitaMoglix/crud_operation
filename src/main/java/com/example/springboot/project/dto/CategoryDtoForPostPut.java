package com.example.springboot.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoForPostPut {
    private Long id;
    private String categoryName;
    private Date createdAt;
    private Date updatedAt;
}