import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.AssertJUnit.fail;

/**
 * @author Rano
 * в тесте удаление рандомного курса и assert  на успешное удаление курса
 * **/
public class DeleteCourseTest extends BaseTest {
    @Test
    public void deleteSelectedCourse() throws InterruptedException {

        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();

        Course randomCourse =randomCourseGenerator.randomCourse();
        deleteCourse.enterToCourse(randomCourse);
        deleteCourse.openCourse();
        deleteCourse.deleteCourse();
        deleteCourse.cancelDelete();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text() = '" + randomCourse.getCourseName() + "']")));

            Assert.assertTrue(true);
        } catch (TimeoutException e) {
            fail("Курс не был удален");
        }

    }
}
