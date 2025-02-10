import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

/** @author Rano
 * создан тест по клонированию рандомного курса
 * Asserts
 **/
public class CloneCourseTest extends BaseTest{
    @BeforeMethod
    public void setUp(){
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();

    }
    @Test
    public void cloneCourseTest(){

       Course randomCourse =randomCourseGenerator.randomCourse();
        cloneCoursePage.enterToCourse(randomCourse);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[@class='tl-formatted-course-name']")));
        Assert.assertNotNull(course, "Ошибка: Курс не найден!");

        cloneCoursePage.openCourse();
        cloneCoursePage.setCloneBtn();

        WebElement clonedCourse = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(text(), '(clone)')]")));
        Assert.assertTrue(clonedCourse != null, "Ошибка: Курс не был клонирован!");

        System.out.println("Курс успешно клонирован!");
    }
    /**
     * @Test на отмену клонирования и Assert
     *
     * **/
    @Test
    public void cloneCourseCanselTest(){
        Course randomCourse =randomCourseGenerator.randomCourse();
        cloneCoursePage.enterToCourse(randomCourse);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement course = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[@class='tl-formatted-course-name']")));
        Assert.assertNotNull(course, "Ошибка: Курс не найден!");
        cloneCoursePage.openCourse();
        cloneCoursePage.cancelClone();

        List<WebElement> clonedCourses = driver.findElements(By.xpath("//span[contains(text(), '(clone)')]"));
        Assert.assertFalse(clonedCourses.isEmpty(), "Ошибка: Курс всё равно клонирован!");
    }
    @Test
    public void clonedCourseNameTest() {
        cloneCoursePage.openCourse();
        cloneCoursePage.setCloneBtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clonedCourse = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(text(), '(clone)')]")));

        String clonedCourseName = clonedCourse.getText();
        Assert.assertTrue(clonedCourseName.contains("(clone)"), "Ошибка: Название клонированного курса некорректно!");
    }




}



//3




