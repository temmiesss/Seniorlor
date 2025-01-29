import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.helper.AlertHelper;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;
import com.digital_nomads.talent_lms.page.users.AddUserPage;
import com.digital_nomads.talent_lms.page.users.AddUserWithInvalidData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.users.UserPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomUserGenerator;
import com.digital_nomads.talent_lms.page.course.DeleteCourse;

import java.util.Random;

public abstract class BaseTest {
    public static WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    AlertHelper alertHelper;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    DeleteCourse deleteCourse = new DeleteCourse(driver);// Rano added
    DashboardPage dashboardPage = new DashboardPage();
    AddUserPage addUserPage = new AddUserPage();
    Random random = new Random();
    AddUserWithInvalidData addUserWithInvalidData = new AddUserWithInvalidData();



    @BeforeSuite
    public void beforeSuite(){
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);

    }
}
