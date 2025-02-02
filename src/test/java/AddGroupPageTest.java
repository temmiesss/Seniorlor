
import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * @author Saida
 */

public class AddGroupPageTest extends BaseTest {

    @Test
    public void testAddGroup() {
        driver.get("https://rustambaeva.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"), ConfigReader.getProperty("password")).switchToLegacyInterface();
        addGroupPage.addNewGroup(group);
    }

    @Test
    public void testEditGroup() {
        driver.get("https://rustambaeva.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"), ConfigReader.getProperty("password")).switchToLegacyInterface();
        addGroupPage.editGroup(group);
        Assert.assertNotNull(addGroupPage, "AddGroupPage will be open after editing");
    }

    @Test
    public void testIsGroupAdded() {
        driver.get("https://rustambaeva.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        Groups testGroup = new Groups("Test Group", "Test description");
        addGroupPage.addNewGroup(testGroup);
        driver.navigate().to("https://rustambaeva.talentlms.com/group/index");
        Assert.assertTrue(addGroupPage.isGroupAdded(testGroup), "Group not found on page!");
    }

    @Test
    public void testDeleteGroup() {
        driver.get("https://rustambaeva.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"), ConfigReader.getProperty("password")).switchToLegacyInterface();
        addGroupPage.deleteGroup(group);
        Assert.assertNotNull(addGroupPage.deleteGroup(group),"AddGroupPage will be open after deleting" );
    }


    }








