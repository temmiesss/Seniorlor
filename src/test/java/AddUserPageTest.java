import entity.User;
import fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * @author Akylai
 *
 */

public class AddUserPageTest extends BaseTest {
    User randomUser = randomUserGenerator.randomUser();

    @BeforeTest
    public void setup(){
        driver.get("https://charlieblack.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }
    /**
     * Проверяет, что новый пользователь с корректными данными добавляется успешно.
     */
    @Test
    public void testAddNewUser(){
        dashboardPage.navigateToAddUserPage();
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
        dashboardPage.navigateToAddUserPage();
        userPage = addUserPage.cancelAddUser();

        boolean isOnUserPage = addUserPage.isPageLoaded();
        Assert.assertTrue(isOnUserPage, "Cancel button did not navigate back to UserPage.");
    }

    @Test
    public void testSelectUserType() {
        dashboardPage.navigateToAddUserPage();

        // Проверка: убедиться, что выбранный тип отображается
        String selectedUserType = addUserPage.selectRandomUserType();
        Assert.assertNotNull(selectedUserType, "No user type was selected.");

        // Проверяем, что выбранное значение принадлежит списку
        List<String> availableUserTypes = addUserPage.getAvailableUserTypes(); // Получаем список доступных типов
        Assert.assertTrue(availableUserTypes.contains(selectedUserType),
                "Selected user type is not in the list of available types.");
    }

    @Test
    public void testSelectTimeZone() {
        dashboardPage.navigateToAddUserPage();

        String selectedTimeZone = addUserPage.selectRandomTimeZone();
        Assert.assertNotNull(selectedTimeZone, "No time zone was selected.");

        // Проверяем, что выбранная временная зона доступна в списке
        List<String> availableTimeZones = addUserPage.getAvailableTimeZones();
        Assert.assertTrue(availableTimeZones.contains(selectedTimeZone),
                "Selected time zone is not in the list of available time zones.");
    }




}
