package com.example.hissabkitaab.controller;

import com.example.hissabkitaab.entity.User;
import com.example.hissabkitaab.payload.UserDto;
import com.example.hissabkitaab.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //ye username fetch kar rha

    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable(value = "username")String username)
    {

        UserDto userDto=userService.getUserByUserName(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    //Ye patientInfo update kar raha
    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id")Long id,@RequestBody UserDto userDto)
    {
        UserDto user =userService.updateUser(userDto,id);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

}
