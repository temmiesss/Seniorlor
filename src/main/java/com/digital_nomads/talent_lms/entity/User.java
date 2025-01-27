package com.digital_nomads.talent_lms.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
