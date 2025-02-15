import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Akylai
 */

public class AddUserPageTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        driver.navigate().refresh();
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        randomUser = randomUserGenerator.randomUser();
    }

    /**
     * Проверяет, что новый пользователь с корректными данными добавляется успешно.
     */
    @Test(priority = 1)
    public void testAddNewUser() {
        dashboardPage.navigateToAddUserPage();
        userPage = addUserPage.addNewUser(randomUser);

        String actualMessage = addUserPage.getAddUserSuccessMessage();
        Assert.assertEquals(actualMessage, "Success! Do you want to add another user?",
                "User was not added successfully");
    }

    /**
     * Проверяем, что после отмены пользователь находится на странице UserPage
     */
    @Test(priority = 2)
    public void testCancelAddUser() {
        dashboardPage.navigateToAddUserPage();
        addUserPage.cancelAddUser();
        Assert.assertNotNull(userPage, "Navigating to the UserPage failed");

        String currentUrl = driver.getCurrentUrl();
        String expectedUrlPart = "user/index";
        Assert.assertTrue(currentUrl.contains(expectedUrlPart), "Current URL does not contain this part");
    }

    // Проверка пустых полей
    @Test(priority = 3)
    public void testEmptyFields() {
        dashboardPage.addUserBtn.click();
        Assert.assertTrue(addUserPage.isPageLoaded(), "AddUserPage did not load correctly");

        User invalidUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("firstName", "");
        invalidData.put("lastName", "");
        invalidData.put("email", "");
        invalidData.put("username", "");
        addUserPage.addInvalidUser(invalidUser, invalidData);

        // Ассерты на ошибки для каждого поля
        String actualMessageFirstName = addUserPage.getInvalidFirstNameMessage();
        Assert.assertEquals(actualMessageFirstName, "'First name' is required");

        String actualMessageLastName = addUserPage.getInvalidLastNameMessage();
        Assert.assertEquals(actualMessageLastName, "'Last name' is required");

        String actualInvalidEmailMessage = addUserPage.getInvalidEmailMessage();
        Assert.assertEquals(actualInvalidEmailMessage, "'Email address' is required");

        String actualInvalidUsernameMessage = addUserPage.getInvalidUsernameMessage();
        Assert.assertEquals(actualInvalidUsernameMessage, "'Username' is required");
    }

    // Проверка максимальной длины
    @Test(priority = 4)
    public void testMaxLengthFields() {
        dashboardPage.addUserBtn.click();
        User invalidUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();

        // Заполняем поля максимальной длиной
        invalidData.put("firstName", "Sam".repeat(17));
        invalidData.put("lastName", "G".repeat(51));
        invalidData.put("email", "email@mail.ru".repeat(12));
        invalidData.put("username", "r".repeat(151));
        invalidData.put("password", "a".repeat(31));

        addUserPage.addInvalidUser(invalidUser, invalidData);

        // Ассерты на ошибки для максимальной длины
        String actualMessageFirstName = addUserPage.getInvalidFirstNameMessage();
        Assert.assertEquals(actualMessageFirstName, "'First name' cannot exceed 50 characters");

        String actualLastNameMaxLengthMessage = addUserPage.getInvalidLastNameMessage();
        Assert.assertEquals(actualLastNameMaxLengthMessage, "'Last name' cannot exceed 50 characters");

        String actualMessageEmail = addUserPage.getInvalidEmailMessage();
        Assert.assertEquals(actualMessageEmail, "'Email address' cannot exceed 150 characters");

        String actualUsernameMaxLengthMessage = addUserPage.getInvalidUsernameMessage();
        Assert.assertEquals(actualUsernameMaxLengthMessage, "'Username' cannot exceed 150 characters");

        String actualPasswordMaxLengthMessage = addUserPage.getInvalidPasswordMessage();
        Assert.assertEquals(actualPasswordMaxLengthMessage, "'Password' cannot exceed 30 characters");
    }
    // Проверка на корректность пароля
    @Test(priority = 5)
    public void invalidPasswordInputMessage() {
        dashboardPage.addUserBtn.click();
        User invalidPasswordUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("password", "mor");
        addUserPage.addInvalidUser(invalidPasswordUser, invalidData);

        String actualPasswordMessage = addUserPage.getInvalidPasswordMessage();
        Assert.assertEquals(actualPasswordMessage, "'Password' is not strong enough " +
                "(should have at least (1) upper case letter, at least (1) lower case letter, " +
                "at least (1) number, at least (8) characters in length)");
    }

    // Проверка уникальности данных
    @Test(priority = 6)
    public void testUniqueEmail() {
        dashboardPage.selectSection(Section.USERS);
        String existingEmail = userPage.getFirstUserEmail();
        userPage.addUser.click();

        User duplicateEmailUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("email", existingEmail);

        addUserPage.addInvalidUser(duplicateEmailUser, invalidData);

        String actualMessage = addUserPage.getInvalidEmailMessage();
        Assert.assertEquals(actualMessage, "Someone is already using this email address");
    }

    @Test(priority = 7)
    public void testUniqueUsername() {
        dashboardPage.selectSection(Section.USERS);
        String existingUsername = userPage.getFirstUserUsername();
        userPage.addUser.click();

        User duplicateUsernameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("username", existingUsername);

        addUserPage.addInvalidUser(duplicateUsernameUser, invalidData);

        String actualMessage = addUserPage.getInvalidUsernameMessage();
        Assert.assertEquals(actualMessage, "Someone is already using this username");
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();  // Очищаем куки перед следующим тестом
    }
}