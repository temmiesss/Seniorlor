import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @authot Akylai
 */

public class EditUserDataTest extends BaseTest{

    @BeforeTest
    public void setup() {
        driver.get("https://badykeeva.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.USERS);
    }

    /**
     * Проверяем, что имя пользователя успешно обновляется.
     * В итоге получаем обновлённое поле с именем и сравниваем его значение с ожидаемым ("Kate") с помощью Assert.assertEquals()
     */
    @Test
    public void testEditUserFirstName() {
        user.setFirstName("Kate");
        editUserDataPage.editUserFirstName(user);

        WebElement updatedFirstName = editUserDataPage.getUpdatedFirstNameField();
        Assert.assertEquals(updatedFirstName.getAttribute("value"), user.getFirstName(),
                "User FirstName does not changed");
    }

    /**
     * Проверяем, что фамилия пользователя успешно обновляется.
     * В итоге получаем обновлённое поле с фамилией и сравниваем его значение с ожидаемым ("Osborne") с помощью Assert.assertEquals()
     */
    @Test
    public void testEditUserLastName() {
        user.setLastName("Osborne");
        editUserDataPage.editUserLastName(user);

        WebElement updatedLastName = editUserDataPage.getUpdatedLastNameField();
        Assert.assertEquals(updatedLastName.getAttribute("value"), user.getLastName(),
                "User LastName does not changed");
    }

    /**
     * Проверка изменения вида пользователя
     */
    @Test
    public void testEditUserType() {
        String expectedUserType = "Trainer-Type";
        editUserDataPage.editUserType(expectedUserType);

        WebElement updatedUserTypeField = editUserDataPage.getUpdatedUserTypeField();
        String actualUserType = updatedUserTypeField.getText();
        Assert.assertEquals(actualUserType, expectedUserType, "User type does not change correctly");
    }
    /**
     * Проверка изменения временной зоны
     */
    @Test
    public void testEditTimeZone() {
        String expectedTimeZone = "(GMT -01:00) Cape Verde";
        editUserDataPage.editTimeZone(expectedTimeZone);

        WebElement updatedTimeZoneField = editUserDataPage.getUpdateTimeZoneField();
        String actualTimeZone = updatedTimeZoneField.getText();
        Assert.assertEquals(actualTimeZone, expectedTimeZone, "Time Zone does not change correctly");
    }
    /**
     * Проверка изменения языка
     */
    @Test
    public void testEditLanguage() {
        String expectedLanguage = "Pусский (Russian)";
        editUserDataPage.editLanguage(expectedLanguage);

        WebElement updatedLanguageField = editUserDataPage.getUpdatedLanguageField();
        String actualLanguage = updatedLanguageField.getText();
        Assert.assertEquals(actualLanguage, expectedLanguage, "Language does not change correctly");
    }
}
