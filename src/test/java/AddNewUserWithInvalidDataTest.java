import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddNewUserWithInvalidDataTest extends BaseTest {

    User randomUser = randomUserGenerator.randomUser();

    @BeforeTest
    public void setup() {
        driver.get("https://charlieblack.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

    @Test
    public void invalidEmailInputTest() {
        dashboardPage.addUserBtn.click();
        User invalidEmailUser = randomUserGenerator.randomUser();
        invalidEmailUser.setEmail("invalidEmail");
        addUserPage.addNewUser(invalidEmailUser);

        String actualInvalidEmailMessage = addUserWithInvalidData.getInvalidEmailMessage();
        Assert.assertEquals(actualInvalidEmailMessage, "This is not a valid 'Email address'");
    }

    @Test
    public void firstNameIsRequiredMessageTest() {
        dashboardPage.addUserBtn.click();
        User userWithEmptyFirstName = randomUserGenerator.randomUser();
        userWithEmptyFirstName.setFirstName("");
        addUserPage.addNewUser(userWithEmptyFirstName);

        String actualNameIsRequiredMessage = addUserWithInvalidData.getFirstNameIsRequiredMessage();
        Assert.assertEquals(actualNameIsRequiredMessage, "'First name' is required");
    }

    @Test
    public void lastNameMaxLengthMessageTest(){
        dashboardPage.addUserBtn.click();
        User lastNameMaxLengthUser = randomUserGenerator.randomUser();
        lastNameMaxLengthUser.setLastName("a".repeat(51));
        addUserPage.addNewUser(lastNameMaxLengthUser);

        String actualLastNameMaxLengthMessage = addUserWithInvalidData.getLastNameMaxLengthMessage();
        Assert.assertEquals(actualLastNameMaxLengthMessage, "'Last name' cannot exceed 50 characters");
    }

    @Test
    public void invalidPasswordInputTest() {
        dashboardPage.addUserBtn.click();
        User invalidPasswordUser = randomUserGenerator.randomUser();
        invalidPasswordUser.setPassword("invpsw");
        addUserPage.addNewUser(invalidPasswordUser);

        String actualInvalidPasswordMessage = addUserWithInvalidData.getPasswordValidationMessage();
        Assert.assertEquals(actualInvalidPasswordMessage, "'Password' is not strong enough (should " +
                "have at least (1) upper case letter, at least (1) lower case letter, at least (1) number, at " +
                "least (8) characters in length)");
    }

    @Test
    public void uniqueEmailTest(){
        dashboardPage.addUserBtn.click();
        User uniqueEmailUser = randomUserGenerator.randomUser();
        uniqueEmailUser.setEmail("jackie001@mail.ru");
        addUserPage.addNewUser(uniqueEmailUser);

        String actualUniqueEmailMessage = addUserWithInvalidData.getUniqueEmailMessage();
        Assert.assertEquals(actualUniqueEmailMessage, "Someone is already using this email address");
    }



}
