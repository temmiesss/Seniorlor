import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.entity.Course;

import org.testng.annotations.Test;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomCourseGenerator;

public class AddNewCourseTest extends BaseTest {
    /**
     * @author Gera
     * тест на создание курса
     * **/
    @Test
    public void addNewCourseTest() {
        LoginPage loginPage = new LoginPage();
        RandomCourseGenerator randomCourseGenGera = new RandomCourseGenerator();
        Course randomCourse = randomCourseGenGera.randomCourse();

        driver.get("https://gerasrd.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"),
                        ConfigReader.getProperty("password"))
                .addNewCourse(randomCourse);

    }
}
