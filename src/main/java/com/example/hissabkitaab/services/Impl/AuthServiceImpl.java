package com.example.hissabkitaab.services.Impl;

import com.example.hissabkitaab.entity.*;


import com.example.hissabkitaab.payload.LoginDto;
import com.example.hissabkitaab.entity.Role;
import com.example.hissabkitaab.payload.UserDto;
import com.example.hissabkitaab.repositories.*;

import com.example.hissabkitaab.security.*;
import com.example.hissabkitaab.services.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;


    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
        this.jwtTokenProvider=jwtTokenProvider;

    }

    @Override
    public String login(LoginDto loginDto) {
       Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenProvider.generateToken(authentication);
        return token;
    }


    @Override
    public String register(UserDto registerDto) {

//        if(userRepository.existsByUsername(registerDto.getUsername())){
//            throw new PostCommentException(HttpStatus.BAD_REQUEST,"Username already exists");
//        }
//        if(userRepository.existsByEmail(registerDto.getEmail())){
//            throw new PostCommentException(HttpStatus.BAD_REQUEST,"Email already exists");
//        }
        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUsername(registerDto.getUsername());
        user.setAge(registerDto.getAge());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);



        return "User registered successfully";








    }


}
