package com.example.assignment_6.model.dto.request;

import lombok.Data;

@Data
public class GetBookRequest {
    private String fakeToken;
    private Long id;
}
