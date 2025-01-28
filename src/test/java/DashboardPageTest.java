
import fileUtils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class DashboardPageTest extends BaseTest {

    /**
     * @author Akylai
     * Выбор раздела через рандом
     */
    @Test
    public void testNavigateToRandomSection(){
        driver.get("https://zheenbaikyzyakylai.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        List<String> sections = Arrays.asList("Users", "Courses", "Categories", "Groups",
                "Branches", "Events engine", "User types", "Import - Export", "Reports", "Account & Settings");

        String randomSection = sections.get(random.nextInt(sections.size()));
        System.out.println("Randomly selected section: " + randomSection);

        dashboardPage.navigateToSection(randomSection);
    }

    // Выбор раздела через app.properties
    @Test
    public void testNavigateToSectionFromProperties(){
        driver.get("https://zheenbaikyzyakylai.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.navigateToSection(ConfigReader.getProperty("section"));
    }

    @Test
    public void testNavigateToAddUserPageFromDashboardPage(){
        addUserPage = dashboardPage.navigateToAddUserPage();
        Assert.assertTrue(addUserPage.isPageLoaded(), "AddUserPage did not load correctly");
    }
}
