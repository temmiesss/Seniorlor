import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.utils.randomEntityUtils.RandomCourseGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.testng.AssertJUnit.fail;
import com.digital_nomads.talent_lms.page.course.DeleteCourse;

/**
 * @author Rano
 *
 * **/
public class DeleteCourseTest extends BaseTest {
    @BeforeMethod
            public void setUp(){
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();
    }

    /**
     * @Test удаляет рандомный курс и выводит на консоль, что курс успешно удален
     **/
    @Test
    public void deleteSelectedCourse() throws InterruptedException {

        Course randomCourse = randomCourseGenerator.randomCourse();
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
    /**
     * @Test на отмену удаления и assert
     **/
    @Test
    public void deleteCancelCourseTest() throws InterruptedException {

        Course randomCourse = randomCourseGenerator.randomCourse();
        deleteCourse.enterToCourse(randomCourse);
        deleteCourse.openCourse();
        deleteCourse.cancelDelete();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text() = '" + randomCourse.getCourseName() + "']")));
            Assert.assertTrue(true);
        } catch (TimeoutException e) {
            fail("Курс не был удален");
        }
    }
    /**
     * @Test Удаление первого курса в списке
     **/
    @Test
    public void deleteFirstCourse() {

        Course randomCourse = randomCourseGenerator.randomCourse();
        deleteCourse.enterToCourse(randomCourse);
        deleteCourse.openCourse();
        deleteCourse.cancelDelete();
            try {
                if (driver == null) {
                    throw new NullPointerException("Ошибка: `driver` = null перед удалением!");
                }
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                System.out.println("Ждём появления чекбокса...");
                WebElement firstCourseCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id='tl-courses-grid']/tbody/tr[1]/td[1]/input")));
                firstCourseCheckbox.click();
                System.out.println(" Чекбокс первого курса выбран");

                WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Delete']")));
                deleteButton.click();
                System.out.println(" Нажата кнопка удаления");

                WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Confirm']")));
                confirmButton.click();
                System.out.println("Удаление подтверждено");

                boolean isDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//*[@id='tl-courses-grid']/tbody/tr[1]")));

                Assert.assertTrue(isDeleted, "Ошибка: первый курс не был удалён!");
                System.out.println("Первый курс успешно удалён!");

            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка при удалении первого курса: " + e.getMessage());
            }
        }
    /**
     * @Test на удаление последнего курса
     *
     * **/
    @Test
    public void deleteLastCourse() {

        Course randomCourse = randomCourseGenerator.randomCourse();
        deleteCourse.enterToCourse(randomCourse);
        deleteCourse.openCourse();
        deleteCourse.cancelDelete();

        try {
            if (driver == null) {
                throw new NullPointerException("Ошибка: `driver` = null перед удалением!");
            }
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            System.out.println("Ждём появления чекбокса...");
            WebElement lastCourseCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id='tl-courses-grid']/tbody/tr[7]/td[2]/a/span")));
            lastCourseCheckbox.click();
            System.out.println(" Чекбокс последнего курса выбран");
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Delete']")));
            deleteButton.click();
            System.out.println(" Нажата кнопка удаления");

            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Confirm']")));
            confirmButton.click();
            System.out.println("Удаление подтверждено");

            boolean isDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//*[@id='tl-courses-grid']/tbody/tr[7]/td[2]/a")));

            Assert.assertTrue(isDeleted, "Ошибка: последний курс не был удалён!");
            System.out.println("Последний курс успешно удалён!");

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при удалении последнего курса: " + e.getMessage());
        }
    }

    /**
     * @Test на улаление определенный курс
     *
     */
    @Test
    public void deleteSpecificCourse() {

        deleteCourse.openCourse();
        String courseName = "Yundt, Bernier and BechtelarFeeney, Jaskolski and RogahnGrady-JerdeReinger LLC";
        deleteCourse.openCourseByName(courseName);
        deleteCourse.deleteCourse();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//span[text()='" + courseName + "']") ));

        Assert.assertTrue(isDeleted, "Ошибка: курс '" + courseName + "' не был удалён!");
    }
    /**
     * @Test на удаление несуществующего курса
     * **/

    @Test
    public void deleteNonExistingCourse() {

        deleteCourse.openCourse();

        String nonExistingCourse = "Accounting - basic level";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isPresent = !wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text()='" + nonExistingCourse + "']")));
        Assert.assertFalse(isPresent, "Ошибка: найден несуществующий курс!");
    }

    @Test
    public void deleteMultipleCourses() {

        Course randomCourse = new Course();
        deleteCourse.openCourse();
        System.out.println("Проверяем количество курсов перед удалением...");
        int coursesBefore = driver.findElements(By.xpath("//*[@id='tl-courses-grid']/tbody/tr")).size();
        System.out.println("Курсов до удаления: " + coursesBefore);
        Assert.assertTrue(coursesBefore > 1, "Ошибка: Недостаточно курсов для удаления!");
        deleteCourse.enterToCourse(randomCourse);
//        deleteCourse.openFirstCourse();
        deleteCourse.deleteCourse();
        deleteCourse.openFirstCourse();
        deleteCourse.deleteCourse();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                    By.xpath("//*[@id='tl-courses-grid']/tbody/tr"), coursesBefore));
            System.out.println(" Курсы успешно удалены!");
        } catch (TimeoutException e) {
            System.out.println("Ошибка: курсы не удалены!");
            fail("Ошибка: курсы всё ещё на месте!");
        }

        int coursesAfter = driver.findElements(By.xpath("//*[@id='tl-courses-grid']/tbody/tr")).size();
        System.out.println(" Курсов после удаления: " + coursesAfter);

        Assert.assertTrue(coursesAfter < coursesBefore, "Ошибка: курсы не были удалены!");
    }

    @Test
    public void deleteCourseAndCheckListUpdate() {

        deleteCourse.openCourse();

        List<WebElement> courses = driver.findElements(By.xpath("//table[@id='tl-courses-grid']//tr//td[1]//a"));

        Assert.assertFalse(courses.isEmpty(), "Ошибка: нет доступных курсов для удаления!");

        // Выбираем случайный курс
        WebElement randomCourseElement = courses.get(new Random().nextInt(courses.size()));
        String courseName = randomCourseElement.getText(); // Запоминаем имя курса

        // Входим в курс
        randomCourseElement.click();

        // Подсчитываем количество кнопок удаления перед удалением
        int beforeDelete = driver.findElements(By.xpath("//table[@id='tl-courses-grid']//td[5]//i[@title='Delete']")).size();

        // Удаляем курс
        deleteCourse.deleteCourse();
        deleteCourse.confirmDelete();

        // Перезагружаем страницу для обновления списка
        driver.navigate().refresh();

        // Подсчитываем количество курсов после удаления
        List<WebElement> updatedCourses = driver.findElements(By.xpath("//table[@id='tl-courses-grid']//tr//td[1]//a"));

        // Проверяем, что удалённый курс больше не отображается
        boolean courseStillExists = updatedCourses.stream().anyMatch(e -> e.getText().equals(courseName));

        Assert.assertFalse(courseStillExists, "Ошибка: курс не был удалён!");
    }


    //public boolean isDisplayed(WebElement element){
    //    return element.isDisplayed();
    //}

}




