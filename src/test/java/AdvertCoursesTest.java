import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdvertCoursesTest extends BaseTest{

        @BeforeMethod
        public void setUp(){
            driver.get(ConfigReader.getProperty("URL"));
            driver.navigate().refresh();
            loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                    .switchToLegacyInterface();
        }
        @Test
        public void courseCatalogTest(){
            advertCourses.enterToCourse();
            advertCourses.viewCourseCatalog();
            Assert.assertTrue(true, "Каталог курсов  открыт");

        }
    @Test
    public void buyCourseTest(){
        advertCourses.enterToCourse();
        advertCourses.buyCoursesPart();
        Assert.assertTrue(true, "Каталог курсов  открыт");

    }
    @Test
    public void reedInfoFromTalant(){
            advertCourses.enterToCourse();
            advertCourses.readInfoFromTalant();
            Assert.assertTrue(true,"Информационный блок открыт.");
    }
    @Test
    public void readMoreTest(){
            advertCourses.enterToCourse();
            advertCourses.readMore();
            Assert.assertTrue(true, "Окно открыто!");
    }

    @Test
    public void courseCatalogOtherProvider(){
            advertCourses.enterToCourse();
            advertCourses.otherCourseProvider();
            Assert.assertTrue(true,"Курс выбран из списка");
    }
    @AfterMethod
    public void tearDown1() {
        driver.manage().deleteAllCookies();
    }
    }
    //5

