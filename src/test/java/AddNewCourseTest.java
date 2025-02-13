import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.courses.AddCoursePage;
import com.digital_nomads.talent_lms.page.courses.AddCourseWithInvalidData;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.entity.Course;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomCourseGenerator;

import java.time.Duration;

import static com.digital_nomads.talent_lms.drivers.Driver.driver;

public class AddNewCourseTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    RandomCourseGenerator randomCourseGenGera = new RandomCourseGenerator();
    DashboardPage dashboardPage = new DashboardPage();
    AddCoursePage addCoursePage = new AddCoursePage();
    RandomCourseGenerator randomCourseGenerator = new RandomCourseGenerator();
    AddCourseWithInvalidData addCourseWithInvalidData = new AddCourseWithInvalidData();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElementActions webElementActions = new WebElementActions();


    @BeforeClass
    public void login() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"),
                ConfigReader.getProperty("password")).switchToLegacyInterface();
        webElementActions.click(dashboardPage.addCourseBtn);
    }

    /**
     * @author Gera
     * тест на создание курса
     *  Метод нажимает на Add course и открывает страницу Add course(create)
     **/

    @Test
    public void addNewCourse() {
        webElementActions.sendKeys(addCoursePage.courseName, randomCourseGenerator.randomCourseName())
                .click(addCoursePage.category)
                .click(addCoursePage.select2)
                .sendKeys(addCoursePage.description, randomCourseGenerator.randomDescription()).
                click(addCoursePage.courseCodeBtn)
                .sendKeys(addCoursePage.courseCode, randomCourseGenerator.randomCourseCode())
                .click(addCoursePage.priceBtn)
                .sendKeys(addCoursePage.price, randomCourseGenerator.randomCoursePrice())
                .click(addCoursePage.videoBtn)
                .sendKeys(addCoursePage.video, "https://www.youtube.com/watch?v=y1zbigWqwSQ")
                .click(addCoursePage.capacityBtn)
                .sendKeys(addCoursePage.capacity, randomCourseGenerator.randomCourseCapacity())
                .click(addCoursePage.submit);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-message']")));
        Assert.assertFalse(successMsg.getText().contains("Success! New course created. Now, add users or  "));
    }

    @AfterClass
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }


}
