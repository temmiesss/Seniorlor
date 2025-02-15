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

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver.get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        Assert.assertTrue(dashboardPage.isPageLoaded(), "ERROR: Login failed, DashboardPage not loaded");
    }

    @Test
    public void testSelectRandomSection() {
        List<Section> sections = Arrays.asList(Section.values());
        Section randomSection = sections.get(random.nextInt(sections.size()));
        String userSection = randomSection.getSectionName();

        dashboardPage.selectSection(randomSection);
        String urlPart;
        if (userSection.equals("Import - Export")) {
            urlPart = "import";
        } else {
            urlPart = "index";
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(urlPart), "ERROR: URL does not contain this part. Current URL: " + currentUrl);
    }

    @Test
    public void testNavigateToAddUserPageFromDashboardPage() {
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
