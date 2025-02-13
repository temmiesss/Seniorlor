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
import java.util.List;
import java.util.Random;

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

    @Test
    public void testNavigationAndEditing() {
        addUserPage = userPage.navigateToAddUserPage();
        Assert.assertTrue(addUserPage.isPageLoaded(), "AddUserPage did not load correctly");

        List<String> users = userPage.getUserList();
        String userEmail = users.get(new Random().nextInt(users.size()));
        editUserDataPage = userPage.editUser(userEmail);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/user/info"));
        Assert.assertTrue(editUserDataPage.isEditPageLoaded(), "The Edit User page did not load correctly.");
    }

    @Test
    public void testDeleteAndCancelDeleteUser() {
        List<String> users = userPage.getUserList();
        String userEmail = users.get(new Random().nextInt(users.size()));

        userPage.deleteUser(userEmail);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> !userPage.isUserPresent(userEmail));
        Assert.assertFalse(userPage.isUserPresent(userEmail), "User was not deleted.");

        userPage.cancelDeleteUser(userEmail);
        wait.until(driver -> userPage.isUserPresent(userEmail));
        Assert.assertTrue(userPage.isUserPresent(userEmail), "User should still be present after canceling deletion.");
    }

    @Test
    public void testMassActions() {
        List<String> users = userPage.getUserList();
        String userEmail = users.get(new Random().nextInt(users.size()));

        performAndVerifyAction(userEmail, Action.DEACTIVATE, "Successfully deactivated 1 user");
        performAndVerifyAction(userEmail, Action.ACTIVATE, "Successfully activated 1 user");
        performAndVerifyAction(userEmail, Action.DELETE, "Successfully deleted 1 user");
    }

    private void performAndVerifyAction(String userEmail, Action action, String expectedMessage) {
        userPage.performMassAction(userEmail, action);
        String successMessage = userPage.getMassActionMessage();
        Assert.assertEquals(successMessage, expectedMessage, action + " action message is incorrect.");
    }

    @Test
    public void testGroupAndBranchActions() {
        List<String> users = userPage.getUserList();
        String userEmail = users.get(new Random().nextInt(users.size()));

        ActionResult groupResult = userPage.performMassAction(userEmail, Action.ADD_TO_GROUP);
        Assert.assertEquals(userPage.getMassActionMessage(), "Successfully added 1 user to group " + groupResult.getSelectedGroup());
        Assert.assertNotNull(groupResult.getSelectedGroup(), "Selected group should not be null.");

        ActionResult branchResult = userPage.performMassAction(userEmail, Action.ADD_TO_BRANCH);
        Assert.assertEquals(userPage.getMassActionMessage(), "Successfully added 1 user to branch " + branchResult.getSelectedBranch());
        Assert.assertNotNull(branchResult.getSelectedBranch(), "Selected branch should not be null.");
    }

    @Test
    public void testSendMessage() {
        List<String> users = userPage.getUserList();
        String userEmail = users.get(new Random().nextInt(users.size()));

        userPage.performMassAction(userEmail, Action.SEND_MESSAGE, true);
        Assert.assertEquals(userPage.getInvalidSubjectMessage(), "'Subject' is required", "Error message for missing subject is incorrect.");

        userPage.performMassAction(userEmail, Action.SEND_MESSAGE);
        Assert.assertEquals(userPage.getMassActionMessage(), "Message sent successfully", "Send message action message is incorrect.");
    }

//    @Test
//    public void testNavigateToAddUserPageFromUserPage() {
//        addUserPage = userPage.navigateToAddUserPage();
//        Assert.assertTrue(addUserPage.isPageLoaded(), "AddUserPage did not load correctly");
//    }
//
//    @Test
//    public void testCancelDeleteUser() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.cancelDeleteUser(userEmail);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(driver -> userPage.isUserPresent(userEmail));
//        Assert.assertTrue(userPage.isUserPresent(userEmail), "User should still be present after canceling deletion.");
//    }
//
//    @Test
//    public void testDeleteUser() {
//        List<String> users = userPage.getUserList();
//        Assert.assertFalse(users.isEmpty(), "No users found to delete.");
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.deleteUser(userEmail);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(driver -> !userPage.isUserPresent(userEmail));
//        Assert.assertFalse(userPage.isUserPresent(userEmail), "User was not deleted.");
//    }
//
//    @Test
//    public void testEditUser() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        editUserDataPage = userPage.editUser(userEmail);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.urlContains("/user/info"));
//        Assert.assertTrue(editUserDataPage.isEditPageLoaded(), "The Edit User page did not load correctly.");
//    }
//
//    @Test
//    public void testUserReportsOption() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.userReportsOption(userEmail);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.urlContains("/reports/userinfo"));
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("/reports/userinfo"), "URL does not contain this part after opening reports.");
//    }
//
//    @Test
//    public void testLogIntoAccountOption() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.logIntoAccountOption(userEmail);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.urlContains("/dashboard"));
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("/dashboard"), "URL does not contain this part after opening reports.");
//    }
//
//    @Test
//    public void testPerformDeactivateAction() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.performMassAction(userEmail, Action.DEACTIVATE);
//
//        String successMessage = userPage.getMassActionMessage();
//        Assert.assertEquals(successMessage, "Successfully deactivated 1 user", "Deactivate action message is incorrect.");
//    }
//
//    @Test
//    public void testPerformActivateAction() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.performMassAction(userEmail, Action.ACTIVATE);
//
//        String successMessage = userPage.getMassActionMessage();
//        Assert.assertEquals(successMessage, "Successfully activated 1 user", "Activate action message is incorrect.");
//    }
//
//    @Test
//    public void testPerformDeleteAction() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.performMassAction(userEmail, Action.DELETE);
//
//        String successMessage = userPage.getMassActionMessage();
//        Assert.assertEquals(successMessage, "Successfully deleted 1 user", "Delete action message is incorrect.");
//    }
//
//    @Test
//    public void testPerformAddToGroupAction() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        ActionResult result = userPage.performMassAction(userEmail, Action.ADD_TO_GROUP);
//
//        String expectedMessage = "Successfully added 1 user to group " + result.getSelectedGroup();
//        String successMessage = userPage.getMassActionMessage();
//
//        Assert.assertEquals(successMessage, expectedMessage, "Add to group action message is incorrect.");
//        Assert.assertNotNull(result.getSelectedGroup(), "Selected group should not be null.");
//    }
//
//    @Test
//    public void testPerformAddToBranchAction() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        ActionResult result = userPage.performMassAction(userEmail, Action.ADD_TO_BRANCH);
//
//        String expectedMessage = "Successfully added 1 user to branch " + result.getSelectedBranch();
//        String successMessage = userPage.getMassActionMessage();
//
//        Assert.assertEquals(successMessage, expectedMessage, "Add to branch action message is incorrect.");
//        Assert.assertNotNull(result.getSelectedBranch(), "Selected branch should not be null.");
//    }
//
//    @Test
//    public void testPerformSendMessageAction() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.performMassAction(userEmail, Action.SEND_MESSAGE);
//
//        String successMessage = userPage.getMassActionMessage();
//        String expectedMessage = "Message sent successfully";
//        Assert.assertEquals(successMessage, expectedMessage, "Send message action message is incorrect.");
//    }
//
//    @Test
//    public void testSendMessageWithoutSubject() {
//        List<String> users = userPage.getUserList();
//        String userEmail = users.get(new Random().nextInt(users.size()));
//        userPage.performMassAction(userEmail, Action.SEND_MESSAGE, true);
//
//        String errorMessage = userPage.getInvalidSubjectMessage();
//        String expectedErrorMessage = "'Subject' is required";
//        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message for missing subject is incorrect.");
//    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();  // Очищаем куки перед следующим тестом
    }
}