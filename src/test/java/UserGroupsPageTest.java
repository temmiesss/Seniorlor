import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Akylai
 */
public class UserGroupsPageTest extends BaseTest{

    @BeforeMethod
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.USERS);
        randomUser = randomUserGenerator.randomUser();
    }

    @Test
    public void testAddUserToGroup() {
        editUserDataPage.openUserProfile("mary");
        userGroupsPage = editUserDataPage.clickGroups();

        String groupName = "Family";
        userGroupsPage.addUserToGroup(groupName);

        boolean isRegistrationLabelVisible = userGroupsPage.registrationLabel.isDisplayed();
        Assert.assertTrue(isRegistrationLabelVisible, "Registration label should be visible after adding user to group");
    }

    @Test
    public void testRemoveUserFromGroup() {
        editUserDataPage.openUserProfile("mary");
        userGroupsPage = editUserDataPage.clickGroups();

        String groupName = "Work";
        userGroupsPage.removeUserFromGroup(groupName);

        boolean isAddToGroupButtonVisible = userGroupsPage.addToGroupOption.isDisplayed();
        Assert.assertTrue(isAddToGroupButtonVisible, "Add to group button should be visible after removing user from group");
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();  // Очищаем куки перед следующим тестом
    }

}
