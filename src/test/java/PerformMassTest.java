import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.enums.CourseAction;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.course.CloneCoursePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PerformMassTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();
        dashboardPage.selectSection(Section.COURSES);
    }

    @Test
    public void performMassDeactivateTest(){
        massAction.performMassActionForCourse("Курс «Python-разработчик» ", CourseAction.DEACTIVATE);

    }
    @Test
    public void performMassActivateTest(){
        massAction.performMassActionForCourse("Курс «Python-разработчик» ", CourseAction.ACTIVATE);

    }
    @Test
    public void performMassDeleteTest(){
        massAction.performMassActionForCourse(
                "Курс «Python-разработчик» ", CourseAction.DELETE);
    }
    @Test
    public void setToCategoryTest(){
        massAction.performMassActionForCourse("Курс «Python-разработчик» ", CourseAction.SET_TO_CATEGORY);
    }
}