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


    @Test
    public void invalidEmailInputTest() {
        dashboardPage.addUserBtn.click();
        User invalidEmailUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("email", "invalidEmail");
        addUserWithInvalidData.addInvalidUser(invalidEmailUser, invalidData);

        String actualInvalidEmailMessage = addUserWithInvalidData.getInvalidEmailMessage();
        Assert.assertEquals(actualInvalidEmailMessage, "This is not a valid 'Email address'");
    }

    @Test
    public void maxLengthEmailInputTest() {
        dashboardPage.addUserBtn.click();
        User invalidEmailUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("email", "email@mail.ru".repeat(12));
        addUserWithInvalidData.addInvalidUser(invalidEmailUser, invalidData);

        String actualInvalidEmailMessage = addUserWithInvalidData.getInvalidEmailMessage();
        Assert.assertEquals(actualInvalidEmailMessage, "'Email address' cannot exceed 150 characters");
    }

    @Test
    public void emptyEmailInputTest() {
        dashboardPage.addUserBtn.click();
        User invalidEmailUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("email", "");
        addUserWithInvalidData.addInvalidUser(invalidEmailUser, invalidData);

        String actualInvalidEmailMessage = addUserWithInvalidData.getInvalidEmailMessage();
        Assert.assertEquals(actualInvalidEmailMessage, "'Email address' is required");
    }

    @Test
    public void uniqueEmailTest() {
        dashboardPage.selectSection(Section.USERS);
        String existingEmail = userPage.getFirstUserEmail();
        userPage.addUser.click();

        User duplicateEmailUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("email", existingEmail);
        addUserWithInvalidData.addInvalidUser(duplicateEmailUser, invalidData);

        String actualUniqueEmailMessage = addUserWithInvalidData.getInvalidEmailMessage();
        Assert.assertEquals(actualUniqueEmailMessage, "Someone is already using this email address");
    }

    @Test
    public void invalidFirstNameMessageTest() {
        dashboardPage.addUserBtn.click();
        User invalidFirstNameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("firstName", "");
        addUserWithInvalidData.addInvalidUser(invalidFirstNameUser, invalidData);

        String actualInvalidFirstNameMessage = addUserWithInvalidData.getInvalidFirstNameMessage();
        Assert.assertEquals(actualInvalidFirstNameMessage, "'First name' is required");
    }

    @Test
    public void firstNameMaxLengthMessageTest() {
        dashboardPage.addUserBtn.click();
        User invalidFirstNameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("firstName", "Sam".repeat(17));
        addUserWithInvalidData.addInvalidUser(invalidFirstNameUser, invalidData);

        String actualFirstNameMaxLengthMessage = addUserWithInvalidData.getInvalidFirstNameMessage();
        Assert.assertEquals(actualFirstNameMaxLengthMessage, "'First name' cannot exceed 50 characters");
    }

    @Test
    public void invalidLastNameMessageTest() {
        dashboardPage.addUserBtn.click();
        User invalidLastNameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("lastName", "");
        addUserWithInvalidData.addInvalidUser(invalidLastNameUser, invalidData);

        String actualInvalidLastNameMessage = addUserWithInvalidData.getInvalidLastNameMessage();
        Assert.assertEquals(actualInvalidLastNameMessage, "'Last name' is required");
    }

    @Test
    public void lastNameMaxLengthMessageTest() {
        dashboardPage.addUserBtn.click();
        User invalidLastNameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("lastName", "G".repeat(51));
        addUserWithInvalidData.addInvalidUser(invalidLastNameUser, invalidData);

        String actualLastNameMaxLengthMessage = addUserWithInvalidData.getInvalidLastNameMessage();
        Assert.assertEquals(actualLastNameMaxLengthMessage, "'Last name' cannot exceed 50 characters");
    }

    @Test
    public void invalidUsernameMessageTest() {
        dashboardPage.addUserBtn.click();
        User invalidUsernameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("username", "");
        addUserWithInvalidData.addInvalidUser(invalidUsernameUser, invalidData);

        String actualInvalidUsernameMessage = addUserWithInvalidData.getInvalidUsernameMessage();
        Assert.assertEquals(actualInvalidUsernameMessage, "'Username' is required");
    }

    @Test
    public void usernameMaxLengthMessageTest() {
        dashboardPage.addUserBtn.click();
        User invalidUsernameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("username", "r".repeat(151));
        addUserWithInvalidData.addInvalidUser(invalidUsernameUser, invalidData);

        String actualUsernameMaxLengthMessage = addUserWithInvalidData.getInvalidUsernameMessage();
        Assert.assertEquals(actualUsernameMaxLengthMessage, "'Username' cannot exceed 150 characters");
    }

    @Test
    public void uniqueUsernameTest() {
        dashboardPage.selectSection(Section.USERS);
        String existingUsername = userPage.getFirstUserUsername();
        userPage.addUser.click();

        User duplicateUsernameUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("username", existingUsername);
        addUserWithInvalidData.addInvalidUser(duplicateUsernameUser, invalidData);

        String actualUniqueUsernameMessage = addUserWithInvalidData.getInvalidUsernameMessage();
        Assert.assertEquals(actualUniqueUsernameMessage, "Someone is already using this username");
    }

    @Test
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

    @Test
    public void maxLengthPasswordTest() {
        dashboardPage.addUserBtn.click();
        User invalidPasswordUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("password", "a".repeat(31));
        addUserWithInvalidData.addInvalidUser(invalidPasswordUser, invalidData);

        String actualPasswordMaxLengthMessage = addUserWithInvalidData.getInvalidPasswordMessage();
        Assert.assertEquals(actualPasswordMaxLengthMessage, "'Password' cannot exceed 30 characters");
    }

    @Test
    public void maxLengthBioInputMessage() {
        dashboardPage.addUserBtn.click();
        User invalidBioUser = randomUserGenerator.randomUser();
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("bio", "This is a simple sentence designed to contain exactly one hundred two characters, including spaces. ".repeat(8) + ".");
        addUserWithInvalidData.addInvalidUser(invalidBioUser, invalidData);

        String actualBioMessage = addUserWithInvalidData.getInvalidBioMessage();
        Assert.assertEquals(actualBioMessage, "'Bio' cannot exceed 800 characters");
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
