package com.example.springboot.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoForGetDelete {
    private Long id;
    private String categoryName;
    private List<ProductDtoWithIdOnly> products = new ArrayList<>();
    private Date createdAt;
    private Date updatedAt;
}

