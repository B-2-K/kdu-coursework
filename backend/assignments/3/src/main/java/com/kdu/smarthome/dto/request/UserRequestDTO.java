package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDTO {
    private String username;
    private String password;
    private String role;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
}
