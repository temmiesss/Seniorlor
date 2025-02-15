import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.enums.Section;
import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.reports.ReportSection;
import com.digital_nomads.talent_lms.page.reports.ReportsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.digital_nomads.talent_lms.page.reports.ReportSection.*;


/**
 * @author Kanykei
 * для проверки кликабельности
 * **/


public class ReportsTest extends BaseTest {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    ReportsPage reportsPage;

    @BeforeMethod
    public void setUp() {

        dashboardPage = new DashboardPage();
        loginPage = new LoginPage();
        reportsPage = new ReportsPage();
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();
        dashboardPage.selectSection(Section.REPORTS);
    }

    @Test
    public void userTest() {

        reportsPage.selectUserReports();
        WebElement isRequired = driver.findElement(By.xpath("//body/div/div/div/div[text()='User reports']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / User reports");
    }

    @Test
    public void testUserReport() {
        reportsPage.openReport(ReportSection.USER_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//body/div/div/div/div[text()='User reports']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / User reports");
    }

    @Test
    public void testCourseReport() {
        reportsPage.openReport(ReportSection.COURSE_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//body/div/div/div/div[text()='Course reports']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / Course reports");


    }

    @Test
    public void testBranchReport() {
        reportsPage.openReport(ReportSection.BRANCH_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//body/div/div/div/div[text()='Branch reports']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / Branch reports");

    }

    @Test
    public void testGroup() {
        reportsPage.openReport(GROUP_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//div[@class='tl-title tl-ellipsis' and contains(., 'Group reports')]"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / Group reports");


    }

    @Test
    public void testScorm() {
        reportsPage.openReport(SCORM_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//div[contains(@class, 'tl-title') and contains(@class, 'tl-ellipsis') and contains(., 'SCORM reports')]"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / SCORM reports");
    }

    @Test
    public void testReports() {
        reportsPage.openReport(TEST_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//div[contains(@class, 'tl-title') and contains(@class, 'tl-ellipsis') and contains(., 'Test reports')]"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / Test reports");

    }

    @Test
    public void surveyTest() {
        reportsPage.openReport(SURVEY_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//div[contains(@class, 'tl-title') and contains(@class, 'tl-ellipsis') and contains(., 'Survey reports')]"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / Survey reports");
    }

    @Test
    public void assignmentTest() {
        reportsPage.openReport(ASSIGNMENT_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//div[contains(@class, 'tl-title') and contains(@class, 'tl-ellipsis') and contains(., 'Assignment reports')]"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / Assignment reports");
    }

    @Test
    public void iltTest() {
        reportsPage.openReport(ILT_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//div[contains(@class, 'tl-title') and contains(@class, 'tl-ellipsis') and contains(., 'ILT reports')]"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / ILT reports");
    }

    @Test
    public void customTest() {
        reportsPage.openReport(CUSTOM_REPORT);
        WebElement isRequired = driver.findElement(By.xpath("//div[contains(@class, 'tl-title') and contains(@class, 'tl-ellipsis') and contains(., 'Custom reports')]"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Home / Reports / Custom reports");
    }


    @Test
    public void testInfographicsReport() {
        reportsPage.selectInfographicsReport();
        WebElement isRequired = driver.findElement(By.xpath("//a[contains(@href, '/reports/infographics') and text()='Infographics']"));
        webElementActions.scrollToElement(isRequired);
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Infographics");
    }


    @Test
    public void testTrainingMatrix() {
        reportsPage.selectTrainingMatrix();
        WebElement isRequired = driver.findElement(By.xpath("//a[contains(@href, '/reports/trainingmatrix') and text()='Training matrix']"));
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Training matrix");
    }

    @Test
    public void testTimelineReport() {
        reportsPage.selectTimelineReport();
        WebElement isRequired = driver.findElement(By.xpath("//a[contains(@href, '/reports/timeline') and normalize-space(text())='Timeline']"));
        webElementActions.scrollToElement(isRequired);
        String actual = isRequired.getText();
        Assert.assertEquals(actual, "Timeline");
    }

}