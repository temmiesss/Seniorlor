package com.digital_nomads.talent_lms.page.course;

import com.digital_nomads.talent_lms.entity.Course;
import com.digital_nomads.talent_lms.page.BasePage;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReportCoursePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"tl-courses-grid\"]/tbody/tr[1]/td[5]/div/div/i[1]")
    public WebElement clickReport;

    @FindBy(xpath = "//a[normalize-space()='Courses']/parent::div[@class='tl-bold-link']")
    public WebElement courseEnter;

    @FindBy(xpath = "//div[@class='btn-group pull-left tl-reports-btn-group']/a")
    public WebElement todayPress;

    @FindBy(xpath = "//*[@id=\"tl-courses-grid\"]/tbody/tr[6]/td[2]")
    public WebElement chooseCourse;

    @FindBy(xpath = "//div[@class='btn-group pull-left tl-reports-btn-group']/a[2]")
    public WebElement yesterdayPress;

    @FindBy(xpath = "//div[@class='btn-group pull-left tl-reports-btn-group']/a[3]")
    public WebElement weekPress;

    @FindBy(xpath = "//div[@class='btn-group pull-left tl-reports-btn-group']/a[4]")
    public WebElement monthPress;

    @FindBy(xpath = "//div[@class='btn-group pull-left tl-reports-btn-group']/a[5]")
    public WebElement yearPress;

    @FindBy(xpath = "//div[@class='btn-group pull-left tl-reports-btn-group']/a[6]")
    public WebElement periodPress;

    @FindBy(xpath = "//div[@class='tl-table-operations-trigger touchable']")
    public WebElement pressBurger;

    @FindBy(id = "tl-export-course")
    public WebElement exportFile;

    @FindBy(xpath = "//*[@id=\"tl-export-user\"]")
    public WebElement userExportFile;

    @FindBy(id = "users-tab")
    public  WebElement usersTabPress;

    @FindBy(xpath = "//*[@id=\"tl-course-reports-list-users\"]/tbody/tr/td[1]/a")
    public WebElement chooseUserReport;



    public ReportCoursePage reportCoursePage(Course course){
        webElementActions.click(courseEnter)
                .moveToElement(pressBurger)
                .click(clickReport);
        return new ReportCoursePage();
    }
    public ReportCoursePage todayReport(){
        todayPress.click();
        return new ReportCoursePage();
    }
    public ReportCoursePage exportFileReport(){
        exportFile.click();
        return this;
    }
    public ReportCoursePage yesterdayReport(){
        yesterdayPress.click();
        return this;
    }
    public ReportCoursePage weekReport(){
        weekPress.click();
        return this;
    }
    public ReportCoursePage monthReport(){
        monthPress.click();
        return this;
    }

    public ReportCoursePage userReport(){
        usersTabPress.click();
        chooseUserReport.click();
        userExportFile.click();
        return this;
    }
    public ReportCoursePage yearUserReport(){
        usersTabPress.click();
        chooseUserReport.click();
        userExportFile.click();
        yearPress.click();
        return this;
    }
    public ReportCoursePage yearReport(){
        yearPress.click();
        return this;
    }
    public ReportCoursePage todayUserReport(){
        usersTabPress.click();
        chooseUserReport.click();
        userExportFile.click();
        todayPress.click();
        return this;
    }

    public ReportCoursePage yesterdayUserReport(){
        usersTabPress.click();
        chooseUserReport.click();
        userExportFile.click();
        yesterdayPress.click();
        return this;
    }
    public ReportCoursePage weekUserReport(){
        usersTabPress.click();
        chooseUserReport.click();
        userExportFile.click();
        weekPress.click();
        return this;
    }
    public ReportCoursePage monthUserReport(){
        usersTabPress.click();
        chooseUserReport.click();
        userExportFile.click();
        monthPress.click();
        return this;
    }

}
