package com.thoughtworks.capacity.gtb.mvc.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.exception.UserLoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserRegisterExistedException;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userRegister(UserDto userDto) throws UserRegisterExistedException {
        if (userRepository.findByUserName(userDto.getUserName()).isPresent()) {
            throw new UserRegisterExistedException();
        }
        userRepository.userRegister(userDto);
    }

    public UserDto userLogin(String userName, String userPassword) throws UserLoginFailedException {

        Optional<UserDto> userDto = userRepository.findByUserName(userName);
        if (!userDto.isPresent()) {
            throw new UserLoginFailedException();
        }
        if (!(userDto.get().getUserPassword()).equals(userPassword)) {
            throw new UserLoginFailedException();
        }
        return userDto.get();
    }
}
