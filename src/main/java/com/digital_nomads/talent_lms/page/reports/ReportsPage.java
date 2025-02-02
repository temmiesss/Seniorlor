package com.digital_nomads.talent_lms.page.reports;

import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.digital_nomads.talent_lms.drivers.Driver.getDriver;

public class ReportsPage extends BasePage {


    @FindBy(xpath = "//a[contains(text(), 'Reports')]")
    public WebElement reportsBtn;

    @FindBy(xpath = "//a[contains(text(),'User reports')]")
    public WebElement usersReport;

    @FindBy(xpath = "//a[contains(text(),'Course reports')]")
    public WebElement coursesReport;

    @FindBy(xpath = "//a[@title='Branch reports']")
    public WebElement branchesReport;

    @FindBy(xpath = "//div[text()='Branch reports']")
    public WebElement branchesReportText;

    @FindBy(xpath = "//a[@title='Group reports']")
    public WebElement groupsReport;

    @FindBy(xpath = "//div[@class='tl-title tl-ellipsis']")
    public WebElement groupsReportText;


    @FindBy(xpath = "//a[contains(text(),'SCORM reports')]")
    public WebElement scormReport;

    @FindBy(xpath = "//div[text()='SCORM reports']")
    public WebElement scormReportText;


    @FindBy(xpath = "//a[@title='Test reports']")
    public WebElement testsReport;

    @FindBy(xpath = "//div[@class='tl-title tl-ellipsis'][contains(text(), 'Test reports')]")
    public WebElement testsReportText;

    @FindBy(xpath = "//a[@title='Survey reports']")
    public WebElement surveysReport;

    @FindBy(xpath = "//div[@class='tl-title tl-ellipsis'][contains(text(), 'Survey reports')]")
    public WebElement surveysReportText;

    @FindBy(xpath = "//a[@title='Assignment reports']")
    public WebElement assignmentsReport;

    @FindBy(xpath = "//div[@class='tl-title tl-ellipsis'][contains(text(), 'Assignment reports')]")
    public WebElement assignmentsReportText;

    @FindBy(xpath = "//a[@title='ILT reports']")
    public WebElement iltsReport;


    @FindBy(xpath = "//div[@class='tl-title tl-ellipsis'][contains(text(), 'ILT reports')]")
    public WebElement iltsReportText;

    @FindBy(xpath = "//a[@title='Custom reports']")
    public WebElement customReport;

    @FindBy(xpath = "//div[@class='tl-title tl-ellipsis']")
    public WebElement customReportText;

    @FindBy(xpath = "//a[contains(text(),'Overview')]")
    public WebElement overviewReport;

    @FindBy(xpath = "//a[contains(text(),'Infographics')]")
    public WebElement infographicsReport;

    @FindBy(xpath = "//a[contains(text(),'Training matrix')]")
    public WebElement trainingMatrixReport;

    @FindBy(xpath = "//a[contains(text(),'Timeline')]")
    public WebElement timelineReport;


    public ReportsPage clickReportsBtn() {
        webElementActions.click(reportsBtn);
        return this;
    }


    public ReportsPage clickUserReport() {
        webElementActions.click(usersReport);
        return this;
    }


    public ReportsPage clickCoursesReport() {
        webElementActions.click(coursesReport);
        return this;
    }

    public ReportsPage clickBranchesReport() {
        webElementActions.click(branchesReport);
        return this;
    }

    public ReportsPage clickGroupsReport() {
        webElementActions.click(groupsReport);
        return this;
    }

    public ReportsPage clickScormReport() {
        webElementActions.click(scormReport);
        return this;
    }

    public ReportsPage clickTestsReport() {
        webElementActions.click(testsReport);
        return this;
    }

    public ReportsPage clickSurveysReport() {
        webElementActions.click(surveysReport);
        return this;
    }

    public ReportsPage clickAssignmentsReport() {
        webElementActions.click(assignmentsReport);
        return this;
    }

    public ReportsPage clickIltsReport() {
        webElementActions.click(iltsReport);
        return this;
    }

    public ReportsPage clickCustomReport() {
        webElementActions.click(coursesReport);
        return this;
    }

    public ReportsPage clickOverview() {
        webElementActions.click(overviewReport);
        return this;
    }

    public ReportsPage clickInfographicsReport() {
        webElementActions.click(infographicsReport);
        return this;
    }

    public ReportsPage clickTrainingMatrixReport() {
        webElementActions.click(trainingMatrixReport);
        return this;
    }

    public ReportsPage clickTimelineReport() {
        webElementActions.click(timelineReport);
        return this;
    }


    public boolean isElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
}



