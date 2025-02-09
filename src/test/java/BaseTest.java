import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.helper.AlertHelper;
import com.digital_nomads.talent_lms.helper.BrowserManager;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.course.CloneCoursePage;
import com.digital_nomads.talent_lms.page.course.DeleteCourse;
import com.digital_nomads.talent_lms.page.course.UpdateCourse;
import com.digital_nomads.talent_lms.page.users.CsvGenerator;
import com.digital_nomads.talent_lms.page.users.*;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomCourseGenerator;
import com.digital_nomads.talent_lms.page.addGroup.AddGroupPage;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.category.CategoryPage;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomUserGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import com.digital_nomads.talent_lms.page.login.LoginPage;

import java.util.Random;

public abstract class BaseTest {
    public User randomUser;
    public WebDriver driver;
    public BrowserManager browserManager;
    WebElementActions webElementActions = new WebElementActions();
    AlertHelper alertHelper;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    DeleteCourse deleteCourse = new DeleteCourse();    // Rano added
    CloneCoursePage cloneCoursePage = new CloneCoursePage();
    UpdateCourse updateCourse = new UpdateCourse();
    CategoryPage categoryPage = new CategoryPage();
    AddGroupPage addGroupPage = new AddGroupPage();
    Groups group = new Groups();
    DashboardPage dashboardPage = new DashboardPage();
    AddUserPage addUserPage = new AddUserPage();
    Random random = new Random();
    AddUserWithInvalidData addUserWithInvalidData = new AddUserWithInvalidData();
    EditUserDataPage editUserDataPage = new EditUserDataPage();
    User user = new User();
    RandomCourseGenerator randomCourseGenerator = new RandomCourseGenerator();
    CsvGenerator csvGenerator = new CsvGenerator();
    FileUtils fileUtils = new FileUtils();
    SortingUserPage sortingUserPage = new SortingUserPage();
    UserCoursesPage userCoursesPage = new UserCoursesPage();
    UserGroupsPage userGroupsPage = new UserGroupsPage();
    UserFilesPage userFilesPage = new UserFilesPage();

    @BeforeSuite
    public void beforeSuite(){
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);
    }

    @AfterClass
    public void tearDown(){
        if (driver != null){
            driver.close();
        }
    }


}
