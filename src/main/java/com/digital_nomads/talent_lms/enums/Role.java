package com.digital_nomads.talent_lms.enums;

import lombok.Getter;

public enum Role {

    ADMINISTRATOR("Administrator"),
    INSTRUCTOR("Instructor"),
    LEANER("Leaner");

    @Getter
    private String role;

    Role(String role) {
        this.role = role;
    }
}
