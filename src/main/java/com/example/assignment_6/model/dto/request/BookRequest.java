package com.example.assignment_6.model.dto.request;

import lombok.Data;

@Data
public class BookRequest {
    private String name;
    private String description;
    private Long libraryId;
}
