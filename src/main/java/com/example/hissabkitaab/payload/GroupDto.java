package com.example.hissabkitaab.payload;

import com.example.hissabkitaab.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GroupDto {
    Long id;
    private String name;

    List<UserDto> users;

}
