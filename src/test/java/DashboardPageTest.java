
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DashboardPageTest extends BaseTest {

    @BeforeTest
    public void setup(){
        driver.get("https://charlieblack.talentlms.com/index");

        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
    }

    /**
     * @author Akylai
     * Выбор раздела вручную из списка DashboardSections
     */
    @Test
    public void testNavigateToSection() {
        Section section = Section.USERS; // Проверяем "Users"
        dashboardPage.selectSection(section.getSectionName());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isDisplayed = wait.until(d -> driver.getCurrentUrl().contains("user"));
        Assert.assertTrue(isDisplayed, "Switching to a " + section + " section is not performed");
    }
    /**
     * @author Akylai
     * Выбор раздела через рандом
     */
    @Test
    public void testSelectRandomSection(){
        Section[] sections = Section.values();
        Section randomSection = sections[random.nextInt(sections.length)];
        dashboardPage.selectSection(randomSection.getSectionName());

    }
}
