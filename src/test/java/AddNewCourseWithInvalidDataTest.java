import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.courses.AddCoursePage;
import com.digital_nomads.talent_lms.page.courses.AddCourseWithInvalidData;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomCourseGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import static com.digital_nomads.talent_lms.drivers.Driver.driver;

/**
 * @author Gera
 * тесты на создание курса с некоректными данными
 * **/

public class AddNewCourseWithInvalidDataTest {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    AddCoursePage addCoursePage = new AddCoursePage();
    RandomCourseGenerator randomCourseGenerator = new RandomCourseGenerator();
    AddCourseWithInvalidData addCourseWithInvalidData = new AddCourseWithInvalidData();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElementActions webElementActions = new WebElementActions();

    @BeforeClass
    public void login() {
        driver.get("https://gerasrd.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"),
                ConfigReader.getProperty("password")).switchToLegacyInterface();
    }

    @Test
    public void courseNameExceed() {
        webElementActions.click(dashboardPage.addCourseBtn)
                .sendKeys(addCoursePage.courseName, randomCourseGenerator.randomCourseName100Char())
                .click(addCoursePage.submit);
        addCourseWithInvalidData.courseNameExceed = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@class='help-inline' and text()=\"'Course name' cannot exceed 100 characters\"]")));
        String actualCourseNameExceedMessage = addCourseWithInvalidData.getCourseNameExceedMessage();
        String expectedMessage = "'Course name' cannot exceed 100 characters";
        Assert.assertEquals(actualCourseNameExceedMessage, expectedMessage);

    }

    @Test
    public void courseNameEmpty() {
        webElementActions.click(dashboardPage.addCourseBtn)
                .sendKeys(addCoursePage.courseName, randomCourseGenerator.courseNameEmpty())
                .click(addCoursePage.submit);
        addCourseWithInvalidData.courseNameRequired = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@class='help-inline' and text()=\"'Course name' is required\"]")));
        String actualCourseNameRequiredMessage = addCourseWithInvalidData.getCourseNameRequiredMessage();
        String expectedMessage = "'Course name' is required";
        Assert.assertEquals(actualCourseNameRequiredMessage, expectedMessage);

    }

    @Test
    public void courseCodeExceeding() {
        webElementActions.click(dashboardPage.addCourseBtn)
                .click(addCoursePage.courseCodeBtn)
                .sendKeys(addCoursePage.courseCode, randomCourseGenerator.randomCourseCodeExceed())
                .click(addCoursePage.submit);
        addCourseWithInvalidData.courseCodeExceed = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@class='help-inline' and text()=\"'Course code' cannot exceed 20 characters\"]")));
        String actualCourseCodeExceedMsg = addCourseWithInvalidData.getCourseCodeExceedMessage();
        String expectedMsg = "'Course code' cannot exceed 20 characters";
        Assert.assertEquals(actualCourseCodeExceedMsg, expectedMsg);
    }

    @Test
    public void coursePriceInvalid() {
        webElementActions.click(dashboardPage.addCourseBtn)
                .click(addCoursePage.priceBtn)
                .sendKeys(addCoursePage.price, randomCourseGenerator.randomCoursePriceInvalid())
                .click(addCoursePage.submit);
        addCourseWithInvalidData.coursePriceInvalid = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@class='help-inline' and text()=\"This is not a valid 'Price'\"]")));
        String actualCoursePriceInvalidMsg = addCourseWithInvalidData.getCoursePriceInvalid();
        String expectedMsg = "This is not a valid 'Price'";
        Assert.assertEquals(actualCoursePriceInvalidMsg, expectedMsg);
    }

    @Test
    public void courseUrlInvalid() {
        webElementActions.click(dashboardPage.addCourseBtn)
                .click(addCoursePage.videoBtn)
                .sendKeys(addCoursePage.video, randomCourseGenerator.randomCourseUrlInvalid())
                .click(addCoursePage.submit);
        addCourseWithInvalidData.courseUrlInvalid = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@class='help-inline help-course-intro-video-url' and text()=\"This is not a valid 'URL'\"]")));
        String actualCourseUrlInvalidMsg = addCourseWithInvalidData.getCourseUrlInvalid();
        String expectedMsg = "This is not a valid 'URL'";
        Assert.assertEquals(actualCourseUrlInvalidMsg, expectedMsg);
    }

    @Test
    public void courseCapacityInvalid() {
        webElementActions.click(dashboardPage.addCourseBtn)
                .click(addCoursePage.capacityBtn)
                .sendKeys(addCoursePage.capacity, randomCourseGenerator.randomCourseCapacityInvalid())
                .click(addCoursePage.submit);
        addCourseWithInvalidData.courseCapacityInvalid = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@class='help-inline' and text()=\"This is not a valid 'Capacity'\"]")));
        String actualCourseCapacityInvalidMsg = addCourseWithInvalidData.getCourseCapacityInvalid();
        String expectedMsg = "This is not a valid 'Capacity'";
        Assert.assertEquals(actualCourseCapacityInvalidMsg, expectedMsg);
    }

    @AfterClass
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }


}
