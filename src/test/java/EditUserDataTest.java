import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EditUserDataTest extends BaseTest {

    @BeforeTest
    public void setup() {
        driver.get("https://charlieblack.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

    @Test
    public void testEditUserFirstName() {
        WebElement usersSection = dashboardPage.findSectionByName(Section.USERS);
        webElementActions.click(usersSection);
        String expectedUrlPart = "user";
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart),
                "URL does not contains this part");

        user.setFirstName("Kate");
        editUserDataPage.editUserFirstName(user);

        // Проверка поля с именем пользователя после обновления
        WebElement updatedFirstName = editUserDataPage.getUpdatedFirstNameField();

        // Проверяем, что имя изменилось
        Assert.assertEquals(updatedFirstName.getAttribute("value"), user.getFirstName(),
                "User FirstName does not changed");
    }


}
