package com.digital_nomads.talent_lms.page.course;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Rano
 * method нажимает на иконку курсы,
 * обновляет изменения
 */
public class UpdateCourse extends BasePage {
    public WebDriver driver;

    @FindBy(xpath = "//*[@id=\"tl-admin-courses\"]/div/div[1]/a")
    public WebElement courseEnter;

    @FindBy(xpath = "//div[@class='tl-table-operations-trigger touchable']")
    public WebElement pressBurger;

    @FindBy(xpath = "//*[@id=\"tl-courses-grid\"]/tbody/tr[1]/td[5]/div/div/i[2]")
    public WebElement editPencil;

    @FindBy(xpath = "//*[@name=\"name\"]")
    public WebElement courseNameField;

    @FindBy (xpath = "//*[@class='span7']")
    public WebElement changeText;

    @FindBy(xpath = "//*[@id=\"show-coursecode\"]")
    public WebElement showCourseCode;

    @FindBy(xpath = "//*[@id=\"course-coursecode\"]")
    public WebElement changeCodeCourse;

    @FindBy(xpath = "//*[@id=\"show-price\"]")
    public WebElement showCoursePrice;

    @FindBy(xpath = "//*[@id=\"course-price\"]")
    public WebElement changeCoursePrice;

    @FindBy(xpath = "//*[@name='submit_course']")
    public WebElement submitBnt;

    public UpdateCourse() {
        super();
    }

    public UpdateCourse enterToCourse(Course course) {
        webElementActions.click(courseEnter)
                .moveToElement(pressBurger)
                .click(editPencil);

       return new UpdateCourse();
    }
    public UpdateCourse editCourse(Course course){
        webElementActions.sendKeys(courseNameField, course.getCourseName())
                .sendKeys(changeText, course.getDescription())
                .click(showCourseCode)
                .sendKeys(changeCodeCourse, course.getCourseCode())
                .click(showCoursePrice)
                .sendKeys(changeCoursePrice, course.getPrice())
                .click(submitBnt);
        return new UpdateCourse();
    }

}



