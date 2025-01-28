import entity.User;
import fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Akylai
 *
 */

public class AddUserTest extends BaseTest {
    User randomUser = randomUserGenerator.randomUser();

    /**
     * Проверяет, что новый пользователь с корректными данными добавляется успешно.
     */
    @Test(priority = 1)
    public void testAddNewUser(){
        driver.get("https://zheenbaikyzyakylai.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.navigateToAddUserPage();
        userPage = addUserPage.addNewUser(randomUser);

        boolean isUserAdded = userPage.isUserPresent(randomUser.getUsername());
        Assert.assertTrue(isUserAdded, "User was not added successfully");
    }

    /**
     * Проверяет, что система не позволяет добавить пользователя с некорректным email.
     */
    @Test(priority = 2)
    public void negativeEmailTest(){
        driver.get("https://zheenbaikyzyakylai.talentlms.com/user/create");
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
        driver.get("https://zheenbaikyzyakylai.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.navigateToAddUserPage();
        userPage = addUserPage.cancelAddUser();

        boolean isOnUserPage = addUserPage.isPageLoaded();
        Assert.assertTrue(isOnUserPage, "Cancel button did not navigate back to UserPage.");
    }
}
