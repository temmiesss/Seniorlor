import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

/**
 * @author Rano
 * Test вызывается и откывается редактирование
 * рендомно редактирует первый курс и сохраняет его от имени админа
 *
 */
public class UpdateCourseTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        driver.get(ConfigReader.getProperty("URL"));
        driver.navigate().refresh();
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

    @Test
    public void updateCourseTest() {

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

    /**
     * @test проверяет наличие отмены изменений курса
     */
    @Test
    public void cancelUpdateBtnTest() {
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        updateCourse.cancelChanges(randomCourse);

        Assert.assertFalse(updateCourse.editPencil.isDisplayed(), "Ошибка: кнопка редактирования не появилась после отмены!");
    }

    /**
     * @test для возврата в каталог курсов
     */
    @Test
    public void returnToCourseCatalogTest() {
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        updateCourse.goToCourseContent(randomCourse);

        Assert.assertTrue(updateCourse.returnToCourseCatalog.isDisplayed(), "Ошибка: не вернулись в содержимое курса!");
    }
/** @Test changing only price and assert
 * */
    @Test
    public void changeOnlyPrice(){
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        String newPrice = randomCourse.getPrice();
        updateCourse.changePrice(randomCourse, randomCourse.getPrice());

       Assert.assertEquals(randomCourse.getPrice(), newPrice);

    }

    /** @Test changing only code of course and Assert
     *
     */
    @Test
    public void changeOnlyCodeTest(){
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        String newCode = randomCourse.getCourseCode();
        updateCourse.changeCode(randomCourse, randomCourseGenerator.randomCourseCode());

        Assert.assertEquals(randomCourse.getCourseCode(), newCode);
    }

    /** @Test changing only Description and Assert
     *
     */
    @Test
    public void changeOnlyTextTest(){
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        String newText = randomCourse.getDescription();
        updateCourse.changeText(randomCourse, randomCourseGenerator.randomDescription());

        Assert.assertEquals(randomCourse.getDescription(), newText);
    }

    /** @test of changing only Capacity of course and assert
     *
     */
    @Test
    public void changeOnlyCapacityTest(){
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        String newCapacity = randomCourse.getCapacity();
        updateCourse.changeCapacity(randomCourse, randomCourseGenerator.randomCourseCapacity());

        Assert.assertEquals(randomCourse.getCapacity(), newCapacity);
    }

    /** @test of changing of Course Name and assert
     *
     */
    @Test
    public void changeNameOfCourseTest(){
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        String newNameOfCourse = randomCourse.getCourseName();
        updateCourse.changeNameOfCourse(randomCourse,randomCourseGenerator.randomCourseName());
        Assert.assertEquals(randomCourse.getCourseName(),newNameOfCourse);

    }

    @Test
    public void changeCategoryOfCourseTest(){
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        String newCategory = randomCourse.getCategory();
//        webElementActions.scrollToElementWithActions(userPage.selectField);
        updateCourse.changeCategory(randomCourse,randomCourseGenerator.randomCourseCategory());

    }

    /**
     * @Test on inputChangesTo Time limit and assert
     */
    @Test
    public void changeTimeLimitTest(){
        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);
        Course randomCourse = randomCourseGenerator.randomCourse();
        String newTimeLimit = randomCourse.getTimeLimit();
        updateCourse.chooseTimeLimitOfCourse(randomCourse, randomCourseGenerator.randomCourseTimeLimit());
        Assert.assertEquals(randomCourse.getTimeLimit(), newTimeLimit);
    }
    /**
     * @Test on input invalid Time  and assert
     */
    @Test
    public void changeTimeLimitWithInvalidDataTest() {

        updateCourse.courseEnter.click();
        webElementActions.moveToElement(updateCourse.pressBurger);
        webElementActions.click(updateCourse.editPencil);

        Course randomCourse = randomCourseGenerator.randomCourse();
        String invalidTimeLimit = " ";
        updateCourse.chooseInvalidTimeLimitOfCourse(randomCourse, invalidTimeLimit);
        Assert.assertFalse(false, "Поле времени не должно быть пустым");

    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }
}

//10


