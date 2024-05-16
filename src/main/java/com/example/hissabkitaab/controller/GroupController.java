package com.example.hissabkitaab.controller;

import com.example.hissabkitaab.entity.User;
import com.example.hissabkitaab.payload.GroupDto;
import com.example.hissabkitaab.payload.UserDto;
import com.example.hissabkitaab.services.ExpensesService;
import com.example.hissabkitaab.services.GroupService;
import com.example.hissabkitaab.services.TripService;
import com.example.hissabkitaab.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/groups")
public class GroupController {
    TripService tripService;
    GroupService groupService;
    UserService userService;
    ExpensesService expensesService;

    public GroupController(TripService tripService, GroupService groupService, UserService userService, ExpensesService expensesService) {
        this.tripService = tripService;
        this.groupService = groupService;
        this.userService = userService;
        this.expensesService = expensesService;
    }
    @PostMapping("/{tripId}/createGroup")
    public ResponseEntity<GroupDto> createGroup(@PathVariable(value = "tripId") long tripId, @RequestBody GroupDto groupDto)
    {
        GroupDto newGroup= groupService.createGroup(tripId,groupDto);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);

    }
    @PostMapping("/{tripId}/{groupId}/addUser/{userId}")
    public ResponseEntity<GroupDto> addUser(@PathVariable(value = "tripId") long tripId, @PathVariable(value="groupId")Long groupId,@PathVariable(value="userId")Long userId,@RequestBody GroupDto groupDto)
    {
        GroupDto updatedGroup= groupService.addUser(tripId,groupId,userId);
        return new ResponseEntity<>(updatedGroup, HttpStatus.CREATED);

    }
    @GetMapping("/{tripId}/{groupId}")
    public List<UserDto> getUsersByGroupId(@PathVariable(value = "tripId") long tripId, @PathVariable(value = "groupId") long groupId)
    {
        return groupService.allUsers(tripId,groupId);

    }
}
