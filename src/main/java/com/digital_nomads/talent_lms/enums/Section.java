package com.digital_nomads.talent_lms.enums;

import lombok.Getter;

@Getter
public enum Section {

    USERS("Users"),
    COURSES("Courses"),
    CATEGORIES("Categories"),
    GROUPS("Groups"),
    BRANCHES("Branches"),
    EVENTS_ENGINE("Events engine"),
    USER_TYPES("User types"),
    IMPORT_EXPORT("Import - Export"),
    REPORTS("Reports"),
    ACCOUNT_SETTINGS("Account & Settings");

    private final String sectionName;

    Section(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return sectionName;
    }
}
