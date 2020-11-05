package com.thoughtworks.capacity.gtb.mvc.repository;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    static List<UserDto> userDtoList = new ArrayList<UserDto>();
    static Integer nextUserId = 1;

    public void userRegister(UserDto userDto) {
        userDto.setUserId(nextUserId);
        userDtoList.add(userDto);
        nextUserId += 1;
    }

    public Optional<UserDto> findByUserName(String userName) {
        return userDtoList.stream().
                filter(userDto -> userDto.getUserName().equals(userName)).
                findAny();
    }
}
