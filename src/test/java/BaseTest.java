import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.helper.AlertHelper;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.addGroup.AddGroupPage;
import com.digital_nomads.talent_lms.page.course.DeleteCourse;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;
import com.digital_nomads.talent_lms.page.users.AddUserPage;
import com.digital_nomads.talent_lms.page.users.AddUserWithInvalidData;
import com.digital_nomads.talent_lms.page.users.EditUserDataPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.users.UserPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomUserGenerator;

import java.util.Random;


public abstract class BaseTest {
    public WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    AlertHelper alertHelper;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    AddGroupPage addGroupPage = new AddGroupPage();
    Groups group = new Groups();
    DeleteCourse deleteCourse = new DeleteCourse(driver);// Rano added
    DashboardPage dashboardPage = new DashboardPage();
    AddUserPage addUserPage = new AddUserPage();
    Random random = new Random();
    AddUserWithInvalidData addUserWithInvalidData = new AddUserWithInvalidData();
    EditUserDataPage editUserDataPage = new EditUserDataPage();
    User user = new User();


    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

}
