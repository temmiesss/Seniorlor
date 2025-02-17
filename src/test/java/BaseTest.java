import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.helper.AlertHelper;
import com.digital_nomads.talent_lms.helper.BrowserManager;
import com.digital_nomads.talent_lms.helper.WebElementActions;
import com.digital_nomads.talent_lms.page.addGroup.AddGroupPage;
import com.digital_nomads.talent_lms.page.course.CloneCoursePage;
import com.digital_nomads.talent_lms.page.course.DeleteCourse;
import com.digital_nomads.talent_lms.page.course.FileUtils;
import com.digital_nomads.talent_lms.page.users.CsvGenerator;
import com.digital_nomads.talent_lms.page.users.*;
import com.digital_nomads.talent_lms.page.course.*;
import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.page.category.CategoryPage;
import com.digital_nomads.talent_lms.page.course.EnterOfOneLerner;
import com.digital_nomads.talent_lms.page.course.UpdateCourse;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;
import com.digital_nomads.talent_lms.page.users.AddUserPage;
import com.digital_nomads.talent_lms.page.users.AddUserWithInvalidData;
import com.digital_nomads.talent_lms.page.users.EditUserDataPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomCourseGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.users.UserPage;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomUserGenerator;

import java.util.Random;


public abstract class BaseTest {
    public User randomUser;
    public WebDriver driver;
    public BrowserManager browserManager;
    WebElementActions webElementActions = new WebElementActions();
    AlertHelper alertHelper;
    UserPage userPage = new UserPage();
    LoginPage loginPage = new LoginPage();
    DeleteCourse deleteCourse = new DeleteCourse();    // Rano added
    CategoryPage categoryPage = new CategoryPage();
    AddGroupPage addGroupPage = new AddGroupPage();
    Groups group = new Groups();
    DashboardPage dashboardPage = new DashboardPage();
    AddUserPage addUserPage = new AddUserPage();
    AddUserWithInvalidData addUserWithInvalidData = new AddUserWithInvalidData();
    EditUserDataPage editUserDataPage = new EditUserDataPage();
    User user = new User();
    UpdateCourse updateCourse = new UpdateCourse();
    RandomUserGenerator randomUserGenerator = new RandomUserGenerator();
    Random random = new Random();
    CloneCoursePage cloneCoursePage = new CloneCoursePage();
    EnterOfOneLerner enterOfOneLerner = new EnterOfOneLerner();
    RandomCourseGenerator randomCourseGenerator = new RandomCourseGenerator();
    CsvGenerator csvGenerator = new CsvGenerator();
    FileUtils fileUtils = new FileUtils();
    SortingUserPage sortingUserPage = new SortingUserPage();
    UserCoursesPage userCoursesPage = new UserCoursesPage();
    UserGroupsPage userGroupsPage = new UserGroupsPage();
    UserFilesPage userFilesPage = new UserFilesPage();
    CVSGenerator cvsGenerator = new CVSGenerator();
    ReportCoursePage reportCoursePage = new ReportCoursePage();
    AdvertCouses advertCourses = new AdvertCouses();
    PerformMassActionCourse massAction = new PerformMassActionCourse();


            ;

    @BeforeSuite
    public void beforeSuite() {
        driver = Driver.getDriver();
        alertHelper = new AlertHelper(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }


}
