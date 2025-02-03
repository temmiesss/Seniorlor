package com.digital_nomads.talent_lms.page.course;

import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * @author Rano
 * courseEnter is method which allows us to enter to Course Section
 * method checkCourse allows us to check course for deleting
 * method deleteBtn is deleting choosing Course
 * **/

public class DeleteCourse extends BasePage {

    @FindBy(xpath = "//a[normalize-space()='Courses']/parent::div[@class='tl-bold-link']")
    public WebElement courseEnter;

    @FindBy(xpath = "//*[@id=\"tl-courses-grid\"]/tbody/tr[1]/td[5]/div/div/i[3]")
    public WebElement iconDelete;

    @FindBy(xpath = "//div[@class='tl-table-operations-trigger touchable']")
    public WebElement pressBurgerBtn;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteBtn;

    @FindBy(xpath = "//a[@class='btn]")
    public WebElement canselBtn;


//    public DeleteCourse(WebDriver driver) {
//        this.driver = driver;
//    }

    public CloneCoursePage enterToCourse(Course course) {
        webElementActions.click(courseEnter)
                .moveToElement(pressBurgerBtn)
                .click(iconDelete);
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
//    public void selectCourse() {
//        try {
//            checkCourse.click();
//            System.out.println("Курс выбран.");
//        } catch (Exception e) {
//            System.out.println("Ошибка при выборе курса: " + e.getMessage());
//        }
//    }
//    public void openBurgerMenu() {
//        try {
//            pressBurger.click();
//            System.out.println("Бургер-меню открыто.");
//        } catch (Exception e) {
//            System.out.println("Ошибка при открытии бургер-меню: " + e.getMessage());
//        }
//    }

    public void deleteCourse() {
        try {
            deleteBtn.click();
            System.out.println("Курс успешно удален.");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении курса: " + e.getMessage());
        }
    }

    public void cancelDelete() {
        try {
            canselBtn.click();
            System.out.println("Удаление отменено.");
        } catch (Exception e) {
            System.out.println("Ошибка при отмене удаления: " + e.getMessage());
        }
    }


}
