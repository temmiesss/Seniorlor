import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.helper.AlertHelper;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.course.CloneCoursePage;
import com.digital_nomads.talent_lms.page.course.DeleteCourse;
import com.digital_nomads.talent_lms.page.course.UpdateCourse;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomCourseGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.users.UserPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomUserGenerator;


public abstract class BaseTest {
    public static WebDriver driver;
    WebElementActions webElementActions = new WebElementActions();
    AlertHelper alertHelper;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    DeleteCourse deleteCourse = new DeleteCourse();    // Rano added
    CloneCoursePage cloneCoursePage = new CloneCoursePage();
    UpdateCourse updateCourse = new UpdateCourse();
    RandomCourseGenerator randomCourseGenerator =new RandomCourseGenerator();



    @BeforeSuite
    public void beforeSuite(){
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);

    }
}
