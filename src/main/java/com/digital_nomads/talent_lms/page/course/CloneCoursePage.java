package com.digital_nomads.talent_lms.page.course;

import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CloneCoursePage extends BasePage {
    public WebDriver driver;

    @FindBy(xpath = "//*[@id=\"tl-admin-courses\"]/div/div[1]/a")
    public WebElement courseEnter;

    @FindBy(name = "name")
    public WebElement courseNameField;

    @FindBy(xpath = "//*[@id=\"tl-clone-modal-confirm-submit\"]")
    public WebElement cloneBtn;

    @FindBy(xpath = "//*[@id=\"tl-clone-modal\"]/div[3]/a[2]")
    public WebElement cloneBtnCansel;

    @FindBy(xpath = "//div[@class='tl-table-operations-trigger touchable']")
    public WebElement pressBurger;

    @FindBy(xpath = "//*[@id=\"tl-courses-grid\"]/tbody/tr/td[5]/div/div/a")
    public WebElement cloneIcon;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteBtn;

    public CloneCoursePage enterToCourse(Course course) {
        webElementActions.click(courseEnter)
                .moveToElement(pressBurger)
                .click(cloneIcon);
        return new CloneCoursePage();
    }

    public void openCourse() {
        try {
            courseEnter.click();
            System.out.println("Курс открыт.");
        } catch (Exception e) {
            System.out.println("Ошибка при открытии курса: " + e.getMessage());
        }
    }
    public void selectCourse() {
        try {
            courseNameField.click();
            System.out.println("Курс выбран.");
        } catch (Exception e) {
            System.out.println("Ошибка при выборе курса: " + e.getMessage());
        }

    }
    public void setCloneBtn(){
        try {
            cloneBtn.click();
            System.out.println("Курс успешно клонирован.");
        } catch (Exception e) {
            System.out.println("Ошибка при клонировании курса: " + e.getMessage());
        }
    }
    public void cancelClone() {
        try {
            cloneBtnCansel.click();
            System.out.println("Клонирование отменено.");
        } catch (Exception e) {
            System.out.println("Ошибка при отмене клонирования: " + e.getMessage());
        }
    }
    public void deleteCourse() {
        try {
            deleteBtn.click();
            System.out.println("Курс успешно удален.");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении курса: " + e.getMessage());
        }
    }
    public int getCourseCount() {
        List<WebElement> courses = driver.findElements(By.xpath("//table/tbody/tr"));
        return courses.size();
    }
    public void searchCourse(String courseName) {
        WebElement searchInput = driver.findElement(By.xpath("//input[@type='search']"));
        searchInput.clear();
        searchInput.sendKeys(courseName + Keys.ENTER);

        // Пробное ожидание (заменить на WebDriverWait, если нужно)
        try {
            Thread.sleep(2000); // Даем странице 2 секунды на фильтрацию (лучше заменить на WebDriverWait)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> results = driver.findElements(By.xpath("//table[contains(@id, 'tl-courses-grid')]//tr"));
        System.out.println("Найдено курсов: " + results.size());
    }

}


