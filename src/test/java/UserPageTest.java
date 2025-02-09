import com.digital_nomads.talent_lms.entity.ActionResult;
import com.digital_nomads.talent_lms.enums.Action;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * @author Akylai
 */

public class UserPageTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.USERS);
    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testNavigateToAddUserPageFromUserPage() {
        addUserPage = userPage.navigateToAddUserPage();
        Assert.assertTrue(addUserPage.isPageLoaded(), "AddUserPage did not load correctly");
    }

    @Test
    public void testDeleteUser() {
        String userEmail = "dawson@mai.ru"; // нужно поменять почту
        userPage.deleteUser(userEmail);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> !userPage.isUserPresent(userEmail));
        Assert.assertFalse(userPage.isUserPresent(userEmail), "User was not deleted.");
    }

    @Test
    public void testCancelDeleteUser() {
        String userEmail = "moderntalking@gmail.com"; // нужно поменять почту
        userPage.cancelDeleteUser(userEmail);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> userPage.isUserPresent(userEmail));
        Assert.assertTrue(userPage.isUserPresent(userEmail), "User should still be present after canceling deletion.");
    }

    @Test
    public void testEditUser() {
        String userEmail = "moderntalking@gmail.com"; // Нужно заменять на существующую почту
        editUserDataPage = userPage.editUser(userEmail);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/user/info"));
        Assert.assertTrue(editUserDataPage.isEditPageLoaded(), "The Edit User page did not load correctly.");
    }

    @Test
    public void testUserReportsOption() {
        userPage.userReportsOption("moderntalking@gmail.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/reports/userinfo"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/reports/userinfo"), "URL does not contain this part after opening reports.");
    }

    @Test
    public void testLogIntoAccountOption() {
        userPage.logIntoAccountOption("moderntalking@gmail.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/dashboard"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"), "URL does not contain this part after opening reports.");
    }

    @Test
    public void testPerformDeactivateAction() {
        userPage.performMassAction("moderntalking@gmail.com", Action.DEACTIVATE);

        String successMessage = userPage.getMassActionMessage();
        Assert.assertEquals(successMessage, "Successfully deactivated 1 user", "Deactivate action message is incorrect.");
    }

    @Test
    public void testNegativeDeactivateAction() {
        // Первая деактивация пользователя:
        userPage.performMassAction("afra@gmail.com", Action.DEACTIVATE);
        // Повторная деактивация того же пользователя:
        userPage.performMassAction("afra@gmail.com", Action.DEACTIVATE);

        String errorMessage = userPage.getMassActionMessage();
        Assert.assertEquals(errorMessage, "No users to deactivate", "Incorrect error message for already deactivated user.");
    }

    @Test
    public void testPerformActivateAction() {
        userPage.performMassAction("moderntalking@gmail.com", Action.ACTIVATE);

        String successMessage = userPage.getMassActionMessage();
        Assert.assertEquals(successMessage, "Successfully activated 1 user", "Activate action message is incorrect.");
    }

    @Test
    public void testNegativeActivateAction() {
        // Первая активация пользователя:
        userPage.performMassAction("afra@gmail.com", Action.ACTIVATE);
        // Повторная активация того же пользователя:
        userPage.performMassAction("afra@gmail.com", Action.ACTIVATE);

        String errorMessage = userPage.getMassActionMessage();
        Assert.assertEquals(errorMessage, "No users to activate", "Incorrect error message for already activated user.");
    }

    @Test
    public void testPerformDeleteAction() {
        userPage.performMassAction("qwerty@gmail.com", Action.DELETE);

        String successMessage = userPage.getMassActionMessage();
        Assert.assertEquals(successMessage, "Successfully deleted 1 user", "Delete action message is incorrect.");
    }

    @Test
    public void testPerformAddToGroupAction() {
        ActionResult result = userPage.performMassAction("mary@mail.ru", Action.ADD_TO_GROUP);

        String expectedMessage = "Successfully added 1 user to group " + result.getSelectedGroup();
        String successMessage = userPage.getMassActionMessage();

        Assert.assertEquals(successMessage, expectedMessage, "Add to group action message is incorrect.");
        Assert.assertNotNull(result.getSelectedGroup(), "Selected group should not be null.");
    }

    @Test
    public void testAddUserToGroupTwice() {
        //Добавляем пользователя в группу первый раз
        ActionResult result = userPage.performMassAction("moderntalking@gmail.com", Action.ADD_TO_GROUP);

        String successMessage = userPage.getMassActionMessage();
        String expectedMessage = "Successfully added 1 user to group " + result.getSelectedGroup();
        Assert.assertEquals(successMessage, expectedMessage, "Add to group action message is incorrect.");

        // Пытаемся снова добавить того же пользователя в ту же группу
        userPage.performMassAction("moderntalking@gmail.com", Action.ADD_TO_GROUP);

        String successMessage2 = userPage.getMassActionMessage();
        String expectedMessage2 = "Selected user already belongs to group " + result.getSelectedGroup();
        Assert.assertEquals(successMessage2, expectedMessage2, "User should already belong to the selected group.");
    }

    @Test
    public void testPerformAddToBranchAction() {
        ActionResult result = userPage.performMassAction("moderntalking@gmail.com", Action.ADD_TO_BRANCH);

        String expectedMessage = "Successfully added 1 user to branch " + result.getSelectedBranch();
        String successMessage = userPage.getMassActionMessage();

        Assert.assertEquals(successMessage, expectedMessage, "Add to branch action message is incorrect.");
        Assert.assertNotNull(result.getSelectedBranch(), "Selected branch should not be null.");
    }

    @Test
    public void testAddUserToBranchTwice() {
        //Добавляем пользователя в ветку первый раз
        ActionResult result = userPage.performMassAction("moderntalking@gmail.com", Action.ADD_TO_BRANCH);

        String successMessage = userPage.getMassActionMessage();
        String expectedMessage = "Successfully added 1 user to branch " + result.getSelectedBranch();
        Assert.assertEquals(successMessage, expectedMessage, "Add to branch action message is incorrect.");

        // Пытаемся снова добавить того же пользователя в ту же ветку
        userPage.performMassAction("moderntalking@gmail.com", Action.ADD_TO_BRANCH);

        String successMessage2 = userPage.getMassActionMessage();
        String expectedMessage2 = "Selected user already belongs to branch " + result.getSelectedBranch();
        Assert.assertEquals(successMessage2, expectedMessage2, "User should already belong to the selected branch.");
    }

    @Test
    public void testPerformSendMessageAction() {
        userPage.performMassAction("moderntalking@gmail.com", Action.SEND_MESSAGE);

        String successMessage = userPage.getMassActionMessage();
        String expectedMessage = "Message sent successfully";
        Assert.assertEquals(successMessage, expectedMessage, "Send message action message is incorrect.");
    }

    @Test
    public void testSendMessageWithoutSubject() {
        userPage.performMassAction("cathey.kohler@gmail.com", Action.SEND_MESSAGE, true);

        String errorMessage = userPage.getInvalidSubjectMessage();
        String expectedErrorMessage = "'Subject' is required";
        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message for missing subject is incorrect.");
    }

}