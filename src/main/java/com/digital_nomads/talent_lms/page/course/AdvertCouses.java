package com.digital_nomads.talent_lms.page.course;

import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdvertCouses extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'tl-header-tools')]//span[1]/a")
    public WebElement pressBuyCourse;

    @FindBy(xpath = "//div[contains(@class, 'tl-header-tools')]//span[2]/a")
    public WebElement viewCourseCatalog;

    @FindBy(xpath = "//span[@class='catalog_course_name tl-bold-item']")
    public WebElement chooseCourseFromCatalog;

    @FindBy(css = ".tl-formatted-course-name")
    public WebElement chooseCoursesFromTalant;

    @FindBy(xpath = "//li[@class='tl-marketplace-info']")
    public WebElement reedAdvertFromTalant;

    @FindBy(xpath = "//*[@id=tl-marketplace-modal-info]/div[3]/a")
    public WebElement readMore;

    @FindBy(xpath = "/html/body/div[3]/div[1]/div/div[2]/div/div/ul/li[2]/a")
    public WebElement otherCourseProviders;

    @FindBy(id = "grid_marketplace")
    public WebElement courseCatalogFromTalant;

    @FindBy(xpath = "//*[@id=tl-admin-courses]/div/div[1]/a")
    public WebElement courseEnter;

    public AdvertCouses enterToCourse() {
        webElementActions.click(courseEnter);
        return new AdvertCouses();
    }

    @Step("метод по просмотру каталога курсов ТалантЛМС")
    public AdvertCouses viewCourseCatalog(){
        webElementActions.click(viewCourseCatalog)
                .moveToElement(chooseCourseFromCatalog)
                .click(chooseCourseFromCatalog);
        return this;
    }
    @Step("метод по покупки курсов ТалантЛМС")
    public AdvertCouses buyCoursesPart(){
        webElementActions.click(pressBuyCourse)
                .moveToElement(chooseCoursesFromTalant)
                .click(chooseCoursesFromTalant);
        return this;
    }

    @Step("метод по прочтению инфо по курсам ТалантЛМС")
    public AdvertCouses readInfoFromTalant(){
        webElementActions.click(pressBuyCourse)
                .click(reedAdvertFromTalant);
        return this;
    }
    @Step("метод по прочтению более инфо ТалантЛМС")
    public AdvertCouses readMore(){
        webElementActions.click(pressBuyCourse)
                .click(reedAdvertFromTalant)
                .click(readMore);
        return this;
    }
    @Step("метод по просмотру других  курсов ТалантЛМС")
    public AdvertCouses otherCourseProvider(){
        webElementActions.click(pressBuyCourse)

                .click(otherCourseProviders)
                .moveToElement(courseCatalogFromTalant)
                .click(courseCatalogFromTalant);
        return this;
    }

}
