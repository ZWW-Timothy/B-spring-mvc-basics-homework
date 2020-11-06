package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.exception.UserLoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserRegisterExistedException;
import com.thoughtworks.capacity.gtb.mvc.service.UserCheckSequence;
import com.thoughtworks.capacity.gtb.mvc.service.UserNameBlankCheck;
import com.thoughtworks.capacity.gtb.mvc.service.UserNameInvalidCheck;
import com.thoughtworks.capacity.gtb.mvc.service.UserPasswordBlankCheck;
import com.thoughtworks.capacity.gtb.mvc.service.UserPasswordInvalidCheck;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void userRegister(@RequestBody @Validated({UserCheckSequence.class}) UserDto userDto) throws UserRegisterExistedException {
        userService.userRegister(userDto);
    }

    @GetMapping("/login")
    @Validated({UserCheckSequence.class})
    public UserDto userLogin(@RequestParam
                             @Size(min = 3, max = 10, message = "用户名不合法", groups = {UserNameInvalidCheck.class})
                             @Pattern(regexp = "[0-9A-Za-z_]*", message = "用户名不合法", groups = {UserNameInvalidCheck.class})
                                     String username,
                             @RequestParam
                             @Size(min = 5, max = 12, message = "密码不合法", groups = {UserPasswordInvalidCheck.class})
                                     String password) throws UserLoginFailedException {
        return userService.userLogin(username, password);
    }
}
