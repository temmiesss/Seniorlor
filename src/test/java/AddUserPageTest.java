import com.digital_nomads.talent_lms.entity.User;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Akylai
 */

public class AddUserPageTest extends BaseTest {
    User randomUser = randomUserGenerator.randomUser();

    @BeforeTest
    public void setup(){
        driver.get("https://badykeeva.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.navigateToAddUserPage();
    }
    /**
     * Проверяет, что новый пользователь с корректными данными добавляется успешно.
     */
    @Test
    public void testAddNewUser(){
        userPage = addUserPage.addNewUser(randomUser);

        String actualMessage = addUserPage.getAddUserSuccessMessage();
        Assert.assertEquals(actualMessage,"Success! Do you want to add another user?",
                "User was not added successfully");
    }

    /**
     * Проверяет, что система не позволяет добавить пользователя с некорректным email.
     */
    @Test
    public void negativeEmailTest(){
        dashboardPage.addUserBtn.click();
        addUserPage.addNewUserWithInvalidEmail(randomUser,"wrong.ru");
        WebElement isRequired = driver.findElement(By.xpath("//div[@class='span8']/child::*[3]//span[@class='help-block']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual,"This is not a valid 'Email address'");
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
}
