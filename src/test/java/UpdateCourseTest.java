import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

/**
 * @author Rano
 * Test вызывается и откывается редактирование
 * рендомно редактирует первый курс и сохраняет его от имени админа
 *
 */
public class UpdateCourseTest extends BaseTest{

    @Test
    public void updateCourseTest() {

        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();

        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);

        Course randomCourse = randomCourseGenerator.randomCourse();

        updateCourse.editCourse(randomCourse);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast toast-success']")));

        Assert.assertFalse(confirmationMessage.getText().contains("Success. Course updated!"), "Курс не обновился!");

        System.out.println("Тест успешно завершен: курс обновлен.");
    }

}

