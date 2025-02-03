import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddUserWithInvalidDataTest extends BaseTest{

    @BeforeTest
    public void setup() {
        driver.get("https://badykeeva.talentlms.com/index");
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
}
