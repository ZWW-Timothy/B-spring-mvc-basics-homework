package com.thoughtworks.capacity.gtb.mvc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.thoughtworks.capacity.gtb.mvc.service.UserEmailCheck;
import com.thoughtworks.capacity.gtb.mvc.service.UserNameBlankCheck;
import com.thoughtworks.capacity.gtb.mvc.service.UserNameInvalidCheck;
import com.thoughtworks.capacity.gtb.mvc.service.UserPasswordBlankCheck;
import com.thoughtworks.capacity.gtb.mvc.service.UserPasswordInvalidCheck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty("id")
    private Integer userId;

    @NotBlank(message = "用户名不为空", groups = {UserNameBlankCheck.class})
    @Size(min = 3, max = 10, message = "用户名不合法", groups = {UserNameInvalidCheck.class})
    @Pattern(regexp = "[0-9A-Za-z_]*", message = "用户名不合法", groups = {UserNameInvalidCheck.class})
    @JsonProperty("username")
    private String userName;

    @NotBlank(message = "密码不为空", groups = {UserPasswordBlankCheck.class})
    @Size(min = 5, max = 12, message = "密码不合法", groups = {UserPasswordInvalidCheck.class})
    @JsonProperty("password")
    private String userPassword;

    @Email(message = "邮箱地址不合法", groups = {UserEmailCheck.class})
    @JsonProperty("email")
    private String userEmail;
}
