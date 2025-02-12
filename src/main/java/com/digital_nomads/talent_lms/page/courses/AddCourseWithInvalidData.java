package com.digital_nomads.talent_lms.page.courses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class AddCourseWithInvalidData {

    @FindBy(xpath = "//span[@class='help-inline' and text()=\"'Course name' is required\"]")
    public WebElement courseNameRequired;

    @FindBy(xpath = "//span[@class='help-inline' and text()=\"'Course name' cannot exceed 100 characters\"]")
    public WebElement courseNameExceed;

    @FindBy(xpath = "//span[@class='help-inline' and text()=\"'Course code' cannot exceed 20 characters\"]")
    public WebElement courseCodeExceed;

    @FindBy(xpath = "//span[@class='help-inline' and text()=\"This is not a valid 'Price'\"]")
    public WebElement coursePriceInvalid;

    @FindBy(xpath = "//span[@class='help-inline help-course-intro-video-url' and text()=\"This is not a valid 'URL'\"]")
    public WebElement courseUrlInvalid;

    @FindBy(xpath = "//span[@class='help-inline' and text()=\"This is not a valid 'Capacity'\"]")
    public WebElement courseCapacityInvalid;


    public String getCourseNameRequiredMessage(){
        return courseNameRequired.getText();
    }
    public String getCourseNameExceedMessage(){
            return courseNameExceed.getText();
    }

    public String getCourseCodeExceedMessage() {
                return courseCodeExceed.getText();
    }

    public String getCoursePriceInvalid(){
        return coursePriceInvalid.getText();
    }

    public String getCourseUrlInvalid(){
        return courseUrlInvalid.getText();
    }

    public String getCourseCapacityInvalid(){
        return courseCapacityInvalid.getText();
    }

}
