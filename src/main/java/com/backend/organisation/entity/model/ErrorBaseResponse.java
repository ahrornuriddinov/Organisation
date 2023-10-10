package com.backend.organisation.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorBaseResponse {
    private List<ErrorResponse> errors;

}
