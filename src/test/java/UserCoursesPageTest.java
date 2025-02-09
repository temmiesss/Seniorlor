import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Akylai
 */
public class UserCoursesPageTest extends BaseTest {
    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.USERS);
        randomUser = randomUserGenerator.randomUser();
    }

    @Test
    public void testEnrollCoursesToUser() {
        String username = "mary";
        editUserDataPage.openUserProfile(username);
        userCoursesPage = editUserDataPage.clickCourses();
        String courseName = "IT";
        userCoursesPage.enrollCoursesToUser(courseName);

        Assert.assertTrue(userCoursesPage.isCourseEnrolled(courseName), "Course should be enrolled, but unEnrollIcon is missing");
    }

    @Test
    public void testPerformEnrollAction() {
        String courseName = "IT";
        editUserDataPage.openUserProfile("mary");
        userCoursesPage = editUserDataPage.clickCourses();

        userCoursesPage.performUserCoursesMassAction(courseName, "Enroll");

        String enrollSuccessMessage = userCoursesPage.getAlertMessage();
        String expectedEnrollMessage = "Successfully assigned 1 course";
        Assert.assertEquals(enrollSuccessMessage, expectedEnrollMessage, "Enroll success message is incorrect");

        Assert.assertTrue(userCoursesPage.isCourseEnrolled(courseName), "Course should be enrolled, but unEnrollIcon is missing");
    }

    @Test
    public void testPerformUnenrollAction() {
        String courseName = "IT";
        editUserDataPage.openUserProfile("mary");
        userCoursesPage = editUserDataPage.clickCourses();

        userCoursesPage.performUserCoursesMassAction(courseName, "Unenroll");

        String unenrollSuccessMessage = userCoursesPage.getAlertMessage();
        String expectedUnenrollMessage = "Successfully unenrolled from 1 course";
        Assert.assertEquals(unenrollSuccessMessage, expectedUnenrollMessage, "Unenroll success message is incorrect");

        Assert.assertTrue(userCoursesPage.isCourseUnenrolled(courseName), "Course should be unenrolled, but unEnrollIcon is still visible");
    }


}
