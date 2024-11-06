package com.lvonce.core.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Endpoint {
    @NotNull
    Long   id;

    @NotBlank
    String httpMethod;

    @NotBlank
    String httpUrl;

    @NotBlank
    String sqlTemplate;
}
