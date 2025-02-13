
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Akylai
 */
public class DashboardPageTest extends BaseTest {

    @BeforeMethod
    public void setup(){
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

    @Test
    public void testSelectRandomSection(){
        List<Section> sections = Arrays.asList(Section.values());
        Section randomSection = sections.get(random.nextInt(sections.size()));
        System.out.println("Randomly selected section: " + randomSection.getSectionName());

        dashboardPage.selectSection(randomSection);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("index"), "ERROR: URL does not contain 'index'. Current URL: " + currentUrl);
    }

    @Test
    public void testNavigateToAddUserPageFromDashboardPage(){
        addUserPage = dashboardPage.navigateToAddUserPage();
        Assert.assertTrue(addUserPage.isPageLoaded(), "AddUserPage did not load correctly");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSelectInvalidSection() {
        Section invalidSection = Section.valueOf("INVALID_SECTION");
        dashboardPage.selectSection(invalidSection);
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();  // Очищаем куки перед следующим тестом
    }
}
