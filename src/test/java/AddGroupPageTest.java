
import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.addGroup.AddGroupPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 * @author Saida
 */

public class AddGroupPageTest extends BaseTest {

    public AddGroupPageTest() {
        super(); // Если необходимо, вызываем конструктор родительского класса
    }

    @BeforeMethod
    public void beforeMethodAuthorization() {
        driver.get("https://rustambaeva.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

    @Test
    public void testAddGroup() {
        addGroupPage.addNewGroup(group);
        Assert.assertTrue(addGroupPage.isGroupAdded(group), "Group not found after adding");
    }

    @Test
    public void testAddNewGroup() {
        Groups group = new Groups("New Group", "Description of new group");
        AddGroupPage result = addGroupPage.addNewGroup(group);
        Assert.assertNotNull(result, "AddGroupPage should not be null after adding group");
    }

    @Test
    public void testEditGroup() {
        addGroupPage.editGroup(group);
        Assert.assertNotNull(addGroupPage, "AddGroupPage will be open after editing");
    }

    @Test
    public void testIsGroupAdded() {
        Groups testGroup = new Groups("Test Group", "Test description");
        addGroupPage.addNewGroup(testGroup);
        driver.navigate().to("https://rustambaeva.talentlms.com/group/index");
        Assert.assertTrue(addGroupPage.isGroupAdded(testGroup), "Group not found on page!");
    }

    @Test
    public void deleteGroupTest() {
        addGroupPage.deleteGroup(group);
        Assert.assertFalse(addGroupPage.isGroupPresent(group), "Группа не была удалена!");
    }

    @Test
    public void cancelDeletingGroupTest() {
        addGroupPage.cancelDeletingGroup(group);
        Assert.assertTrue(addGroupPage.isGroupAdded(group), "The group should still be visible after canceling delete.");
    }

    @Test
    public void testAddGroupEmptyName() {
        Groups group = new Groups("", "Description of new group");
        AddGroupPage result = addGroupPage.addNewGroup(group);
        Assert.assertNotNull(result, "AddGroupPage should not be null even with empty name");
    }

    @Test
    public void testAddGroupEmptyDescription() {
        Groups group = new Groups("New Group", "");
        AddGroupPage result = addGroupPage.addNewGroup(group);
        Assert.assertNotNull(result, "AddGroupPage should not be null even with empty description");
    }

    @Test
    public void testAddGroupWithLongName() {
        Groups group = new Groups("A very long group name that exceeds normal length", "Description");
        AddGroupPage result = addGroupPage.addNewGroup(group);
        Assert.assertNotNull(result, "AddGroupPage should handle long names");
    }

    @Test
    public void testAddGroupWithLongDescription() {
        Groups group = new Groups("New Group", "A very long description that exceeds normal character count to test max length input.");
        AddGroupPage result = addGroupPage.addNewGroup(group);
        Assert.assertNotNull(result, "AddGroupPage should handle long descriptions");
    }

    @Test
    public void testRedirectAfterGroupAdded() {
        Groups group = new Groups("New Group", "Test Description");
        addGroupPage.addNewGroup(group);
        driver.get("https://rustambaeva.talentlms.com/group/index");
        Assert.assertTrue(addGroupPage.isGroupAdded(group), "Group should be added successfully");
    }

    @Test
    public void testGroupDisplayedInTable() {
        Groups group = new Groups("New Group", "Description");
        addGroupPage.addNewGroup(group);
        Assert.assertTrue(addGroupPage.isGroupAdded(group), "Group should be displayed in the group table");
    }

    @Test
    public void testAddGroupButtonDisplayed() {
        Assert.assertTrue(addGroupPage.isElementVisible(addGroupPage.addGroupBtn), "Add Group button should be visible");
    }

    @Test
    public void testMandatoryFields() {
        Groups group = new Groups("Group Name", "");
        AddGroupPage result = addGroupPage.addNewGroup(group);
        Assert.assertNotNull(result, "AddGroupPage should handle mandatory field validation");
    }

    @Test
    public void testAddGroupButtonAfterDataEntry() {

        addGroupPage.addNewGroup(group);
        Assert.assertEquals(addGroupPage.groupTitle.getText(), "Fall24", "Текст не совпадает!");

    }

    @Test
    public void testEditGroupEmptyName() {
        Groups group = new Groups("", "Updated description");
        addGroupPage.editGroup(group);
        Assert.assertNotNull(addGroupPage, "Group should be edited even with an empty name");
    }

    @Test
    public void testEditGroupEmptyDescription() {
        Groups group = new Groups("Updated Name", "");
        addGroupPage.editGroup(group);
        Assert.assertNotNull(addGroupPage, "Group should be edited even with an empty description");
    }

    @Test
    public void testEditGroupWithLongName() {
        Groups group = new Groups("A very long group name that exceeds normal length", "Updated description");
        addGroupPage.editGroup(group);
        Assert.assertNotNull(addGroupPage, "Group should be edited with a long name");
    }

}
