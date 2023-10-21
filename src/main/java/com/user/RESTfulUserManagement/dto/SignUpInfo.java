package com.user.RESTfulUserManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpInfo {

    @NotBlank
    private String name;

    @Min(value = 18,message = "User age should be above 18")
    private int age;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
