import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Akylai
 */

public class AddUserPageTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.navigateToAddUserPage();
        randomUser = randomUserGenerator.randomUser();
    }

    /**
     * Проверяет, что новый пользователь с корректными данными добавляется успешно.
     */
    @Test
    public void testAddNewUser() {
        userPage = addUserPage.addNewUser(randomUser);

        String actualMessage = addUserPage.getAddUserSuccessMessage();
        Assert.assertEquals(actualMessage, "Success! Do you want to add another user?",
                "User was not added successfully");
    }

    /**
     * Проверяем, что после отмены пользователь находится на странице UserPage
     */
    @Test
    public void testCancelAddUser() {
        addUserPage.cancelAddUser();
        Assert.assertNotNull(userPage, "Navigating to the UserPage failed");

        String currentUrl = driver.getCurrentUrl();
        String expectedUrlPart = "user/index";
        Assert.assertTrue(currentUrl.contains(expectedUrlPart), "Current URL does not contain this part");
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();  // Очищаем куки перед следующим тестом
    }

}
