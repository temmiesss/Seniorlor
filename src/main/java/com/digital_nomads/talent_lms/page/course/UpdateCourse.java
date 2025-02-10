package com.digital_nomads.talent_lms.page.course;
import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

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
    public void openRandomCourse() {
        List<WebElement> courses = driver.findElements(By.xpath("//table[@id='tl-courses-grid']//tr/td[1]//a"));

        if (courses.isEmpty()) {
            throw new NoSuchElementException("Нет доступных курсов для редактирования!");
        }
        WebElement randomCourse = courses.get(new Random().nextInt(courses.size()));
        randomCourse.click();
    }
    public void changeCourseName(String newName) {
        WebElement nameField = driver.findElement(By.id("course-name-input"));
        nameField.clear();
        nameField.sendKeys(newName);
    }
    public void changeDescription(String newDescription) {
        WebElement descriptionField = driver.findElement(By.id("course-description-input"));
        descriptionField.clear();
        descriptionField.sendKeys(newDescription);
    }
    public void changeCategory(String category) {
        WebElement categoryDropdown = driver.findElement(By.id("course-category"));
        Select select = new Select(categoryDropdown);
        select.selectByVisibleText(category);
    }
    public void changeStatus(String status) {
        WebElement statusDropdown = driver.findElement(By.id("course-status"));
        Select select = new Select(statusDropdown);
        select.selectByVisibleText(status);
    }
    public void changeStartDate(String startDate) {
        WebElement startDateField = driver.findElement(By.id("course-start-date"));
        startDateField.clear();
        startDateField.sendKeys(startDate);
    }
    public void changeEndDate(String endDate) {
        WebElement endDateField = driver.findElement(By.id("course-end-date"));
        endDateField.clear();
        endDateField.sendKeys(endDate);
    }
    public void addInstructor(String instructorName) {
        WebElement instructorField = driver.findElement(By.id("course-instructor"));
        instructorField.clear();
        instructorField.sendKeys(instructorName);
    }
    public void changePrice(String price) {
        WebElement priceField = driver.findElement(By.id("course-price"));
        priceField.clear();
        priceField.sendKeys(price);
    }
    public void uploadCourseImage(String imagePath) {
        WebElement uploadButton = driver.findElement(By.id("course-image-upload"));
        uploadButton.sendKeys(imagePath);
    }
    public void saveChanges() {
        WebElement saveButton = driver.findElement(By.id("save-course-button"));
        saveButton.click();
    }
    public void cancelChanges() {
        WebElement cancelButton = driver.findElement(By.id("cancel-course-button"));
        cancelButton.click();
    }

}



