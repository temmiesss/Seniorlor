import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.helper.AlertHelper;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.category.CategoryPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.users.UserPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomUserGenerator;
import page.DeleteCourse;

public abstract class BaseTest {
    public static WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    AlertHelper alertHelper;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    DeleteCourse deleteCourse = new DeleteCourse(driver);    // Rano added
    CategoryPage categoryPage = new CategoryPage();


    @BeforeSuite
    public void beforeSuite(){
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);

    }
}
