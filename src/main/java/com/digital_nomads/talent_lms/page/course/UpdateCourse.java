package com.digital_nomads.talent_lms.page.course;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.page.BasePage;
import io.qameta.allure.Step;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Rano
 * methodы по изменению курсов,
 *
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

    @FindBy(xpath = "//a[contains(text(), 'cancel')]")
    public WebElement cancelBtn;

    @FindBy(xpath = "//*[@id=\"tl-goto-course\"]")
    public WebElement returnToCourseCatalog;

    @FindBy(xpath = "//*[@id=\"course-capacity\"]")
    public WebElement courseCapacity;

    @FindBy(xpath = "//*[@id=\"show-capacity\"]")
    public WebElement showCapacity;

    @FindBy(xpath = "//span[contains(@class, 'select2-chosen')]")
    public WebElement pressSelectorOfCategory;

    @FindBy(xpath = "//*[@id=\"icon-time-options\"]")
    public WebElement pressTimeOptions;

    @FindBy(xpath = "//*[@id=\"control-group-time-options\"]/div[1]/div/div/a[2]")
    public WebElement pressTimeLimit;

    @FindBy(id = "course-timelimit")
    public WebElement inputTimeLimit;

    public UpdateCourse() {
        super();
    }

    @Step("метод входа в исчезающем меню")
    public UpdateCourse enterToCourse(Course course) {
        webElementActions.click(courseEnter)
                .moveToElement(pressBurger)
                .click(editPencil);

       return new UpdateCourse();
    }
    @Step("метод рандомного изменения курса")
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
    @Step("метод отмены рандомного изменения курса")
    public UpdateCourse cancelChanges(Course course) {
        webElementActions.sendKeys(courseNameField, course.getCourseName())
                .sendKeys(changeText, course.getDescription())
                .click(showCourseCode)
                .sendKeys(changeCodeCourse, course.getCourseCode())
                .click(showCoursePrice)
                .sendKeys(changeCoursePrice, course.getPrice())
                .click(cancelBtn);
        return new UpdateCourse();
    }

    @Step("метод возврата в каталог курсов")
    public UpdateCourse goToCourseContent(Course course){
        webElementActions.sendKeys(courseNameField, course.getCourseName())
                .sendKeys(changeText, course.getDescription())
                .click(showCourseCode)
                .sendKeys(changeCodeCourse, course.getCourseCode())
                .click(showCoursePrice)
                .sendKeys(changeCoursePrice, course.getPrice())
                .click(returnToCourseCatalog);
        return new UpdateCourse();

    }
    @Step("Изменение только цены курса")
    public UpdateCourse changePrice(Course course, String newPrice) {
        webElementActions
                .click(showCoursePrice)
                .clear(changeCoursePrice)
                .sendKeys(changeCoursePrice, newPrice)
                .click(submitBnt);

        return this;
    }
    @Step("Изменение только цены курса")
    public UpdateCourse changeCode(Course course, String newCode) {
        webElementActions
                .click(showCourseCode)
                .clear(changeCodeCourse)
                .sendKeys(changeCodeCourse, newCode)
                .click(submitBnt);

        return this;
    }
    @Step("Изменение только описание курса")
    public UpdateCourse changeText(Course course, String newText) {
        webElementActions
                .click(changeText)
                .clear(changeText)
                .sendKeys(changeText, newText)
                .click(submitBnt);

        return this;
    }
    @Step("Изменение только емкости курса")
    public UpdateCourse changeCapacity(Course course, String newCapacity) {
        webElementActions
                .click(showCapacity)
                .clear(courseCapacity)
                .sendKeys(courseCapacity, newCapacity)
                .click(submitBnt);
        return this;
    }
    @Step("метод изменяет название курса")
    public UpdateCourse changeNameOfCourse(Course course, String newNameOfCourse){
        webElementActions
                .click(courseNameField)
                .clear(courseNameField)
                .sendKeys(courseNameField, newNameOfCourse)
                .click(submitBnt);
        return new UpdateCourse();
    }
   @Step("метод изменяет категорию курса")
    public UpdateCourse changeCategory(Course course, String  newCategory){
        webElementActions
                .click(pressSelectorOfCategory)
                .scrollToElement(pressSelectorOfCategory)
                .click(pressSelectorOfCategory)
                .click(submitBnt);
        return new UpdateCourse();
   }

   @Step("метод выбирает лимит для курса")
    public UpdateCourse chooseTimeLimitOfCourse(Course course, String newTimeLimit){
        webElementActions
                .click(pressTimeOptions)
                .click(pressTimeLimit)
                .sendKeys(inputTimeLimit, newTimeLimit)
                .click(submitBnt);
        return new UpdateCourse();

   }
    @Step("метод выбирает с невалидным лимитом для курса")
    public UpdateCourse chooseInvalidTimeLimitOfCourse(Course course, String invalidTimeLimit){
        webElementActions
                .click(pressTimeOptions)
                .click(pressTimeLimit)
                .click(inputTimeLimit)
                .clear(inputTimeLimit)
                .sendKeys(inputTimeLimit, invalidTimeLimit)
                .click(submitBnt);
        return new UpdateCourse();

    }



}



