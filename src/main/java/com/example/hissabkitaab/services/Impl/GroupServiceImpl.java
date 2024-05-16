package com.example.hissabkitaab.services.Impl;

import com.example.hissabkitaab.entity.*;
import com.example.hissabkitaab.exception.PostCommentException;
import com.example.hissabkitaab.exception.ResourceNotFoundException;
import com.example.hissabkitaab.payload.ExpensesDto;
import com.example.hissabkitaab.payload.GroupDto;
import com.example.hissabkitaab.payload.UserDto;
import com.example.hissabkitaab.repositories.ExpenseRepository;
import com.example.hissabkitaab.repositories.GroupRepository;
import com.example.hissabkitaab.repositories.TripRepository;
import com.example.hissabkitaab.repositories.UserRepository;
import com.example.hissabkitaab.services.GroupService;
import jakarta.persistence.SecondaryTable;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private TripRepository tripRepository;
    ExpenseRepository expenseRepository;
    GroupRepository groupRepository;
    UserRepository userRepository;
    ModelMapper mapper;

    public GroupServiceImpl(TripRepository tripRepository, ExpenseRepository expenseRepository, GroupRepository groupRepository,UserRepository userRepository,ModelMapper mapper) {
        this.tripRepository = tripRepository;
        this.expenseRepository = expenseRepository;
        this.groupRepository=groupRepository;
        this.userRepository=userRepository;
        this.mapper = mapper;
    }

    @Override
    public GroupDto createGroup(long tripId, GroupDto groupDto) {
        Group group = mapToEntity(groupDto);
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new ResourceNotFoundException("Trip","Id",tripId));
        //comment.setCreatedAt(LocalDateTime.now());
        group.setTrip(trip);
        trip.setGroup(group);
        Group newGroup = groupRepository.save(group);
        return maptoDto(newGroup);
    }

    @Override
    public GroupDto addUser(long tripId, long groupId, long userId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new ResourceNotFoundException("Trip","Id",tripId));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group","Id",groupId));
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        //comment.setCreatedAt(LocalDateTime.now());
        //user.setGroup(group);
        List<User> users=group.getUsers();


        User userGroup = userRepository.findById(userId).get();
        users.add(userGroup);
        group.setUsers(users);
        group.setTrip(trip);
        Group newGroup = groupRepository.save(group);
        GroupDto groupDto=maptoDto(newGroup);

        return groupDto;

    }

    @Override
    public List<UserDto> allUsers(long tripId, long groupId) {
        Trip trip=tripRepository.findById(tripId).orElseThrow(()->new ResourceNotFoundException("Trip","id",tripId));
        Group group=groupRepository.findById(groupId).orElseThrow(()->new ResourceNotFoundException("Group","id",groupId));

        if(!group.getTrip().getId().equals(trip.getId()))
        {
            throw new PostCommentException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        List<User> users = group.getUsers();
        return users.stream().map(user -> maptoDto(user)).collect(Collectors.toList());

    }

    private GroupDto maptoDto(Group group)
    {
        GroupDto groupDto=mapper.map(group, GroupDto.class);
        /*CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setCreatedAt(LocalDateTime.now());*/
        return groupDto;
    }
    private UserDto maptoDto(User user)
    {
        UserDto userDto=mapper.map(user, UserDto.class);
        /*CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        commentDto.setCreatedAt(LocalDateTime.now());*/
        return userDto;
    }

    private Group mapToEntity(GroupDto groupDto)
    {
        Group group = mapper.map(groupDto,Group.class);
        /*Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setCreatedAt(LocalDateTime.now());*/
        return group;
    }
}
