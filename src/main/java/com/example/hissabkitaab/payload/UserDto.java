package com.example.hissabkitaab.payload;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String password;
    private String username;

}
