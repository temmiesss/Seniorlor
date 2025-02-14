package com.digital_nomads.talent_lms.page.reports;

import org.openqa.selenium.By;

/**
 * @author Kanykei
 **/

public enum ReportSection {
    USER_REPORT("User Reports", By.xpath("//a[@title='User reports']")),
    COURSE_REPORT("Course Reports", By.xpath("//a[@title='Course reports']")),
    BRANCH_REPORT("Branch Reports", By.xpath("//a[@title='Branch reports']")),
    GROUP_REPORT("Group Reports", By.xpath("//a[@title='Group reports']")),
    SCORM_REPORT("SCORM Reports", By.xpath("//a[contains(text(),'SCORM reports')]")),
    TEST_REPORT("Test Reports", By.xpath("//a[@title='Test reports']")),
    SURVEY_REPORT("Survey Reports", By.xpath("//a[@title='Survey reports']")),
    ASSIGNMENT_REPORT("Assignment Reports", By.xpath("//a[@title='Assignment reports']")),
    ILT_REPORT("ILT Reports", By.xpath("//a[@title='ILT reports']")),
    CUSTOM_REPORT("Custom Reports", By.xpath("//a[@title='Custom reports']"));


    private final String name;
    private final By locator;

    ReportSection(String name, By locator) {
        this.name = name;
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }

    public String getName() {
        return name;
    }

}