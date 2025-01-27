package page;

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
 * **/

public class DeleteCourse extends BasePage {

    @FindBy(xpath = "//a[normalize-space()='Courses']/parent::div[@class='tl-bold-link']")
    public WebElement courseEnter;

    @FindBy(xpath = "input[@class='hidden-mobile tl-grid-checkbox']")
    public WebElement checkCourse;

    @FindBy(xpath = "//div[@class='tl-table-operations-trigger touchable']")
    public WebElement pressBurger;

    @FindBy(xpath = "//a[@id='tl-confirm-submit']")
    public WebElement deleteBtn;

    @FindBy(xpath = "//a[@class='btn]")
    public WebElement canselBtn;


    private WebDriver driver;

    public DeleteCourse(WebDriver driver) {
        this.driver = driver;
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
            checkCourse.click();
            System.out.println("Курс выбран.");
        } catch (Exception e) {
            System.out.println("Ошибка при выборе курса: " + e.getMessage());
        }
    }
    public void openBurgerMenu() {
        try {
            pressBurger.click();
            System.out.println("Бургер-меню открыто.");
        } catch (Exception e) {
            System.out.println("Ошибка при открытии бургер-меню: " + e.getMessage());
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

    public void cancelDelete() {
        try {
            canselBtn.click();
            System.out.println("Удаление отменено.");
        } catch (Exception e) {
            System.out.println("Ошибка при отмене удаления: " + e.getMessage());
        }
    }

    public boolean isCourseInvisible() {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            webDriverWait.until(ExpectedConditions.invisibilityOf(courseEnter));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCourseVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(courseEnter));
            return courseEnter.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean isCourseVisibleAfterCancel() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(courseEnter));
            return courseEnter.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
