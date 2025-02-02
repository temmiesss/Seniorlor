import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.helper.AlertHelper;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.users.UserPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomUserGenerator;
import page.DeleteCourse;

import java.time.Duration;

public abstract class BaseTest {
    public static WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    AlertHelper alertHelper;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    DeleteCourse deleteCourse = new DeleteCourse(driver);    // Rano added



    @BeforeSuite
    public void beforeSuite(){
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);
    }


//    @BeforeSuite
//    public void beforeSuite() {
//        driver = Driver.getDriver();
//    }

//    @BeforeMethod
//    public void beforeMethod(){
//        driver.get("https://ideaspark312.talentlms.com/dashboard");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        LoginPage loginPage = new LoginPage();
//        loginPage.login(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"));
//    }
}
