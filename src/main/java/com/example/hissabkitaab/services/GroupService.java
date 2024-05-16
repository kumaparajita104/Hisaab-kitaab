package com.example.hissabkitaab.services;

import com.example.hissabkitaab.payload.ExpensesDto;
import com.example.hissabkitaab.payload.GroupDto;
import com.example.hissabkitaab.payload.UserDto;

import java.util.List;
import java.util.Set;

public interface GroupService {
    GroupDto createGroup(long tripId, GroupDto groupDto);
    GroupDto addUser(long tripId,long groupId,long userId);

    List<UserDto> allUsers(long tripId, long groupId);



}
