import com.digital_nomads.talent_lms.entity.Course;
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

   @Test
   public void deleteCourse() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteCourse.deleteBtn));
            deleteButton.click();
            System.out.println("Кнопка удаления нажата.");
            WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Confirm')]"))); // Измените XPath на актуальный
            confirmDeleteButton.click();
            System.out.println("Удаление подтверждено.");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//span[contains(text(), '(clone)')]")));
            System.out.println("Курс успешно удалён.");
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Кнопка удаления или окно подтверждения не загрузилось!");
        } catch (Exception e) {
            System.out.println("Ошибка при удалении курса: " + e.getMessage());
        }
    }
    @Test
    public void searchClonedCourseTest() {
        Course randomCourse =randomCourseGenerator.randomCourse();
        cloneCoursePage.enterToCourse(randomCourse);
        cloneCoursePage.setCloneBtn();
        cloneCoursePage.searchCourse("(clone)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchedCourse = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//span[contains(@class, 'tl-formatted-course-name')]")));
        String cloneSearchCourse =  searchedCourse.getText();
        Assert.assertFalse(driver.findElements(By.xpath("//span[contains(text(), '(clone)')]")).isEmpty(),
                "Ошибка: Клонированный курс не найден в поиске!");
    }


}




