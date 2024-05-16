package com.example.hissabkitaab.security;

import org.springframework.stereotype.Service;

@Service
public class DoctorUserDetailsService{
//    DoctorRepository doctorRepository;
//
//    public DoctorUserDetailsService(DoctorRepository doctorRepository) {
//        this.doctorRepository = doctorRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//        Doctor user = doctorRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
//                .orElseThrow(()->new UsernameNotFoundException("User not found with username or email:"+usernameOrEmail));
//
//        Set<GrantedAuthority> authorities = user.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                user.getPassword(),
//                authorities);
//    }
}
