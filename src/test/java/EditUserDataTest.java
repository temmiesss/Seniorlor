import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @authot Akylai
 */

public class EditUserDataTest extends BaseTest {
    private String userEmail = "moderntalking@gmail.com";

    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.USERS);
    }

    @Test
    public void testOpenUserProfile() {
        String username = "mary";
        editUserDataPage.openUserProfile(username);

        // Проверяем, что после клика заголовок страницы содержит имя пользователя
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(username), "User profile page did not open correctly");
    }

    @Test
    public void testOpenProgressTab(){
        String username = "mary";
        editUserDataPage.openUserProfile(username);
        editUserDataPage.clickToProgressTab();
        System.out.println(editUserDataPage.progressUrl);
        Assert.assertTrue(editUserDataPage.progressUrl.contains("/reports/userinfo/id"));
    }

    @Test
    public void testOpenInfographicTab(){
        String username = "mary";
        editUserDataPage.openUserProfile(username);
        editUserDataPage.clickToInfographicTab();
        System.out.println(editUserDataPage.infographicUrl);
        Assert.assertTrue(editUserDataPage.infographicUrl.contains("/reports/userinfographics/id"));
    }

    @Test
    public void testRedirectToUserCoursesPage(){
        String username = "mary";
        editUserDataPage.openUserProfile(username);
        userCoursesPage = editUserDataPage.clickCourses();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("/user/courses/id"), "Expected URL does not contain this part");
    }

    @Test
    public void testRedirectToUserGroupsPage(){
        String username = "mary";
        editUserDataPage.openUserProfile(username);
        userGroupsPage = editUserDataPage.clickGroups();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("/user/groups/id"),"Expected URL does not contain this part" );
    }

    @Test
    public void testRedirectToUserFilesPage(){
        String username = "mary";
        editUserDataPage.openUserProfile(username);
        userFilesPage = editUserDataPage.clickFiles();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("/user/files/id"),"Expected URL does not contain this part" );
    }

    /**
     * Проверяем, что имя пользователя успешно обновляется.
     */
    @Test
    public void testEditUserFirstName() {
        userPage.editUser(userEmail);
        user.setFirstName("Kate");
        editUserDataPage.editUserData("firstname", user.getFirstName());

        WebElement updatedFirstName = editUserDataPage.getUpdatedFirstNameField();
        Assert.assertEquals(updatedFirstName.getAttribute("value"), user.getFirstName(),
                "User FirstName does not changed");
    }

    /**
     * Проверяем, что фамилия пользователя успешно обновляется.
     */
    @Test
    public void testEditUserLastName() {
        userPage.editUser(userEmail);
        user.setLastName("Osborne");
        editUserDataPage.editUserData("lastname", user.getLastName());

        WebElement updatedLastName = editUserDataPage.getUpdatedLastNameField();
        Assert.assertEquals(updatedLastName.getAttribute("value"), user.getLastName(),
                "User LastName does not changed");
    }

    /**
     * Проверка успешного изменения вида пользователя
     */
    @Test
    public void testEditUserType() {
        userPage.editUser(userEmail);
        String expectedUserType = "Trainer-Type";
        editUserDataPage.editUserData("usertype", expectedUserType);

        WebElement updatedUserTypeField = editUserDataPage.getUpdatedUserTypeField();
        String actualUserType = updatedUserTypeField.getText();
        Assert.assertEquals(actualUserType, expectedUserType, "User type does not change correctly");
    }

    /**
     * Проверка успешного изменения временной зоны
     */
    @Test
    public void testEditTimeZone() {
        userPage.editUser(userEmail);
        String expectedTimeZone = "(GMT -01:00) Cape Verde";
        editUserDataPage.editUserData("timezone", expectedTimeZone);

        WebElement updatedTimeZoneField = editUserDataPage.getUpdateTimeZoneField();
        String actualTimeZone = updatedTimeZoneField.getText();
        Assert.assertEquals(actualTimeZone, expectedTimeZone, "Time Zone does not change correctly");
    }

    /**
     * Проверка успешного изменения языка
     */
    @Test
    public void testEditLanguage() {
        userPage.editUser(userEmail);
        String expectedLanguage = "Pусский (Russian)";
        editUserDataPage.editUserData("language", expectedLanguage);

        WebElement updatedLanguageField = editUserDataPage.getUpdatedLanguageField();
        String actualLanguage = updatedLanguageField.getText();
        Assert.assertEquals(actualLanguage, expectedLanguage, "Language does not change correctly");
    }


    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.close();
        driver.quit();
    }
}
