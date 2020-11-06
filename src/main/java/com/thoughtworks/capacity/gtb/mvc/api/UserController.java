package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.dto.UserDto;
import com.thoughtworks.capacity.gtb.mvc.exception.UserLoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserRegisterExistedException;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
    public void userRegister(@RequestBody @Valid UserDto userDto) throws UserRegisterExistedException {
        userService.userRegister(userDto);
    }

    @GetMapping("/login")
    public UserDto userLogin(@RequestParam
                                 @NotEmpty(message = "用户名不为空")
                                 @Size(min = 3, max = 10, message = "用户名不合法")
                                 @Pattern(regexp = "[0-9A-Za-z_]*", message = "用户名不合法")
                                         String username,
                             @RequestParam
                                 @NotEmpty(message = "密码不为空")
                                 @Size(min = 5, max = 12, message = "密码不合法")
                                         String password) throws UserLoginFailedException {
        return userService.userLogin(username, password);
    }
}
