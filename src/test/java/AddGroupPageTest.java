
import com.digital_nomads.talent_lms.entity.Groups;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.addGroup.AddGroupPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;
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



}

