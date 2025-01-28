package com.digital_nomads.talent_lms.page.courses;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * @author Gera
 * все xpaths на странице Add course(create)
 */

public class AddCoursePage extends BasePage {

    @FindBy(xpath = "//input[@name='name']")
    public WebElement courseName;

    @FindBy(xpath = "//div[@class='select2-container']")
    public WebElement category;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    public WebElement select2;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement description;

    @FindBy(xpath = "//a[@id='show-coursecode']")
    public WebElement courseCodeBtn;

    @FindBy(xpath = "//input[@name='course_code']")
    public WebElement courseCode;

    @FindBy(xpath = "//a[@id='show-price']")
    public WebElement priceBtn;

    @FindBy(xpath = "//input[@name='price']")
    public WebElement price;

    @FindBy(xpath = "//a[@id='show-intro-video-url']")
    public WebElement videoBtn;

    @FindBy(xpath = "//input[@name='intro_video_url']")
    public WebElement video;

    @FindBy(xpath = "//a[@id='show-capacity']")
    public WebElement capacityBtn;

    @FindBy(xpath = "//input[@name='capacity']")
    public WebElement capacity;

    @FindBy(xpath = "//div[@id='s2id_course-certification']")
    public WebElement certificateBtn;

    @FindBy(xpath = "//input[@name='submit_course']")
    public WebElement submit;


}
