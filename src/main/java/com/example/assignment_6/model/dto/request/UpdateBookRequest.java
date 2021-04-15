package com.example.assignment_6.model.dto.request;

import lombok.Data;

@Data
public class UpdateBookRequest {
    private Long id;
    private String name;
    private String description;
}
