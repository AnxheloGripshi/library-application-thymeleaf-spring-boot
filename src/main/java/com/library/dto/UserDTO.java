package com.library.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
