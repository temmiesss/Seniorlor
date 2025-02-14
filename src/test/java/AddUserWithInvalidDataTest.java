import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Akylai
 */
public class AddUserWithInvalidDataTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

    // Группа 1: Проверка пустых полей
    @Test(priority = 1)
    public void testEmptyFields() {
        dashboardPage.addUserBtn.click();
        User invalidUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("firstName", "");
        invalidData.put("lastName", "");
        invalidData.put("email", "");
        invalidData.put("username", "");
        addUserWithInvalidData.addInvalidUser(invalidUser, invalidData);

        // Ассерты на ошибки для каждого поля
        String actualMessageFirstName = addUserWithInvalidData.getInvalidFirstNameMessage();
        Assert.assertEquals(actualMessageFirstName, "'First name' is required");

        String actualMessageLastName = addUserWithInvalidData.getInvalidLastNameMessage();
        Assert.assertEquals(actualMessageLastName, "'Last name' is required");

        String actualInvalidEmailMessage = addUserWithInvalidData.getInvalidEmailMessage();
        Assert.assertEquals(actualInvalidEmailMessage, "'Email address' is required");

        String actualInvalidUsernameMessage = addUserWithInvalidData.getInvalidUsernameMessage();
        Assert.assertEquals(actualInvalidUsernameMessage, "'Username' is required");
    }

    // Группа 2: Проверка максимальной длины
    @Test(priority = 2)
    public void testMaxLengthFields() {
        dashboardPage.addUserBtn.click();
        User invalidUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();

        // Заполняем поля максимальной длиной
        invalidData.put("firstName", "Sam".repeat(17));      // пример с firstName
        invalidData.put("lastName", "G".repeat(51));         // пример с lastName
        invalidData.put("email", "email@mail.ru".repeat(12)); // пример с email
        invalidData.put("username", "r".repeat(151));        // пример с username
        invalidData.put("password", "a".repeat(31));                 // пример с паролем

        addUserWithInvalidData.addInvalidUser(invalidUser, invalidData);

        // Ассерты на ошибки для максимальной длины
        String actualMessageFirstName = addUserWithInvalidData.getInvalidFirstNameMessage();
        Assert.assertEquals(actualMessageFirstName, "'First name' cannot exceed 50 characters");

        String actualLastNameMaxLengthMessage = addUserWithInvalidData.getInvalidLastNameMessage();
        Assert.assertEquals(actualLastNameMaxLengthMessage, "'Last name' cannot exceed 50 characters");

        String actualMessageEmail = addUserWithInvalidData.getInvalidEmailMessage();
        Assert.assertEquals(actualMessageEmail, "'Email address' cannot exceed 150 characters");

        String actualUsernameMaxLengthMessage = addUserWithInvalidData.getInvalidUsernameMessage();
        Assert.assertEquals(actualUsernameMaxLengthMessage, "'Username' cannot exceed 150 characters");

        String actualPasswordMaxLengthMessage = addUserWithInvalidData.getInvalidPasswordMessage();
        Assert.assertEquals(actualPasswordMaxLengthMessage, "'Password' cannot exceed 30 characters");
    }
    // Проверка на корректность пароля
    @Test(priority = 3)
    public void invalidPasswordInputMessage() {
        dashboardPage.addUserBtn.click();
        User invalidPasswordUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("password", "mor");
        addUserWithInvalidData.addInvalidUser(invalidPasswordUser, invalidData);

        String actualPasswordMessage = addUserWithInvalidData.getInvalidPasswordMessage();
        Assert.assertEquals(actualPasswordMessage, "'Password' is not strong enough " +
                "(should have at least (1) upper case letter, at least (1) lower case letter, " +
                "at least (1) number, at least (8) characters in length)");
    }

    // Проверка уникальности данных
    @Test(priority = 4)
    public void testUniqueEmail() {
        dashboardPage.selectSection(Section.USERS);
        String existingEmail = userPage.getFirstUserEmail();
        userPage.addUser.click();

        User duplicateEmailUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("email", existingEmail);

        addUserWithInvalidData.addInvalidUser(duplicateEmailUser, invalidData);

        String actualMessage = addUserWithInvalidData.getInvalidEmailMessage();
        Assert.assertEquals(actualMessage, "Someone is already using this email address");
    }

    @Test(priority = 5)
    public void testUniqueUsername() {
        dashboardPage.selectSection(Section.USERS);
        String existingUsername = userPage.getFirstUserUsername();
        userPage.addUser.click();

        User duplicateUsernameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("username", existingUsername);

        addUserWithInvalidData.addInvalidUser(duplicateUsernameUser, invalidData);

        String actualMessage = addUserWithInvalidData.getInvalidUsernameMessage();
        Assert.assertEquals(actualMessage, "This is not a valid 'Username'");
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();  // Очищаем куки перед следующим тестом
    }
}
