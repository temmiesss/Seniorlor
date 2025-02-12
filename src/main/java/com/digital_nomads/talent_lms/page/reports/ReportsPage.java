package com.digital_nomads.talent_lms.page.reports;

import com.digital_nomads.talent_lms.drivers.Driver;
import com.digital_nomads.talent_lms.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author Kanykei
 * **/


public class ReportsPage extends BasePage {
    @FindBy(xpath = "//a[contains(text(),'User reports')]")
    public WebElement userReportBtn;

    @FindBy(xpath = "//a[text()=\"Timeline\"]\n")
    public WebElement timelineReportBtn;

    @FindBy(xpath = "//a[contains(text(),'Infographics')]")
    public WebElement infographicsReportBtn;

    @FindBy(xpath = "//a[contains(@href, '/reports/trainingmatrix') and text()='Training matrix']")
    public WebElement trainingMatrix;

    private WebDriver driver = Driver.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public void openReport(ReportSection section) {
        WebElement reportLink = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(section.getLocator())));
        reportLink.click();
    }


    public boolean isDisplayed(ReportSection section) {
        WebElement reportTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(section.getLocator()));
        return reportTitle.isDisplayed();

    }

    public void selectUserReports() {

        webElementActions.click(userReportBtn);

    }
    public void selectTimelineReport() {
        webElementActions.click(timelineReportBtn);
    }



    public void selectTrainingMatrix(){
        webElementActions.click(trainingMatrix);
    }
    public void selectInfographicsReport() {
        webElementActions.click(infographicsReportBtn);
    }



}