package com.thoughtworks.capacity.gtb.mvc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer userId;

    @NotEmpty(message = "用户名不为空")
    @Size(min = 3, max = 10, message = "用户名不合法")
    @Pattern(regexp = "[0-9A-Za-z_]*", message = "用户名不合法")
    @JsonProperty("username")
    private String userName;

    @NotEmpty(message = "密码不为空")
    @Size(min = 5, max = 12, message = "密码不合法")
    @JsonProperty("password")
    private String userPassword;

    @Email(message = "邮箱地址不合法")
    @JsonProperty("email")
    private String userEmail;
}
