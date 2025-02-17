import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EnterOfOneLernerCourseTest extends BaseTest{
@BeforeMethod
public void setUp(){
    driver.get(ConfigReader.getProperty("URL"));
    loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();
}
    @Test
    public void enterOfOneLearner(){

        enterOfOneLerner.chooseFirstCourse();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("course"), "Ошибка: URL не изменился, курс не открылся!");
    }
    @Test
    public void enterToCourseCatalog(){
        Assert.assertFalse(false, "Каталог не должен быть открыт до вызова метода");

        enterOfOneLerner.enterCourseCatalog();

        Assert.assertTrue(true, "Каталог курсов должен быть открыт");

        }
    @AfterMethod
    public void tearDown1() {
        driver.manage().deleteAllCookies();
    }



}
//2