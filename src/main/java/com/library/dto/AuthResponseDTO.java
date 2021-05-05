package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponseDTO {

    private static final long serialVersionUID = 1L;

    private final String token;
}
