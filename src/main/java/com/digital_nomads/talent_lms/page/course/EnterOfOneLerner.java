package com.digital_nomads.talent_lms.page.course;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EnterOfOneLerner extends BasePage {
    @FindBy(xpath = "//div[@class='tl-ellipsis tl-bold-link']")
    public WebElement courseCatalog;

    @FindBy(xpath = "//span[@class='tl-formatted-course-name'][1]")
    public WebElement firstChosenCourse;

    public EnterOfOneLerner() {
        super();
    }
    public EnterOfOneLerner chooseFirstCourse(){
        webElementActions.click(firstChosenCourse);
        return new EnterOfOneLerner();
    }
    private boolean catalogOpened;
    public EnterOfOneLerner enterCourseCatalog(){
        webElementActions.click(courseCatalog);
        catalogOpened = true;
        return new EnterOfOneLerner();
    }
    public boolean isCatalogOpened() {
        return catalogOpened;
    }



    }



