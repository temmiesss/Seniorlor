import entity.User;
import fileUtils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Akylai
 *
 */

public class AddUserPageTest extends BaseTest {
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
    @Test(priority = 3)
    public void testCancelAddUser() {
        driver.get("https://zheenbaikyzyakylai.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.navigateToAddUserPage();
        userPage = addUserPage.cancelAddUser();

        boolean isOnUserPage = addUserPage.isPageLoaded();
        Assert.assertTrue(isOnUserPage, "Cancel button did not navigate back to UserPage.");
    }

    @Test(priority = 4)
    public void testSelectUserType() {
        driver.get("https://zheenbaikyzyakylai.talentlms.com/user/create");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.navigateToAddUserPage();

        // Выбор случайного типа пользователя
        addUserPage.selectUserType();

        // Проверка: убедиться, что выбранный тип отображается
        String selectedUserType = addUserPage.getSelectedUserType();
        Assert.assertNotNull(selectedUserType, "No user type was selected.");

        // Проверяем, что выбранное значение принадлежит списку
        List<String> availableUserTypes = addUserPage.getAvailableUserTypes(); // Получаем список доступных типов
        Assert.assertTrue(availableUserTypes.contains(selectedUserType),
                "Selected user type is not in the list of available types.");
    }

    @Test(priority = 5)
    public void testSelectTimeZone() {
        driver.get("https://zheenbaikyzyakylai.talentlms.com/user/create");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.navigateToAddUserPage();

        // Выбор случайной временной зоны
        addUserPage.selectTimeZone();

        String selectedTimeZone = addUserPage.getSelectedTimeZone();
        Assert.assertNotNull(selectedTimeZone, "No time zone was selected.");
        System.out.println("Selected time zone: " + selectedTimeZone);

        // Проверяем, что выбранная временная зона доступна в списке
        List<String> availableTimeZones = addUserPage.getAvailableTimeZones();
        Assert.assertTrue(availableTimeZones.contains(selectedTimeZone),
                "Selected time zone is not in the list of available time zones.");
    }


}
