import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
/** @author Rano
 * создан тест по клонированию рандомного курса
 * также есть возможность отмены клонирования, на данный момент закомичено, но он рабочий
 * Asserts
 **/
public class CloneCourseTest extends BaseTest{

    @Test
    public void cloneCourseTest(){

        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();

        Course randomCourse =randomCourseGenerator.randomCourse();
        cloneCoursePage.enterToCourse(randomCourse);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[@class='tl-formatted-course-name']")));
        Assert.assertNotNull(course, "Ошибка: Курс не найден!");

        cloneCoursePage.openCourse();
        cloneCoursePage.setCloneBtn();
//        cloneCoursePage.cancelDelete();

        WebElement clonedCourse = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(text(), '(clone)')]")));
        Assert.assertTrue(clonedCourse != null, "Ошибка: Курс не был клонирован!");

        System.out.println("Курс успешно клонирован!");
    }
}
