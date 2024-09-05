package com.web.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private Integer age;
    private Long contactno;
    private String city;
    private String userType;
    private String adminPassword;

    
}

