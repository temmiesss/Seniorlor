

import com.digital_nomads.talent_lms.fileUtils.ConfigReader;
import com.digital_nomads.talent_lms.page.dashboard.DashboardPage;
import com.digital_nomads.talent_lms.page.login.LoginPage;
import com.digital_nomads.talent_lms.page.reports.ReportsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportsTest extends BaseTest {
    DashboardPage dashboardPage = new DashboardPage();
    LoginPage loginPage = new LoginPage();
    ReportsPage reportsPage = new ReportsPage();


    @Test
    public void reportsTest() {
        LoginPage loginPage = new LoginPage();
        ReportsPage dashBoardReports = new ReportsPage();
        driver.get("https://ideaspark312.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"), ConfigReader.getProperty("password"))
                .switchToLegacyInterface();


        reportsPage.clickReportsBtn();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.usersReport),
                "User reports");

        reportsPage.clickUserReport();
        driver.navigate().back();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.coursesReport),
                "Course reports");
        reportsPage.clickCoursesReport();
        driver.navigate().back();

        reportsPage.clickBranchesReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.branchesReportText),
                "Branch reports");

        driver.navigate().back();

        reportsPage.clickGroupsReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.groupsReportText),
                "Group reports");
        driver.navigate().back();

        reportsPage.clickScormReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.scormReportText),
                "SCORM reports");

        driver.navigate().back();

        reportsPage.clickTestsReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.testsReportText),
                "Test reports");

        driver.navigate().back();


        reportsPage.clickSurveysReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.surveysReportText),
                "Survey reports");

        driver.navigate().back();

        reportsPage.clickAssignmentsReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.assignmentsReportText),
                "Assignment reports");

        driver.navigate().back();

        reportsPage.clickIltsReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.iltsReportText),
                "ILT reports");

        driver.navigate().back();

        reportsPage.clickCustomReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.customReportText),
                "Custom Reports");

        driver.navigate().back();


        reportsPage.clickOverview();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.overviewReport),
                "Home / Reports");
        reportsPage.clickOverview();
        driver.navigate().back();


        reportsPage.clickInfographicsReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.infographicsReport),
                "Your training infographic");
        reportsPage.clickInfographicsReport();
        driver.navigate().back();



        reportsPage.clickTrainingMatrixReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.trainingMatrixReport),
                "Training matrix");

        reportsPage.clickTimelineReport();
        Assert.assertTrue(reportsPage.isElementDisplayed(reportsPage.timelineReport),
                "Timeline");
        reportsPage.clickTimelineReport();
        driver.navigate().back();


    }
}







