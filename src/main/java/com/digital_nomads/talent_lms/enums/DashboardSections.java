package com.digital_nomads.talent_lms.enums;

import lombok.Getter;

@Getter
public enum DashboardSections {

    USERS("//div[@class='tl-bold-link']/a[normalize-space()='Users']", "user"),
    COURSES("//div[@class='tl-bold-link']/a[normalize-space()='Courses']", "course"),
    CATEGORIES("//div[@class='tl-bold-link']/a[normalize-space()='Categories']", "category"),
    GROUPS("//div[@class='tl-bold-link']/a[normalize-space()='Groups']", "group"),
    BRANCHES("//div[@class='tl-bold-link']/a[normalize-space()='Branches']", "branch"),
    EVENTS_ENGINE("//div[@class='tl-bold-link']/a[normalize-space()='Events engine']", "eventsengine"),
    USER_TYPES("//div[@class='tl-bold-link']/a[normalize-space()='User types']", "acl"),
    IMPORT_EXPORT("//div[@class='tl-bold-link']/a[normalize-space()='Import - Export']", "import"),
    REPORTS("//div[@class='tl-bold-link']/a[normalize-space()='Reports']", "reports"),
    ACCOUNT_SETTINGS("//div[@class='tl-bold-link']/a[normalize-space()='Account & Settings']", "account"),
    ADD_USER("//div[@class='hidden-phone']/a[normalize-space()='Add user']","user"),
    ADD_COURSE("//div[@class='hidden-phone']/a[normalize-space()='Add course']", "course"),
    COURSE_STORE("//div[@class='hidden-phone']/a[normalize-space()='Course Store']", "talentlibrary_index"),
    ADD_CATEGORY("//div[@class='hidden-phone']/a[normalize-space()='Add category']", "category"),
    ADD_GROUP("//div[@class='hidden-phone']/a[normalize-space()='Add group']", "group"),
    ADD_BRANCH("//div[@class='hidden-phone']/a[normalize-space()='Add branch']", "branch"),
    ADD_NOTIFICATION("//div[@class='hidden-phone']/a[normalize-space()='Add notification']", "notification_create"),
    ADD_AUTOMATION("//div[@class='hidden-phone']/a[normalize-space()='Add automation']", "automatedaction_create"),
    ADD_USER_TYPE("//div[@class='hidden-phone']/a[normalize-space()='Add user type']", "acl"),
    IMPORT("//div[@class='hidden-phone']/a[normalize-space()='Import']", "import"),
    EXPORT("//div[@class='hidden-phone']/a[normalize-space()='Export']", "export"),
    USERS_REPORT("//div[@id='tl-admin-dashboard-reports']/div/div/a[normalize-space()='Users']", "reports/user"),
    COURSES_REPORTS("//div[@id='tl-admin-dashboard-reports']/div/div/a[normalize-space()='Courses']", "reports/course"),
    BRANCHES_REPORTS("//div[@id='tl-admin-dashboard-reports']/div/div/a[normalize-space()='Branches']", "reports/branch"),
    GROUPS_REPORTS("//div[@id='tl-admin-dashboard-reports']/div/div/a[normalize-space()='Groups']", "reports/group"),
    SCORM_REPORTS("//div[@id='tl-admin-dashboard-reports']/div/div/a[normalize-space()='Scorm']", "scorm"),
    TESTS_REPORTS("//div[@id='tl-admin-dashboard-reports']/div/div/a[normalize-space()='Tests']", "test"),
    SURVEYS_REPORTS("//p/a[normalize-space()='Surveys']", "survey"),
    ASSIGNMENTS_REPORTS("//p/a[normalize-space()='Assignments']", "assignment"),
    ILTS_REPORTS("//p/a[normalize-space()='ILTs']", "ilt"),
    INFOGRAPHICS_REPORTS("//div[@class='hidden-phone']/a[contains(text(),'Infographics')]", "infographics"),
    TRAINING_MATRIX_REPORTS("//div[@class='hidden-phone']/a[contains(text(),'Training matrix')]", "trainingmatrix"),
    TIMELINE_REPORTS("//div[@class='hidden-phone']/p/a[contains(text(),'Timeline')]", "timeline"),
    CUSTOM_REPORTS("//div[@class='hidden-phone']/a[contains(text(),'Custom')]", "custom_index"),
    HOMEPAGE_ACCOUNT_SETTINGS("//div[@id='tl-admin-settings']/div/a[contains(text(), 'Homepage')]", "cms_index"),
    USERS_ACCOUNT_SETTINGS("//div[@id='tl-admin-settings']/div/a[contains(text(), 'Users')]", "users_index"),
    THEMES_ACCOUNT_SETTINGS("//div[@id='tl-admin-settings']/div/a[contains(text(), 'Themes')]", "themes_index"),
    CERTIFICATES_ACCOUNT_SETTINGS("//div[@id='tl-admin-settings']/div/a[contains(text(), 'Certificates')]", "certifications_index"),
    GAMIFICATION_ACCOUNT_SETTINGS("//div[@id='tl-admin-settings']/div/a[contains(text(), 'Gamification')]", "gamification_index"),
    E_COMMERCE_ACCOUNT_SETTINGS("//div[@id='tl-admin-settings']/div/a[contains(text(), 'E-commerce')]", "ecommerce_index"),
    DOMAIN_ACCOUNT_SETTINGS("//div[@id='tl-admin-settings']/div/a[contains(text(), 'Domain')]", "domain_index"),
    SUBSCRIPTION_ACCOUNT_SETTINGS("//div[@id='tl-admin-settings']/div/a[contains(text(), 'Subscription')]", "subscription_index"),
    NEW_INTERFACE_ACCOUNT_SETTINGS("//*[@id='tl-admin-settings']/div[@class='hidden-phone']/a/strong", "plus_index");

    private final String xpath;
    private final String urlPart;

    DashboardSections(String xpath, String urlPart) {
        this.xpath = xpath;
        this.urlPart = urlPart;
    }
}
