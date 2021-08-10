package com.iryna.entity;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userName;
    private String password;
}
