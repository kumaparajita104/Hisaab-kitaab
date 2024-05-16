package com.example.hissabkitaab.services.Impl;

import com.example.hissabkitaab.entity.Role;
import com.example.hissabkitaab.entity.Trip;
import com.example.hissabkitaab.entity.User;
import com.example.hissabkitaab.exception.ResourceNotFoundException;
import com.example.hissabkitaab.exception.UsernameNotFoundException;
import com.example.hissabkitaab.payload.UserDto;
import com.example.hissabkitaab.repositories.UserRepository;
import com.example.hissabkitaab.services.UserService;
import org.modelmapper.ModelMapper;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    ModelMapper  mapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDto getUserByUserName(String username) {
        User user= userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User","username",username));



        return mapToDTO(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));


        return mapToDTO(user);
    }

    @Override
    public List<Role> getRoleById(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> getALlUsers() {
       return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto,Long userId) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());

        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

    }
    private UserDto mapToDTO(User user)
    {
        UserDto userDto=mapper.map(user,UserDto.class);
        /*PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setCreatedAt(LocalDateTime.now());
        postDto.setContent(post.getContent());*/
        return userDto;

    }
}
