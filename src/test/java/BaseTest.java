import drivers.Driver;
import entity.User;
import helper.AlertHelper;
import helper.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import page.dashboard.DashboardPage;
import page.login.LoginPage;
import page.users.AddUserPage;
import page.users.UserPage;
import utils.randomEntityUtils.RandomUserGenerator;

import java.util.Random;

public abstract class BaseTest {
    public static WebDriver driver;
    AlertHelper alertHelper;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    Random random = new Random();
    DashboardPage dashboardPage = new DashboardPage();
    User randomUser = new User();
    AddUserPage addUserPage = new AddUserPage();



    @BeforeSuite
    public void beforeSuite(){
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);

    }
}
