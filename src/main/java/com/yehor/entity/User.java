package com.yehor.entity;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userName;
    private String password;
    private String generatedSalt;
    private Role role;
}
