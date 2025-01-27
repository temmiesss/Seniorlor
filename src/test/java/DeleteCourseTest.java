import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.DeleteCourse;
/**
 * @author Rano
 * **/

public class DeleteCourseTest extends BaseTest {
    @Test
    public void deleteSelectedCourse() throws InterruptedException {

        DeleteCourse deleteCourse = new DeleteCourse(driver);

        driver.get("https://ranoprojectgroup.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password")).switchToLegacyInterface();

        boolean isCourseVisibleBefore = deleteCourse.isCourseVisible();
        Assert.assertTrue(isCourseVisibleBefore, "course is avaliable!");

        deleteCourse.openCourse();
        deleteCourse.selectCourse();
        deleteCourse.openBurgerMenu();
        deleteCourse.deleteCourse();
        deleteCourse.cancelDelete();

        boolean isCourseInvisibleAfterDelete = deleteCourse.isCourseInvisible();
        Assert.assertTrue(isCourseInvisibleAfterDelete, "Курс должен быть удален и исчезнуть!");

        Thread.sleep(2000);
        deleteCourse.cancelDelete();


//        boolean isCourseVisibleAfterCancel = deleteCourse.isCourseVisibleAfterCancel();
//        Assert.assertTrue(isCourseVisibleAfterCancel, "Курс должен быть доступен после отмены удаления!");

    }
}
