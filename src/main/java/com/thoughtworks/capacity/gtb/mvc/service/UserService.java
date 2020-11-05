package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistedException;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userRegister(UserDto userDto) throws UserExistedException {
        if (userRepository.findByUserName(userDto.getUserName()).isPresent()) {
            throw new UserExistedException();
        }
        userRepository.userRegister(userDto);
    }
}
