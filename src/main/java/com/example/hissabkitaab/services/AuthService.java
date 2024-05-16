package com.example.hissabkitaab.services;

import com.example.hissabkitaab.payload.UserDto;
import com.example.hissabkitaab.payload.*;
//import com.example.selfhelp.payload.DoctorRegisterDto;
//import com.example.selfhelp.payload.LoginDto;
//import com.example.selfhelp.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    //String doctorLogin(DoctorLoginDto loginDto);
    String register(UserDto registerDto);

    //String doctorRegister(DoctorRegisterDto registerDto);


}
